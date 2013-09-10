package cs.ecl.w12.project.grybasli;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cs.ecl.w12.project.grybasli.MyContentProvider;
import cs.ecl.w12.project.grybasli.BPDBHelper;
import cs.ecl.w12.project.grybasli.BPTable1;
import cs.ecl.w12.project.grybasli.R;
import cs.ecl.w12.project.grybasli.DateTimePicker;


import cs.ecl.w12.project.grybasli.DateTimePicker.ICustomDateTimeListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;
import android.widget.ToggleButton;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
//import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Toast;


public class FirstTab extends Activity implements ICustomDateTimeListener,OnClickListener  {
	/** Called when the activity is first created. */
	
	private Spinner mSite,mPosition;
	  private Button btnSubmit;
	  private DateTimePicker dateTimePicker;
    private ToggleButton mLb_Kg;
	  private EditText mSystolic,mDiastolic,mPulse,mWeight,mComment;
	  private CheckBox mArrhythmia,mAnalysis;

	  private Uri bpUri;
	  
	  public static double kg_to_lb = 1.0;
	  
	  public static Calendar mycalendar_date=Calendar.getInstance();
	  
	  public static int datetimepicked = 0;
	  
	  public static  String Site = "";
	  public static  int Site_position = 0;
	  public static  String Position = "";
	  public static  int Position_position = 0;
	  public static  String Systolic = "";
	  public static  String Diastolic = "";
	  public static  String Pulse = "";
	  public static  int    Weight = 0;
      public static  String Arrhythmia = "n";
      public static  String Comment ="";
      public static  String Analysis = "n";
	  public static  String strdate1 = null;
	  public static  String strdate2 = null;
	  

  private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* First Tab Content */
		setContentView(R.layout.activity_main);  

		//set button onclick listener to open the date time picker dialogue
        Button button = (Button) findViewById(R.id.button_picktimedate);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                // create date time picker
                //dateTimePicker = new DateTimePicker(FirstTab.this, DateTimePicker.iCustomDateTimeListener);
                //dateTimePicker.set24HourFormat(true);
                //dateTimePicker.showDialog();
                //dateTimePicker.onClick(v);

            	saveState();
            	
