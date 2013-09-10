package cs.ecl.w12.project.grybasli;


import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import cs.ecl.w12.project.grybasli.MyDemoView;

//import javax.swing.text.View;

import android.net.Uri;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.database.Cursor;

import android.view.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import cs.ecl.w12.project.grybasli.BPTable1;

import org.afree.chart.*;
import org.afree.data.category.*;
import org.afree.data.general.DefaultPieDataset;
import org.afree.data.xy.*;
import org.afree.data.*;
import org.afree.chart.renderer.category.*;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.afree.chart.plot.*;
import org.afree.util.*;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.demo.DemoView;

import org.afree.graphics.GradientColor;
import org.afree.graphics.SolidColor;

import android.content.Context;
import android.graphics.*;


import org.afree.chart.ChartFactory;
import org.afree.chart.AFreeChart;
import org.afree.chart.demo.DemoView;
import org.afree.chart.plot.PiePlot;
import org.afree.chart.title.TextTitle;
import org.afree.data.general.DefaultPieDataset;
import org.afree.data.general.PieDataset;
import org.afree.graphics.geom.Font;
import android.graphics.Typeface;


import org.afree.data.time.*;

import org.afree.data.xy.XYDataset;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;
//import org.afree.ui.ApplicationFrame;
import org.afree.ui.RectangleInsets;
import org.afree.ui.RefineryUtilities;


import android.view.View;



public class SecondTab extends Activity {

	@SuppressWarnings("unused")
	private ArrayList<String> list_date =  new ArrayList<String>();  
	@SuppressWarnings("unused")
	private ArrayList<String> list_time =  new ArrayList<String>();  
	@SuppressWarnings("unused")
	private ArrayList<String> list_diastolic =  new ArrayList<String>();  
	@SuppressWarnings("unused")
	private ArrayList<String> list_systolic =  new ArrayList<String>();  
	@SuppressWarnings("unused")
	private ArrayList<String> list_pulse =  new ArrayList<String>();    
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        /*
        ScrollView sv = new ScrollView(this);
        LinearLayout layout = new LinearLayout(this);
        // Define the LinearLayout's characteristics
        layout.setGravity(Gravity.CENTER);
        layout.setOrientation(LinearLayout.VERTICAL);
        sv.addView(layout);

        // Set generic layout parameters
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);



        myDemoView myview = new myDemoView(this);
        //myview.setLayoutParams(params);
        myview.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,500));
        
        layout.addView(myview);  //, 0);  //params); // Of course, this too

        setContentView(sv);
        
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout);
        //setContentView(getDataFromDatabaseAndDraw());
         
         
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Cupcake", new Integer(40));
        dataset.setValue("Donut", new Integer(5));
        dataset.setValue("Eclair", new Integer(10));
        dataset.setValue("Froyo", new Integer(25));
        dataset.setValue("Gingerbread", new Integer(20));
        dataset.setValue("Honeycomb", new Integer(50));
        AFreeChart chart = ChartFactory.createPieChart("aaa", dataset,
                                                       true, false, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Typeface.NORMAL, 8));
        plot.setCircular(false);            
       plot.setLabelLinksVisible(false); 
        
        PieChartDemo01View spcv = (PieChartDemo01View) findViewById(R.id.spcv);
        spcv.setChart(chart);  */
        setContentView(R.layout.activity_history);
        
        /*
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Cupcake", new Integer(40));
        dataset.setValue("Donut", new Integer(5));
        dataset.setValue("Eclair", new Integer(10));
        dataset.setValue("Froyo", new Integer(25));
        dataset.setValue("Gingerbread", new Integer(20));
        dataset.setValue("Honeycomb", new Integer(50));
        AFreeChart chart = ChartFactory.createPieChart("abc", dataset,
                                                       true, false, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Typeface.NORMAL, 8));
        plot.setCircular(false);            
        plot.setLabelLinksVisible(false);  

       // SamplePieChartView spcv = (SamplePieChartView) findViewById(R.id.spcv);
       // spcv.setChart(chart);  */
        
        
        
        MyDemoView dv = (MyDemoView) findViewById(R.id.dv);
        dv.setChart(((MyDemoView) dv).createChart(((MyDemoView) dv).createDataset(this)));
        //dv.setChart(chart);
        
         
    }

}



    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_history, menu);
        return true;
    }  */
    

