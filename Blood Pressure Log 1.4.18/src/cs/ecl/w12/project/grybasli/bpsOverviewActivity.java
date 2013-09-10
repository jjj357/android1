package cs.ecl.w12.project.grybasli;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import cs.ecl.w12.project.grybasli.MyContentProvider;
import cs.ecl.w12.project.grybasli.BPTable1;

/*
 * BPsOverviewActivity displays the existing bp items
 * in a list
 * 
 * You can create new ones via the ActionBar entry "Insert"
 * You can delete existing ones via a long press on the item
 */

public class bpsOverviewActivity extends ListActivity implements
    LoaderManager.LoaderCallbacks<Cursor> {
  private static final int ACTIVITY_CREATE = 0;
  private static final int ACTIVITY_EDIT = 1;
  private static final int DELETE_ID = Menu.FIRST + 1;
  // private Cursor cursor;
  private SimpleCursorAdapter adapter;

  
/** Called when the activity is first created. */

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.bp_list);
    this.getListView().setDividerHeight(2);
    fillData();
    registerForContextMenu(getListView());
  }

  
  // Create the menu based on the XML defintion
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.listmenu, menu);
    return true;
  } 

  // Reaction to the menu selection
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case R.id.insert:
      createBP();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }    

  @Override
  public boolean onContextItemSelected(MenuItem item) {
    switch (item.getItemId()) {
    case DELETE_ID:
      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
          .getMenuInfo();
      Uri uri = Uri.parse(MyContentProvider.CONTENT_URI + "/"
          + info.id);
      getContentResolver().delete(uri, null, null);
      fillData();
      return true;
    }
    return super.onContextItemSelected(item);
  }

  private void createBP() {
    Intent i = new Intent(this, FirstTab.class);
    startActivityForResult(i, ACTIVITY_CREATE);
  }

  /*
  // Opens the second activity if an entry is clicked
  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    Intent i = new Intent(this, FirstTab.class);
    Uri bpUri = Uri.parse(MyContentProvider.CONTENT_URI + "/" + id);
    i.putExtra(MyContentProvider.CONTENT_ITEM_TYPE, bpUri);

    // Activity returns an result if called with startActivityForResult
    startActivityForResult(i, ACTIVITY_EDIT);
  }   */

  // Called with the result of the other activity
  // requestCode was the origin request code send to the activity
  // resultCode is the return code, 0 is everything is ok
  // intend can be used to get data
  @Override
  protected void onActivityResult(int requestCode, int resultCode,
      Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
  }

  private void fillData() {

    // Fields from the database (projection)
    // Must include the _id column for the adapter to work
    //String[] from = new String[] { BPTable1.COLUMN_ID };
	  String[] from = new String[] {BPTable1.COLUMN_DATE,BPTable1.COLUMN_SITE, BPTable1.COLUMN_TIME, 
	    	BPTable1.COLUMN_POSITION, BPTable1.COLUMN_SYSTOLIC, BPTable1.COLUMN_ISARRHYTHMIA, BPTable1.COLUMN_DIASTOLIC, BPTable1.COLUMN_FORANALYSIS,
	   		BPTable1.COLUMN_HEARTRATE, BPTable1.COLUMN_COMMENT,BPTable1.COLUMN_WEIGHT };
    // Fields on the UI to which we map
    int[] to = new int[] { R.id.label1, R.id.label2, R.id.label3, R.id.label4, R.id.label5, 
    		R.id.label6, R.id.label7, R.id.label8, R.id.label9, R.id.label10, R.id.label11 };
    /*
    
	  // Get the base URI for the BPTable table in the Contacts content provider.
	  Uri uri = MyContentProvider.CONTENT_URI;
	  // Make the query.
	  Cursor cur = context.getContentResolver().query(uri,  ///managedQuery(uri,  //   
	  from, // Which columns to return
	  null, // Which rows to return (all rows)
	  null, // Selection arguments (none)
	  // Put the results in ascendingorder by name
	  BPTable1.COLUMN_ID + " ASC");
	  
	 */

    getLoaderManager().initLoader(0, null, this);
    adapter = new SimpleCursorAdapter(this, R.layout.bp_row, null, from,
        to, 0);

    setListAdapter(adapter);
  }

  @Override
  public void onCreateContextMenu(ContextMenu menu, View v,
      ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);
    menu.add(0, DELETE_ID, 0, R.string.menu_delete);
  }

  // Creates a new loader after the initLoader () call
  @Override
  public Loader<Cursor> onCreateLoader(int id, Bundle args) {
    //String[] projection = { BPTable1.COLUMN_ID, BPTable1.COLUMN_DATE,BPTable1.COLUMN_TIME };
	  String[] projection = new String[] { BPTable1.COLUMN_ID,BPTable1.COLUMN_DATE,BPTable1.COLUMN_SITE, BPTable1.COLUMN_TIME, 
		    	BPTable1.COLUMN_POSITION, BPTable1.COLUMN_SYSTOLIC, BPTable1.COLUMN_ISARRHYTHMIA, BPTable1.COLUMN_DIASTOLIC, BPTable1.COLUMN_FORANALYSIS,
		   		BPTable1.COLUMN_HEARTRATE, BPTable1.COLUMN_COMMENT,BPTable1.COLUMN_WEIGHT};
    CursorLoader cursorLoader = new CursorLoader(this,
        MyContentProvider.CONTENT_URI, projection, null, null, null);
    return cursorLoader;
  }

  @Override
  public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
    adapter.swapCursor(data);
  }

  @Override
  public void onLoaderReset(Loader<Cursor> loader) {
    // data is not available anymore, delete reference
    adapter.swapCursor(null);
  }

} 

