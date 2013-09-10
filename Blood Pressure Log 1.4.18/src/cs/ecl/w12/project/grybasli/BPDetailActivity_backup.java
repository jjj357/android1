package cs.ecl.w12.project.grybasli;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import cs.ecl.w12.project.grybasli.MyContentProvider;
import cs.ecl.w12.project.grybasli.BPTable1;

/*
 * TodoDetailActivity allows to enter a new todo item 
 * or to change an existing
 */
public class BPDetailActivity_backup extends Activity {
  private Spinner mCategory;
  private EditText mTitleText;
  private EditText mBodyText;

  private Uri bpUri;

  @Override
  protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.bp_edit);

    mCategory = (Spinner) findViewById(R.id.category);
    mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
    mBodyText = (EditText) findViewById(R.id.todo_edit_description);
    Button confirmButton = (Button) findViewById(R.id.todo_edit_button);

    Bundle extras = getIntent().getExtras();

    // Check from the saved Instance
    bpUri = (bundle == null) ? null : (Uri) bundle
        .getParcelable(MyContentProvider.CONTENT_ITEM_TYPE);

    // Or passed from the other activity
    if (extras != null) {
      bpUri = extras
          .getParcelable(MyContentProvider.CONTENT_ITEM_TYPE);

      fillData(bpUri);
    }

    confirmButton.setOnClickListener(new View.OnClickListener() {
      public void onClick(View view) {
        if (TextUtils.isEmpty(mTitleText.getText().toString())) {
          makeToast();
        } else {
          setResult(RESULT_OK);
          finish();
        }
      }

    });
  }

  private void fillData(Uri uri) {
    String[] projection = { BPTable1.COLUMN_ID,
        BPTable1.COLUMN_DATE, BPTable1.COLUMN_TIME };
    Cursor cursor = getContentResolver().query(uri, projection, null, null,
        null);
    if (cursor != null) {
      cursor.moveToFirst();
      String category = cursor.getString(cursor
          .getColumnIndexOrThrow(BPTable1.COLUMN_ID));                        ////////////////<-----------------

      for (int i = 0; i < mCategory.getCount(); i++) {

        String s = (String) mCategory.getItemAtPosition(i);
        if (s.equalsIgnoreCase(category)) {
          mCategory.setSelection(i);
        }
      }

      mTitleText.setText(cursor.getString(cursor
          .getColumnIndexOrThrow(BPTable1.COLUMN_DATE)));
      mBodyText.setText(cursor.getString(cursor
          .getColumnIndexOrThrow(BPTable1.COLUMN_TIME)));

      // Always close the cursor
      cursor.close();
    }
  }

  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    saveState();
    outState.putParcelable(MyContentProvider.CONTENT_ITEM_TYPE, bpUri);
  }

  @Override
  protected void onPause() {
    super.onPause();
    saveState();
  }

  private void saveState() {
    String category = (String) mCategory.getSelectedItem();
    String summary = mTitleText.getText().toString();
    String description = mBodyText.getText().toString();

    // Only save if either summary or description
    // is available

    if (description.length() == 0 && summary.length() == 0) {
      return;
    }

    ContentValues values = new ContentValues();
    values.put(BPTable1.COLUMN_ID, category);
    values.put(BPTable1.COLUMN_DATE, summary);
    values.put(BPTable1.COLUMN_TIME, description);

    if (bpUri == null) {
      // New todo
      bpUri = getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
    } else {
      // Update todo
      getContentResolver().update(bpUri, values, null, null);
    }
  }

  private void makeToast() {
    Toast.makeText(BPDetailActivity_backup.this, "Please maintain a detail",
        Toast.LENGTH_LONG).show();
  }
} 

