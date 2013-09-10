package cs.ecl.w12.project.grybasli;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import cs.ecl.w12.project.grybasli.DateTimePicker.ICustomDateTimeListener;

public class PickDateTimeActivity extends Activity implements ICustomDateTimeListener 
{
	private TextView textView;
	private DateTimePicker dateTimePicker;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickdatetime);
        
        //textView = (TextView) findViewById(R.id.textview);
        //if (FirstTab.datetimepicked == 1) {
        //	Intent i = new Intent(this, FirstTab.class);
        //	startActivity(i);
        //}
        dateTimePicker = new DateTimePicker(PickDateTimeActivity.this, this);
        dateTimePicker.set24HourFormat(true);
        dateTimePicker.showDialog();
    }
    
	@Override
	public void onSet(Calendar calendarSelected, Date dateSelected, int year,
			String monthFullName, String monthShortName, int monthNumber,
			int date, String weekDayFullName, String weekDayShortName,
			int hour24, int hour12, int min, int sec, String AM_PM) 
	 {
		//textView.setText(dateSelected.toLocaleString());
		FirstTab.mycalendar_date = calendarSelected;
		DateTimePicker.calendar_date = calendarSelected;
		FirstTab.datetimepicked = 1;
		
    	Intent i = new Intent(this, TabBarExample.class);
    	startActivity(i);
	 }

	@Override
	public void onCancel() 
	{
		Log.d("datetimepickerdialog", "canceled");
	}
}
