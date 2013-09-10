package cs.ecl.w12.project.grybasli;
	 
import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
	 
import cs.ecl.w12.project.grybasli.R;
import cs.ecl.w12.project.grybasli.MyContentProvider;
	 
public class AnalysisActivity extends FragmentActivity {
	   
	    @SuppressWarnings("unused")
	    private ArrayList<String> dateArray =  new ArrayList<String>(); 
	    @SuppressWarnings("unused")
	    private ArrayList<String> timeArray =  new ArrayList<String>(); 
	    @SuppressWarnings("unused")
	    private ArrayList<String> diastolicArray =  new ArrayList<String>(); 
	    @SuppressWarnings("unused")
	    private ArrayList<String> systolicArray =  new ArrayList<String>(); 
	    @SuppressWarnings("unused")
	    private ArrayList<String> pulseArray =  new ArrayList<String>();   
	 

	    @SuppressWarnings("deprecation")
		protected void onCreate(Bundle savedInstanceState){
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_analysis);
	           
	            // set value into textview
	            TextView textView = (TextView) findViewById(R.id.systolic);
	            TextView textView2 = (TextView) findViewById(R.id.diastolic);
	            TextView textView3 = (TextView) findViewById(R.id.pulse);
	            TextView textView4 = (TextView) findViewById(R.id.modeSys);
	            TextView textView5 = (TextView) findViewById(R.id.meanSys);
	            TextView textView6 = (TextView) findViewById(R.id.medSys);
	            TextView textView7 = (TextView) findViewById(R.id.rangeSys);
	            TextView textView8 = (TextView) findViewById(R.id.standardSys);
	            TextView textView9 = (TextView) findViewById(R.id.variantSys);
	            TextView textView10 = (TextView) findViewById(R.id.modeDias);
	            TextView textView11 = (TextView) findViewById(R.id.meanDias);
	            TextView textView12 = (TextView) findViewById(R.id.medDias);
	            TextView textView14 = (TextView) findViewById(R.id.rangeDias);
	            TextView textView15 = (TextView) findViewById(R.id.standardDias);
	            TextView textView16 = (TextView) findViewById(R.id.variantDias);
	            TextView textView17 = (TextView) findViewById(R.id.modePulse);
	            TextView textView18 = (TextView) findViewById(R.id.meanPulse);
	            TextView textView19 = (TextView) findViewById(R.id.medPulse);
	            TextView textView20 = (TextView) findViewById(R.id.rangePulse);
	            TextView textView21 = (TextView) findViewById(R.id.standardPulse);
	            TextView textView22 = (TextView) findViewById(R.id.variantPulse);
	           
	            BPDBHelper databaseHelper = new BPDBHelper(this);
	            SQLiteDatabase sqlDatabase = databaseHelper.getReadableDatabase();
	            
	            Cursor cursor = sqlDatabase.query(BPTable1.TABLE_BP, null, null, null, null, null, null);
	            
	            startManagingCursor(cursor);
	            
	            while(cursor.moveToNext()){
	            	
	            	String systolic = cursor.getString(cursor.getColumnIndex(BPTable1.COLUMN_SYSTOLIC));
	            	String diastolic = cursor.getString(cursor.getColumnIndex(BPTable1.COLUMN_DIASTOLIC));
	            	String heartrate = cursor.getString(cursor.getColumnIndex(BPTable1.COLUMN_HEARTRATE));
	            	
	            	systolicArray.add(systolic);
	            	diastolicArray.add(diastolic);
	            	pulseArray.add(heartrate);
	            }
	          
	            sqlDatabase.close();
	            
	            int sysMean = getMean("systolic");
	            int diasMean = getMean("diastolic");
	            int pulseMean = getMean("pulse");
	            
	            int sysMed = getMedian("systolic");
	            int diasMed = getMedian("diastolic");
	            int pulseMed = getMedian("pulse");
	            
	            int stdSys = (int) getStandard("systolic");
	            int stdDias = (int) getStandard("diastolic");
	            int stdPulse = (int) getStandard("pulse");
	            
	            int varSys = (int) getVariance("systolic");
	            int varDias = (int) getVariance("diastolic");
	            int varPulse = (int) getVariance("pulse");
	            
	            int rngSys = getRange("systolic");
	            int rngDias = getRange("diastolic");
	            int rngPulse = getRange("pulse");
	            
	            int modeSys = getMode("systolic");
	            int modeDias = getMode("diastolic");
	            int modePulse = getMode("pulse");
	            
	            textView.setText("Systolic");
	            textView2.setText("Diastolic");
	            textView3.setText("Pulse");
	            
	            textView4.setText(Integer.toString(modeSys) + " ");
	            textView5.setText(Integer.toString(sysMean) + " ");
	            textView6.setText(Integer.toString(sysMed) + " ");
	            textView7.setText(Integer.toString(rngSys) + " ");
	            textView8.setText(Integer.toString(stdSys) + " ");
	            textView9.setText(Integer.toString(varSys) + " ");
	            textView10.setText(Integer.toString(modeDias) + " ");
	            textView11.setText(Integer.toString(diasMean) + " ");
	            textView12.setText(Integer.toString(diasMed) + " ");
	            textView14.setText(Integer.toString(rngDias) + " ");
	            textView15.setText(Integer.toString(stdDias) + " ");
	            textView16.setText(Integer.toString(varDias) + " ");
	            textView17.setText(Integer.toString(modePulse) + " ");
	            textView18.setText(Integer.toString(pulseMean) + " ");
	            textView19.setText(Integer.toString(pulseMed) + " ");
	            textView20.setText(Integer.toString(rngPulse) + " ");
	            textView21.setText(Integer.toString(stdPulse) + " ");
	            textView22.setText(Integer.toString(varPulse) + " ");

