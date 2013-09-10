package cs.ecl.w12.project.grybasli;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class TabBarExample extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);
        
        /* TabHost will have Tabs */
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        /* TabSpec used to create a new tab. 
         * By using TabSpec only we can able to setContent to the tab.
         * By using TabSpec setIndicator() we can set name to tab. */
        
        /* tid1 is firstTabSpec Id. Its used to access outside. */
        TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        TabSpec fourthTabSpec = tabHost.newTabSpec("tid1");
        
        TextView txtTab1 = new TextView(this);
        txtTab1.setText("New");    //getString(R.string.tab_new));
        //txtTab1.setPadding(8, 9, 8, 9);
        txtTab1.setTextColor(Color.RED);
        txtTab1.setTextSize(14);
        int height_in_pixels = txtTab1.getLineCount() * txtTab1.getLineHeight(); //approx height text
        txtTab1.setHeight(height_in_pixels);
        //txtTab1.setHeight(10);
        //txtTab1.setTypeface(localTypeface1);
        txtTab1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        txtTab1.setBackgroundResource(R.drawable.tab_new);
        
        TextView txtTab2 = new TextView(this);
        txtTab2.setText("Total");    //getString(R.string.tab_new));
        //txtTab2.setPadding(8, 9, 8, 9);
        txtTab2.setTextColor(Color.RED);
        txtTab2.setTextSize(14);
        height_in_pixels = txtTab2.getLineCount() * txtTab1.getLineHeight(); //approx height text
        txtTab2.setHeight(height_in_pixels);
        //txtTab2.setTypeface(localTypeface1);
        txtTab2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        txtTab2.setBackgroundResource(R.drawable.tab_total);
        
        TextView txtTab3 = new TextView(this);
        txtTab3.setText("History");    //getString(R.string.tab_new));
        //txtTab3.setPadding(8, 9, 8, 9);
        txtTab3.setTextColor(Color.RED);
        txtTab3.setTextSize(14);
        height_in_pixels = txtTab3.getLineCount() * txtTab1.getLineHeight(); //approx height text
        txtTab3.setHeight(height_in_pixels);
        //txtTab3.setTypeface(localTypeface1);
        txtTab3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        txtTab3.setBackgroundResource(R.drawable.tab_history);
        
        TextView txtTab4 = new TextView(this);
        txtTab4.setText("Analysis");    //getString(R.string.tab_new));
        //txtTab4.setPadding(8, 9, 8, 9);
        txtTab4.setTextColor(Color.RED);
        txtTab4.setTextSize(14);
        height_in_pixels = txtTab4.getLineCount() * txtTab1.getLineHeight(); //approx height text
        txtTab4.setHeight(height_in_pixels);
        //txtTab4.setTypeface(localTypeface1);
        txtTab4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        txtTab4.setBackgroundResource(R.drawable.tab_analysis);
        
        
        /* TabSpec setIndicator() is used to set name for the tab. */
        /* TabSpec setContent() is used to set content for a particular tab. */
        firstTabSpec.setIndicator(txtTab1).setContent(new Intent(this,FirstTab.class));
        secondTabSpec.setIndicator(txtTab2).setContent(new Intent(this,bpsOverviewActivity.class));
        thirdTabSpec.setIndicator(txtTab3).setContent(new Intent(this,SecondTab.class));
        fourthTabSpec.setIndicator(txtTab4).setContent(new Intent(this,AnalysisActivity.class));
        
        /* Add tabSpec to the TabHost to display. */
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        tabHost.addTab(thirdTabSpec);
        tabHost.addTab(fourthTabSpec);
        
    }
}
