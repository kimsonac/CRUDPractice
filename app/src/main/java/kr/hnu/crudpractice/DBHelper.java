package kr.hnu.crudpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "TestDB.db", null, 2);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE test ( _id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { // db 지우고 다시 하고 싶으면 버전 수동으로 바꿔 주기
        onCreate(db);
    }
}
