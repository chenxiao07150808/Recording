package cn.edu.gdmec.s07150808.recording.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.edu.gdmec.s07150808.recording.database.RecordDbSchema.RecordTable;

/**
 * Created by chen on 2017/3/5.
 */
public class RecordBaseHelder extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "recordBase.db";
    public RecordBaseHelder(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table "+RecordTable.NANE+"("+"_id integer primary key autoincrement, "+ RecordTable.Cols.UUID+", "+
       RecordTable.Cols.TITLE+", "+RecordTable.Cols.DATE+", "+RecordTable.Cols.SOLVED+")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
