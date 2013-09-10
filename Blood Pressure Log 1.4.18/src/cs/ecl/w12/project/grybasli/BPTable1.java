package cs.ecl.w12.project.grybasli;


import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class BPTable1 {

	  // Database table
	  public static final String TABLE_BP = "BPTable1";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_DATE = "date";
	  public static final String COLUMN_TIME = "time";
	  public static final String COLUMN_SITE = "site";
	  public static final String COLUMN_POSITION = "position";	  
	  public static final String COLUMN_SYSTOLIC = "systolic";
	  public static final String COLUMN_DIASTOLIC = "diastolic";
	  public static final String COLUMN_HEARTRATE = "heartrate";
	  public static final String COLUMN_WEIGHT = "weight";  //"lb" is the default for weight
	  public static final String COLUMN_ISARRHYTHMIA = "isarrhythmia";
	  public static final String COLUMN_FORANALYSIS = "foranalysis";
	  public static final String COLUMN_COMMENT = "comment";
	  
	  

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE = "create table " 
	      + TABLE_BP
	      + "(" 
	      + COLUMN_ID + " integer primary key autoincrement, " 
	      + COLUMN_DATE + " text not null, " 
	      + COLUMN_TIME + " text not null, "       
	      + COLUMN_SITE + " text not null, "
	      + COLUMN_POSITION + " text not null, "
	      + COLUMN_SYSTOLIC + " text not null, "
	      + COLUMN_DIASTOLIC + " text not null, "
	      + COLUMN_HEARTRATE + " text not null, "
	      + COLUMN_WEIGHT + " text not null, "
	      + COLUMN_ISARRHYTHMIA + " text null, "
	      + COLUMN_FORANALYSIS + " text  null, "
	      + COLUMN_COMMENT + " text null"
	      + ");";

	  public static void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  public static void onUpgrade(SQLiteDatabase database, int oldVersion,
	      int newVersion) {
	    Log.w(BPTable1.class.getName(), "Upgrading database from version "
	        + oldVersion + " to " + newVersion
	        + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_BP);
	    onCreate(database);
	  }
	} 

