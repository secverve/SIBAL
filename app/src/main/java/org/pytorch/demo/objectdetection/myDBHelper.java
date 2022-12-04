package org.pytorch.demo.objectdetection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDBHelper extends SQLiteOpenHelper {
    public myDBHelper(Context context) {
        super(context, "groupDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE userDB ( " +
                        "id VARCHAR(40) PRIMARY KEY, pass VARCHAR(20), " +
                        "gender CHAR(10), phonenum VARCHAR(20), address VARCHAR(50)" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS userDB");
    }
}
