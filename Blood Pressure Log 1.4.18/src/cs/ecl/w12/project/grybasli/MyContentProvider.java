package cs.ecl.w12.project.grybasli;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import cs.ecl.w12.project.grybasli.BPDBHelper;
import cs.ecl.w12.project.grybasli.BPTable1;


public class MyContentProvider extends ContentProvider {

	  // database
	  private BPDBHelper database;

	  // Used for the UriMacher
	  private static final int BPS = 10;
	  private static final int BP_ID = 20;

	  private static final String AUTHORITY = "cs.ecl.w12.project.grybasli";

	  private static final String BASE_PATH = "bps";
	  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	      + "/" + BASE_PATH);

	  public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	      + "/bps";
	  //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	   //   + "/bp";
	  
	  public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
		      + "/bp";

	  public static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH, BPS);
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/*", BP_ID);
	  }

	  @Override
	  public boolean onCreate() {
	    database = new BPDBHelper(getContext());
	    return true;  //false;
	  }

	  @Override
	  public Cursor query(Uri uri, String[] projection, String selection,
	      String[] selectionArgs, String sortOrder) {

	    // Using SQLiteQueryBuilder instead of query() method
	    SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

	    // Check if the caller has requested a column which does not exists
	    checkColumns(projection);

	    // Set the table
	    queryBuilder.setTables(BPTable1.TABLE_BP);

	    int uriType = sURIMatcher.match(uri);
	    switch (uriType) {
	    case BPS:
	      break;
	    case BP_ID:
	      // Adding the ID to the original query
	      queryBuilder.appendWhere(BPTable1.COLUMN_ID + "="
	          + uri.getLastPathSegment());
	      break;
	    default:
	      throw new IllegalArgumentException("in query(),Unknown URI: " + uri);
	    }

	    SQLiteDatabase db = database.getWritableDatabase();
	    Cursor cursor = queryBuilder.query(db, projection, selection,
	        selectionArgs, null, null, sortOrder);
	    // Make sure that potential listeners are getting notified
	    cursor.setNotificationUri(getContext().getContentResolver(), uri);

	    return cursor;
	  }

	  @Override
	  public String getType(Uri uri) {
	    //return null;
		  return uri.toString();
	  }

	  @Override
	  public Uri insert(Uri uri, ContentValues values) {
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsDeleted = 0;
	    long id = 0;
	    switch (uriType) {
	    case BPS:
	      id = sqlDB.insert(BPTable1.TABLE_BP, null, values);
	      break;
	    default:
	      throw new IllegalArgumentException("in insert(),Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return Uri.parse(BASE_PATH + "/" + id);
	  }

	  @Override
	  public int delete(Uri uri, String selection, String[] selectionArgs) {
	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsDeleted = 0;
	    switch (uriType) {
	    case BPS:
	      rowsDeleted = sqlDB.delete(BPTable1.TABLE_BP, selection,
	          selectionArgs);
	      break;
	    case BP_ID:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsDeleted = sqlDB.delete(BPTable1.TABLE_BP,
	        		BPTable1.COLUMN_ID + "=" + id, 
	            null);
	      } else {
	        rowsDeleted = sqlDB.delete(BPTable1.TABLE_BP,
	        		BPTable1.COLUMN_ID + "=" + id 
	            + " and " + selection,
	            selectionArgs);
	      }
	      break;
	    default:
	      throw new IllegalArgumentException("in delete,Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsDeleted;
	  }

	  @Override
	  public int update(Uri uri, ContentValues values, String selection,
	      String[] selectionArgs) {

	    int uriType = sURIMatcher.match(uri);
	    SQLiteDatabase sqlDB = database.getWritableDatabase();
	    int rowsUpdated = 0;
	    switch (uriType) {
	    case BPS:
	      rowsUpdated = sqlDB.update(BPTable1.TABLE_BP, 
	          values, 
	          selection,
	          selectionArgs);
	      break;
	    case BP_ID:
	      String id = uri.getLastPathSegment();
	      if (TextUtils.isEmpty(selection)) {
	        rowsUpdated = sqlDB.update(BPTable1.TABLE_BP, 
	            values,
	            BPTable1.COLUMN_ID + "=" + id, 
	            null);
	      } else {
	        rowsUpdated = sqlDB.update(BPTable1.TABLE_BP, 
	            values,
	            BPTable1.COLUMN_ID + "=" + id 
	            + " and " 
	            + selection,
	            selectionArgs);
	      }
	      break;
	    default:
	      throw new IllegalArgumentException("in update(),Unknown URI: " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return rowsUpdated;
	  }

	  private void checkColumns(String[] projection) {
	    String[] available = { BPTable1.COLUMN_DATE,BPTable1.COLUMN_TIME, BPTable1.COLUMN_SITE, 
	    		BPTable1.COLUMN_POSITION, BPTable1.COLUMN_SYSTOLIC, BPTable1.COLUMN_DIASTOLIC, 
	    		BPTable1.COLUMN_HEARTRATE, BPTable1.COLUMN_WEIGHT, BPTable1.COLUMN_ISARRHYTHMIA, 
	    		BPTable1.COLUMN_FORANALYSIS, BPTable1.COLUMN_COMMENT,
	    		BPTable1.COLUMN_ID };
	    if (projection != null) {
	      HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
	      HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
	      // Check if all columns which are requested are available
	      if (!availableColumns.containsAll(requestedColumns)) {
	        throw new IllegalArgumentException("Unknown columns in projection");
	      }
	    }
	  }

	} 