            	Intent i = new Intent(FirstTab.this, PickDateTimeActivity.class);
            	startActivity(i);
            			
            			
            }
        });
        
        //save data into content provider
	    mSite = (Spinner) findViewById(R.id.spinner1);
	    mPosition = (Spinner) findViewById(R.id.spinner2);
	    mSystolic = (EditText) findViewById(R.id.edittext_systolic);
	    mDiastolic = (EditText) findViewById(R.id.edittext_diastolic);
	    mPulse = (EditText) findViewById(R.id.edittext_pulse);
	    mWeight = (EditText) findViewById(R.id.edittext_weight);
	    mLb_Kg = (ToggleButton) findViewById(R.id.togglebutton);
	    mArrhythmia = (CheckBox) findViewById(R.id.checkbox_arrhythmia);
	    mComment = (EditText) findViewById(R.id.edittext_comment);
	    mAnalysis = (CheckBox) findViewById(R.id.checkbox_analysis);

	    Button confirmButton = (Button) findViewById(R.id.button_Save);
	    
	    Bundle extras = getIntent().getExtras();

	    // Check from the saved Instance
	    bpUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
	        .getParcelable(MyContentProvider.CONTENT_ITEM_TYPE);

	    
	    // Or passed from the other activity
	    if (extras != null) {
	    	//bpUri = extras
	        //  .getParcelable(MyContentProvider.CONTENT_ITEM_TYPE);

	      //fillData(bpUri);
	    }
	    
	    //////////////save field's value for coming back from another activity//////////////////////
	    if (!Site.equals("")) {
	    	mSite.setSelection(Site_position);
	    }
	    if (!Position.equals("")) {
	        mPosition.setSelection(Position_position);
	    }
	    if (!Systolic.equals("")) {
	       mSystolic.setText(Systolic);
	    }
	    if (!Diastolic.equals("")) {
	       mDiastolic.setText(Diastolic);
	    }
	    if (!Pulse.equals("")) {
	       mPulse.setText(Pulse);
	    }
	    if (Weight != 0) {
	       mWeight.setText(new Integer(Weight).toString());
	    }
	    
	    if (kg_to_lb == 2.20462) {
	    	mLb_Kg.setChecked(true);  //  .setPressed(true);
	    }
	    
	    if (Arrhythmia.equals("y")) {
	    	mArrhythmia.setSelected(true);   //Checked(true);
	    }
	    
	    if (!Comment.equals("")) {
            mComment.setText(Comment);
	    }
	    
	    if (Analysis.equals("y")) {    
	    	mAnalysis.setChecked(true);
	    }
	    
	    
	    
	    
	   // Toast.makeText(FirstTab.this, "Site is "+Site +"Position is "+ Position,
		//        Toast.LENGTH_LONG).show();
	    
	    if (Systolic.length() == 0 || Diastolic.length() == 0 ||
		    	Pulse.length() == 0 || (mWeight.getText().toString()).length() == 0 ||
		    			datetimepicked == 0) {  //DateTimePicker.calendar_date == null) {
		    Toast.makeText(FirstTab.this, "Please enter all the data",
			        Toast.LENGTH_LONG).show();
		      return;
		    }
	    
	    
	    if (mWeight.getText().toString() != "") {
	        Weight = Integer.parseInt(mWeight.getText().toString());
	    }
	  
	    
	    
	    if (mLb_Kg.isPressed()) {
	    	kg_to_lb = 2.20462;		    	
	    }		    
	    //String Lb_Kg = mLb_Kg.getText().toString();
	    
	    //String Arrhythmia = "n";
	    if (mArrhythmia.isPressed()) {	    
	       Arrhythmia = "y";
	    }
	    
	    Comment = mComment.getText().toString();
	    
	    //String Analysis = "n";
	    if (mAnalysis.isPressed()) {	    
	    	Analysis = "y";
	    }
	    
	    
        ////////////////////////////////////
	    confirmButton.setOnClickListener(new View.OnClickListener() {
	      public void onClick(View view) {
	        if (TextUtils.isEmpty(mSystolic.getText().toString()) || TextUtils.isEmpty(mDiastolic.getText().toString()) ||
	        	TextUtils.isEmpty(mPulse.getText().toString()) || TextUtils.isEmpty(mWeight.getText().toString()) ||	        		        	
	        	DateTimePicker.calendar_date == null ) {
	        	//|| TextUtils.isEmpty(mSite.getSelectedItem().toString()) ||
	        	//TextUtils.isEmpty(mPosition.getSelectedItem().toString()) )
	        	//System.out.println("in save button onclick(), msystolic is " + mSystolic.getText().toString()+"mdiastolic is " + mDiastolic.getText().toString() +
	        		//	 "mpulse is " + mPulse.getText().toString() + "weight is " + mWeight.getText().toString() +
	        		//	 "date is " + DateTimePicker.calendar_date.toString());
	            makeToast();
	            return;
	        } else {
	            saveStateIntoDatabase();
	            setResult(RESULT_OK);
	          //Intent intent = new Intent(getActivity(),bpsOverviewActivity.class);
	         // startService(intent);
	          //finish();
	        }
	      
	      }
	    });  
	    
	    
        
        

	}//end of oncreate()
	
	
	
	//@Override
	public void onClick(View src) {
   // public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (src.getId() == R.id.button_Save) {

		          Intent intent = new Intent(this,bpsOverviewActivity.class);
		          startService(intent);

		}
		
	}  
	
	
	
    //add listener to spinner site and position
    public void addListenerOnSpinnerItemSelection() {
    	mSite = (Spinner) findViewById(R.id.spinner1);
    	mSite.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    	
    	mPosition = (Spinner) findViewById(R.id.spinner2);
    	mPosition.setOnItemSelectedListener(new CustomOnItemSelectedListener());
      }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
	    
	    saveState();
	  //  if (outState != null){
	  //      outState.putParcelable(MyContentProvider.CONTENT_ITEM_TYPE, bpUri);
      //      outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
      //          getActionBar().getSelectedNavigationIndex());
	  //  }
        super.onSaveInstanceState(outState);
    }
    
    public void onToggleClicked(View view) {
        // Is the toggle on?
        boolean on = ((ToggleButton) view).isChecked();
        
        if (on) {
            // Enable vibrate
        } else {
            // Disable vibrate
        }
    }
    
    //implement the date time picker methods
	@Override
	public void onSet(Calendar calendarSelected, Date dateSelected, int year,
			String monthFullName, String monthShortName, int monthNumber,
			int date, String weekDayFullName, String weekDayShortName,
			int hour24, int hour12, int min, int sec, String AM_PM) 
	 {
		mycalendar_date = calendarSelected;
		DateTimePicker.calendar_date = calendarSelected;
		//textView.setText(calendarSelected.toLocaleString());
		//DateTimePicker.calendar_date.setTime(calendarSelected);
	 }

	public void onCancel() 
	{
		Log.d("datetimepickerdialog", "canceled");
	}
	
	private void fillData(Uri uri) {
	    String[] projection = { BPTable1.COLUMN_DATE,BPTable1.COLUMN_TIME, BPTable1.COLUMN_SITE, 
	    		BPTable1.COLUMN_POSITION, BPTable1.COLUMN_SYSTOLIC, BPTable1.COLUMN_DIASTOLIC, 
	    		BPTable1.COLUMN_HEARTRATE, BPTable1.COLUMN_WEIGHT, BPTable1.COLUMN_ISARRHYTHMIA, 
	    		BPTable1.COLUMN_FORANALYSIS, BPTable1.COLUMN_COMMENT };
	    Cursor cursor = getContentResolver().query(uri, projection, null, null,
	        null);
	    if (cursor != null) {
	      cursor.moveToFirst();
	      String columndate = cursor.getString(cursor
	          .getColumnIndexOrThrow(BPTable1.COLUMN_DATE));
	      
	      String columntime = cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_TIME));
	      

	      /*
	      for (int i = 0; i < mCategory.getCount(); i++) {

	        String s = (String) mCategory.getItemAtPosition(i);
	        if (s.equalsIgnoreCase(category)) {
	          mCategory.setSelection(i);
	        }
	      }   */

	      //fill in 11 columns      
	      /*
	      String strdate = null;
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
	      if (DateTimePicker.calendar_date != null) {
	      strdate = sdf.format(DateTimePicker.calendar_date.getTime());
	      }   */

	      
	      //mBodyText.setText(
	      //cursor.getString(cursor.getColumnIndexOrThrow(BPTable.COLUMN_TIME)) = calendar_date;
	      mSite.setSelection(Integer.parseInt(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_SITE))));
	      
	      mPosition.setSelection(Integer.parseInt(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_POSITION))));
	      
	      mSystolic.setText(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_SYSTOLIC)));
	      mDiastolic.setText(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_DIASTOLIC)));
	      mPulse.setText(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_HEARTRATE)));
	      mWeight.setText(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_WEIGHT)));
	      
	      boolean temp=false; 
	      if (cursor.getString(cursor.getColumnIndexOrThrow(BPTable1.COLUMN_ISARRHYTHMIA)).equals("y")){
	             temp = true;
	      } else {
	    	     temp = false;
	      }		      
	      mArrhythmia.setSelected(temp);  
	      
	      boolean temp1=false; 
	      if (cursor.getString(cursor.getColumnIndexOrThrow(BPTable1.COLUMN_FORANALYSIS)).equals("y")){
	             temp1 = true;
	      } else {
	    	     temp1 = false;
	      }
	      mAnalysis.setSelected(temp1);  
	      
	      
	      mComment.setText(cursor.getString(cursor
		          .getColumnIndexOrThrow(BPTable1.COLUMN_COMMENT)));
	      

	      // Always close the cursor
	      cursor.close();
	    }
	  }
	
	 @Override
	  protected void onPause() {
	    super.onPause();
	    saveState();
	  }   

	  private void saveState() {
		    Site = (String) mSite.getSelectedItem().toString();
		    Site_position = mSite.getSelectedItemPosition();
		    Position = (String) mPosition.getSelectedItem().toString();
		    Position_position = mPosition.getSelectedItemPosition();
		    Systolic = mSystolic.getText().toString();
		    Diastolic = mDiastolic.getText().toString();
		    Pulse = mPulse.getText().toString();
		    Weight = 0;
		    
		   // Toast.makeText(FirstTab.this, "Site is "+Site +"Position is "+ Position,
			  //      Toast.LENGTH_LONG).show();
		    
		    
		    
		    if (!mWeight.getText().toString().equals("")) {
		        Weight = Integer.parseInt(mWeight.getText().toString());
		    }
		  
		    
		    
		    if (mLb_Kg.isPressed()) {
		    	kg_to_lb = 2.20462;		    	
		    }		    
		    //String Lb_Kg = mLb_Kg.getText().toString();
		    
		    //String Arrhythmia = "n";
		    if (mArrhythmia.isPressed()) {	    
		       Arrhythmia = "y";
		    }
		    
		    Comment = mComment.getText().toString();
		    
		    //String Analysis = "n";
		    if (mArrhythmia.isPressed()) {	    
		    	Analysis = "y";
		    }
		    
		    //String Analysis = mAnalysis.getText().toString();
		    // Only save if either summary or description
		    // is available
		    
		    if (Systolic.length() == 0 || Diastolic.length() == 0 ||
			    	Pulse.length() == 0 || (mWeight.getText().toString()).length() == 0 ||
			    			datetimepicked == 0) {  //DateTimePicker.calendar_date == null) {
			    Toast.makeText(FirstTab.this, "Please enter all the data",
				        Toast.LENGTH_LONG).show();
			      return;
			    }



            /*
		    ContentValues values = new ContentValues();
		    
		      //String strdate1 = null;
		      //String strdate2 = null;
		      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		      SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
		      if (DateTimePicker.calendar_date != null) {
		      //if ( mycalendar_date != null) {
		         strdate1 = sdf1.format(DateTimePicker.calendar_date.getTime());
		         strdate2 = sdf2.format(DateTimePicker.calendar_date.getTime());
		      } 
			   // Toast.makeText(FirstTab.this, "entered date is "+strdate1,
				//        Toast.LENGTH_LONG).show();
			    
			   // Toast.makeText(FirstTab.this, "entered time is "+strdate2,
				 //       Toast.LENGTH_LONG).show();
			    
			    
		    values.put(BPTable1.COLUMN_DATE, strdate1);
		    values.put(BPTable1.COLUMN_TIME, strdate2);
		    
		    values.put(BPTable1.COLUMN_SITE, Site);
		    values.put(BPTable1.COLUMN_POSITION, Position);
		    values.put(BPTable1.COLUMN_SYSTOLIC, Systolic);
		    values.put(BPTable1.COLUMN_DIASTOLIC, Diastolic);
		    values.put(BPTable1.COLUMN_HEARTRATE, Pulse);
		    values.put(BPTable1.COLUMN_WEIGHT,("" + Math.round(Weight * kg_to_lb)));
		    values.put(BPTable1.COLUMN_ISARRHYTHMIA,Arrhythmia);
		    values.put(BPTable1.COLUMN_FORANALYSIS, Analysis);
		    values.put(BPTable1.COLUMN_COMMENT, Comment);

		    if (bpUri == null) {
		      // New blood pressure
			    //Toast.makeText(FirstTab.this, "when bpUri == null, bpUri is "+bpUri,
				 //       Toast.LENGTH_LONG).show();
		    	bpUri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
		    } else {
			   // Toast.makeText(FirstTab.this, "when bpUri != null, bpUri is "+bpUri,
				  //      Toast.LENGTH_LONG).show();
		      // Update blood pressure
		      //getContentResolver().update(bpUri, values, null, null);
		    }        */
		    
		    Toast.makeText(FirstTab.this, "Input saved for future use",
			        Toast.LENGTH_LONG).show();
		  }
	  
	  private void saveStateIntoDatabase() {
	    Site = (String) mSite.getSelectedItem().toString();
	    Site_position = mSite.getSelectedItemPosition();
	    Position = (String) mPosition.getSelectedItem().toString();
	    Position_position = mPosition.getSelectedItemPosition();
	    Systolic = mSystolic.getText().toString();
	    Diastolic = mDiastolic.getText().toString();
	    Pulse = mPulse.getText().toString();
	    Weight = 0;
	    
	   // Toast.makeText(FirstTab.this, "Site is "+Site +"Position is "+ Position,
		  //      Toast.LENGTH_LONG).show();
	    
	    
	    
	    if (!mWeight.getText().toString().equals("")) {
	        Weight = Integer.parseInt(mWeight.getText().toString());
	    }
	  
	    
	    
	    if (mLb_Kg.isPressed()) {
	    	kg_to_lb = 2.20462;		    	
	    }		    
	    //String Lb_Kg = mLb_Kg.getText().toString();
	    
	    //String Arrhythmia = "n";
	    if (mArrhythmia.isPressed()) {	    
	       Arrhythmia = "y";
	    }
	    
	    Comment = mComment.getText().toString();
	    
	    //String Analysis = "n";
	    if (mArrhythmia.isPressed()) {	    
	    	Analysis = "y";
	    }
	    
	    //String Analysis = mAnalysis.getText().toString();
	    // Only save if either summary or description
	    // is available
	    
	    if (Systolic.length() == 0 || Diastolic.length() == 0 ||
		    	Pulse.length() == 0 || (mWeight.getText().toString()).length() == 0 ||
		    			datetimepicked == 0) {  //DateTimePicker.calendar_date == null) {
		    Toast.makeText(FirstTab.this, "Please enter all the data",
			        Toast.LENGTH_LONG).show();
		      return;
		    }




	    ContentValues values = new ContentValues();
	    
	      //String strdate1 = null;
	      //String strdate2 = null;
	      SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
	      SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
	      if (DateTimePicker.calendar_date != null) {
	      //if ( mycalendar_date != null) {
	         strdate1 = sdf1.format(DateTimePicker.calendar_date.getTime());
	         strdate2 = sdf2.format(DateTimePicker.calendar_date.getTime());
	      } 
		   // Toast.makeText(FirstTab.this, "entered date is "+strdate1,
			//        Toast.LENGTH_LONG).show();
		    
		   // Toast.makeText(FirstTab.this, "entered time is "+strdate2,
			 //       Toast.LENGTH_LONG).show();
		    
		    
	    values.put(BPTable1.COLUMN_DATE, strdate1);
	    values.put(BPTable1.COLUMN_TIME, strdate2);
	    
	    values.put(BPTable1.COLUMN_SITE, Site);
	    values.put(BPTable1.COLUMN_POSITION, Position);
	    values.put(BPTable1.COLUMN_SYSTOLIC, Systolic);
	    values.put(BPTable1.COLUMN_DIASTOLIC, Diastolic);
	    values.put(BPTable1.COLUMN_HEARTRATE, Pulse);
	    values.put(BPTable1.COLUMN_WEIGHT,("" + Math.round(Weight * kg_to_lb)));
	    values.put(BPTable1.COLUMN_ISARRHYTHMIA,Arrhythmia);
	    values.put(BPTable1.COLUMN_FORANALYSIS, Analysis);
	    values.put(BPTable1.COLUMN_COMMENT, Comment);

	    if (bpUri == null) {
	      // New blood pressure
		    //Toast.makeText(FirstTab.this, "when bpUri == null, bpUri is "+bpUri,
			 //       Toast.LENGTH_LONG).show();
	    	bpUri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
	    } else {
		   // Toast.makeText(FirstTab.this, "when bpUri != null, bpUri is "+bpUri,
			  //      Toast.LENGTH_LONG).show();
	      // Update blood pressure
	      //getContentResolver().update(bpUri, values, null, null);
	    }
	    
	    Toast.makeText(FirstTab.this, "Result saved",
		        Toast.LENGTH_LONG).show();
	  }

	  private void makeToast() {
	    Toast.makeText(FirstTab.this, "Please enter all the data",
	        Toast.LENGTH_LONG).show();
	  }
	  
	
	
    
	
}