	            //String mobile = mobileValues[position
	    }

	    public int getMedian(String type){
	    	ArrayList<String> meanType = null;
	    	if(type == "systolic"){
	    		meanType = systolicArray;
	    	}
	    	if(type == "diastolic"){
	    		meanType = diastolicArray;
	    	}
	    	if(type == "pulse"){
	    		meanType = pulseArray;
	    	}
	    	
	    	int i, temp, sum = 0;
	    	
	    		for(i = 0; i < meanType.size(); i++){
	    		
	    			temp = Integer.parseInt(meanType.get(i));
	    			sum += temp;
	    		
	    		}
	    		int ret = sum / 2;
	        return ret;

	    }
	    public int getMean(String type){
	       
	    	ArrayList<String> meanType = null;
	    	if(type == "systolic"){
	    		meanType = systolicArray;
	    	}
	    	if(type == "diastolic"){
	    		meanType = diastolicArray;
	    	}
	    	if(type == "pulse"){
	    		meanType = pulseArray;
	    	}
	    	
	    	int i, n = 1, temp, sum = 0;
	    	
	    		for(i = 0; i < meanType.size(); i++){
	    		
	    			temp = Integer.parseInt(meanType.get(i));
	    			sum += temp / n;
	    			n++;
	    		
	    		}
	        return sum;
	    }
	    
	    public int getMode(String type){
	     	ArrayList<String> meanType = null;
	    	if(type == "systolic"){
	    		meanType = systolicArray;
	    	}
	    	if(type == "diastolic"){
	    		meanType = diastolicArray;
	    	}
	    	if(type == "pulse"){
	    		meanType = pulseArray;
	    	}
	    	
	    	int i, n = 1, temp, temp2, z = 0, c = 0, store = 0;
	    	
    		for(i = 0; i < meanType.size(); i++){
    		
    			temp = Integer.parseInt(meanType.get(i));
    			
    			while(z < meanType.size()){
    				n= 0;
    				temp2 = Integer.parseInt(meanType.get(z));

    				if(temp == temp2)n++;
    				
    				z++;
    				
    			}
    			
				
				if(n >= c){
					store = temp;
				}
    		
    		}
    		
    		
	    	
	    	return store;
	    }
	    
	    public int getRange(String type){
	     	ArrayList<String> meanType = null;
	    	if(type == "systolic"){
	    		meanType = systolicArray;
	    	}
	    	if(type == "diastolic"){
	    		meanType = diastolicArray;
	    	}
	    	if(type == "pulse"){
	    		meanType = pulseArray;
	    	}
	    	
	    	int i, n = 1, temp, min = 0, max = 0;
	    	
    		for(i = 0; i < meanType.size(); i++){
    		
    			temp = Integer.parseInt(meanType.get(i));
    			if(i == 0){
    				min = temp;
    				max = temp;
    			}
    			if(temp <= min){
    				min = temp;
    			}
    			if(temp >= max){
    				max = temp;
    			}
    		}
    		
	    	return max - min;
	    }
	   
	    public double getVariance(String type) {
	    	ArrayList<String> meanType = null;
	    	if(type == "systolic"){
	    		meanType = systolicArray;
	    	}
	    	if(type == "diastolic"){
	    		meanType = diastolicArray;
	    	}
	    	if(type == "pulse"){
	    		meanType = pulseArray;
	    	}
	    	
	        long n = 0;
	        double mean = 0;
	        double s = 0.0;
	        int x = 0;
	        double temp = 0;
	        
	        for (x = 0; x < meanType.size(); x++) {
	       
	        	temp = Double.parseDouble(meanType.get(x));
	            n++;
	            double delta = temp - mean;
	            mean += delta / n;
	            s += delta * (temp - mean);
	       
	        }           
	            // if you want to calculate std deviation
	            return s;
	        }
	   
	    public double getStandard(String type){
	    	
	    	ArrayList<String> meanType = null;
	    	if(type == "systolic"){
	    		meanType = systolicArray;
	    	}
	    	if(type == "diastolic"){
	    		meanType = diastolicArray;
	    	}
	    	if(type == "pulse"){
	    		meanType = pulseArray;
	    	}
	    	
	        long n = 0;
	        double mean = 0;
	        double s = 0.0;
	        int x = 0;
	        double temp = 0;
	        
	        for (x = 0; x < meanType.size(); x++) {
	       
	        	temp = Double.parseDouble(meanType.get(x));
	            n++;
	            double delta = temp - mean;
	            mean += delta / n;
	            s += delta * (temp - mean);
	       
	        }           
	            // if you want to calculate std deviation
	            return (s / n);
	    }
}
	