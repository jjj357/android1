package cs.ecl.w12.project.grybasli;

//import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.demo.DemoView;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.data.time.Minute;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
import org.afree.ui.RectangleInsets;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;

public class MyDemoView extends DemoView {

	private ArrayList<String> list_date =  new ArrayList<String>();  
	private ArrayList<String> list_time =  new ArrayList<String>();  
	private ArrayList<String> list_diastolic =  new ArrayList<String>();  
	private ArrayList<String> list_systolic =  new ArrayList<String>();  
	private ArrayList<String> list_pulse =  new ArrayList<String>();  
	final XYDataset dataset;
	final AFreeChart chart;
	    /**
	     * constructor
	     * @param context
	     */
	    public MyDemoView(Context context) {
	        super(context);
           
	        dataset = createDataset(context);
	        chart = createChart(dataset);

	        setChart(chart);
	    }
	    


	    
	    public MyDemoView(Context context, AttributeSet attrs) {
	        super(context, attrs);
	        dataset = createDataset(context);
	        chart = createChart(dataset);

	        setChart(chart);
	       //initialize();
	    }

	    /**
	     * Creates a sample dataset.
	     * @return a sample dataset.
	     */
	    

		  
		  
	    XYDataset createDataset(Context context) {
	    	
			  //get data from database
			// Form an array specifying which columns to return.
			  int counter = 0;
			  String[] projection = new String[] {
					  BPTable1.COLUMN_ID,
					  BPTable1.COLUMN_DATE,
					  BPTable1.COLUMN_TIME,
					  BPTable1.COLUMN_SYSTOLIC,
					  BPTable1.COLUMN_DIASTOLIC,
					  BPTable1.COLUMN_HEARTRATE
			  };
			  
			  // Get the base URI for the BPTable table in the Contacts content provider.
			  Uri uri = MyContentProvider.CONTENT_URI;
			  
			  // Make the query.
		   if (uri != null){
			  Cursor cur = context.getContentResolver().query(uri,  ///managedQuery(uri,  //   
			  projection, // Which columns to return
			  null, // Which rows to return (all rows)
			  null, // Selection arguments (none)
			  // Put the results in ascendingorder by name
			  BPTable1.COLUMN_ID + " ASC");
              
			  
			  //reading data
			  if (cur.moveToFirst()) {
				  //String name;
				 // String phoneNumber;
				  int dateColumn = cur.getColumnIndex(BPTable1.COLUMN_DATE);
				  int timeColumn = cur.getColumnIndex(BPTable1.COLUMN_TIME);
				  int systolicColumn = cur.getColumnIndex(BPTable1.COLUMN_SYSTOLIC);
				  int diastolicColumn = cur.getColumnIndex(BPTable1.COLUMN_DIASTOLIC);
				  int pulseColumn = cur.getColumnIndex(BPTable1.COLUMN_HEARTRATE);
				  
				  
				  //String imagePath;
				  do {
				      // Get the field values
					  list_date.add(cur.getString(dateColumn));
					  list_time.add(cur.getString(timeColumn));
					  list_systolic.add(cur.getString(systolicColumn));
					  list_diastolic.add(cur.getString(diastolicColumn));
					  list_pulse.add(cur.getString(pulseColumn));
					  counter++;

				  } while (cur.moveToNext());
			  }
			}
			  
	          TimeSeries ts1 = new TimeSeries("systolic");
	          for (int j=0;j<counter;j++)  {   
	             ts1.addOrUpdate(new Minute(Integer.parseInt(list_time.get(j).substring(2)), Integer.parseInt(list_time.get(j).substring(0,2)),Integer.parseInt(list_date.get(j).substring(6)),
	            		 Integer.parseInt(list_date.get(j).substring(4,6)),Integer.parseInt(list_date.get(j).substring(0,4))),Integer.parseInt(list_systolic.get(j)));
	          }
	         
	          TimeSeries ts2 = new TimeSeries("diastolic");
	          for (int j=0;j<counter;j++)  {   
	             ts2.addOrUpdate(new Minute(Integer.parseInt(list_time.get(j).substring(2)), Integer.parseInt(list_time.get(j).substring(0,2)),Integer.parseInt(list_date.get(j).substring(6)),
	            		 Integer.parseInt(list_date.get(j).substring(4,6)),Integer.parseInt(list_date.get(j).substring(0,4))),Integer.parseInt(list_diastolic.get(j)));
	          }
	          
	          TimeSeries ts3 = new TimeSeries("pulse");
	          for (int j=0;j<counter;j++)  {   
	             ts3.addOrUpdate(new Minute(Integer.parseInt(list_time.get(j).substring(2)), Integer.parseInt(list_time.get(j).substring(0,2)),Integer.parseInt(list_date.get(j).substring(6)),
	            		 Integer.parseInt(list_date.get(j).substring(4,6)),Integer.parseInt(list_date.get(j).substring(0,4))),Integer.parseInt(list_pulse.get(j)));
	          }

	          /*
	          TimeSeries ts2 = new TimeSeries("diastolic");
	          for (int j=0;j<=i-4;j=j+4)  {  
	             ts2.add(new Second(Integer.parseInt(array1[j+3].substring(12)), Integer.parseInt(array1[j+3].substring(10,12)),Integer.parseInt(array1[j+3].substring(8,10)),
	            		 Integer.parseInt(array1[j+3].substring(6,8)),Integer.parseInt(array1[j+3].substring(4,6)),Integer.parseInt(array1[j+3].substring(0,4))), Integer.parseInt(array1[j+1]));
	          }
	          
	          TimeSeries ts3 = new TimeSeries("heart rate");
	          for (int j=0;j<=i-4;j=j+4)  {  
		         ts3.add(new Second(Integer.parseInt(array1[j+3].substring(12)), Integer.parseInt(array1[j+3].substring(10,12)),Integer.parseInt(array1[j+3].substring(8,10)),
		                 Integer.parseInt(array1[j+3].substring(6,8)),Integer.parseInt(array1[j+3].substring(4,6)),Integer.parseInt(array1[j+3].substring(0,4))), Integer.parseInt(array1[j+2]));
	          }  */
	          
	          TimeSeriesCollection dataset = new TimeSeriesCollection();
	          dataset.addSeries(ts1);
	          dataset.addSeries(ts2);
	          dataset.addSeries(ts3);
	          
	          return dataset;
	    }

