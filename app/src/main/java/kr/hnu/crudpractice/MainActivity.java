package kr.hnu.crudpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String selectMsg = "SELECT _id, text FROM test";
    DBHelper dbHelper;
    TextView mResult;
    EditText mInsert, mDelete, mUpdate;
    SQLiteDatabase readDB, writeDB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        readDB = dbHelper.getReadableDatabase();
        writeDB = dbHelper.getWritableDatabase();
        mResult = findViewById(R.id.result);
    }

    public void setResult() {
        cursor = readDB.rawQuery(selectMsg, null);
        String Result = "";

        while (cursor.moveToNext()) {
            String number = cursor.getString(0);
            String data = cursor.getString(1);
            Result += (number + " = " + data + "\n");
        }

        if(Result.length() == 0) {
            mResult.setText("No Data!");
        }
        else {
            mResult.setText(Result);
        }

        cursor.close();
    }

    public void mOnClick(View v) {
        ContentValues row = new ContentValues();

        switch (v.getId()) {
            case R.id.insertB:
                mInsert = findViewById(R.id.insertE);
                String text = mInsert.getText().toString();
                row.put("text", text); // text 속성에, mInsert로 받은 데이터
                writeDB.insert("test", null, row); // 위의 row를 null(자동)번째
                setResult();
                break;

            case R.id.deleteB:
                mDelete = findViewById(R.id.deleteE);
                String mData = mDelete.getText().toString();

                writeDB.execSQL("DELETE FROM test WHERE _id =" + mData);
                setResult();
                break;

            case R.id.updateB:
                mUpdate = findViewById(R.id.updateE);
                String mKey = mUpdate.getText().toString();

                row.put("text", "UPDATE");
                writeDB.update("test", row, "_id = " + mKey, null);
                setResult();
                break;
        }

    }


    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}