	    /**
	     * Creates a chart.
	     * @param dataset the dataset.
	     * @return a chart.
	     */
	    AFreeChart createChart(XYDataset dataset) {

	    	AFreeChart chart = ChartFactory.createTimeSeriesChart(
	                  "Heart Data Report",  // title
	                  "heart data",         // (red-systolic,blue-diastolic,green-heart-rate)",             // x-axis label
	                  "value",   // y-axis label
	                  dataset,            // data
	                  true,               // create legend?
	                  true,               
	                  false               // generate URLs?
	              );

	          chart.setBackgroundPaintType(new SolidColor(Color.GREEN));

	              //chart.setBackgroundPaintType( Color.GREEN);

	              XYPlot plot = (XYPlot) chart.getPlot();
	              plot.setBackgroundPaintType(new SolidColor(Color.LTGRAY));
	              plot.setDomainGridlinePaintType(new SolidColor(Color.WHITE));
	              plot.setRangeGridlinePaintType(new SolidColor(Color.WHITE));
	              plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
	              plot.setDomainCrosshairVisible(true);
	              plot.setRangeCrosshairVisible(true);

	              XYItemRenderer r = plot.getRenderer();
	              if (r instanceof XYLineAndShapeRenderer) {
	                  XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
	                  renderer.setBaseShapesVisible(true);
	                  renderer.setBaseShapesFilled(true);
	                  renderer.setDrawSeriesLineAsPath(true);
	              }

	              DateAxis axis = (DateAxis) plot.getDomainAxis();
	              axis.setDateFormatOverride(new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss"));               //MMM-yyyy"));
	              
	    	////////////////////////////////
	        //plot.setLabelFont(new Font("SansSerif", Typeface.NORMAL, 12));
	        //plot.setNoDataMessage("No data available");
	        //plot.setCircular(false);
	        //plot.setLabelGap(0.02);
	        return chart;

	    }
	}//end of class myDemoView






