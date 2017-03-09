package cn.edu.gdmec.s07150808.recording;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.edu.gdmec.s07150808.recording.database.RecordBaseHelder;
import cn.edu.gdmec.s07150808.recording.database.RecordCuesorWrapper;
import cn.edu.gdmec.s07150808.recording.database.RecordDbSchema;

/**
 * Created by chen on 2017/3/2.
 */
public class RecordLab {
    private static RecordLab sRecordLab;
    /*List<Object> 定义名= new ArrayList<Object>();*/
   /* private List <Record> mRecords;*/
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public static RecordLab get(Context context){
        if(sRecordLab==null){
            sRecordLab=new RecordLab(context);
        }
        return sRecordLab;
    }
    private RecordLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new RecordBaseHelder(mContext).getWritableDatabase();
      /*  mRecords = new ArrayList<>();*/
      /*  for(int i=0 ;i<100;i++){
            Record record = new Record();
            record.setTitle("记录 #"+i);
            record.setSolved(i%2==0);
            mRecords.add(record);
        }*/

    }
   /* public void addRecord(Record record){
       *//* mRecords.add(record);*//*
    }*/
    public List<Record> getRecords(){
       /* return mRecords;*/
       /* return new ArrayList<>();*/
       List<Record>  recordList = new ArrayList<>();
        RecordCuesorWrapper cursorWrapper = queryRecords(null,null);
    try {
        cursorWrapper.moveToFirst();
        while (!cursorWrapper.isAfterLast()) {
            recordList.add(cursorWrapper.getRcord());
            cursorWrapper.moveToNext();
        }
    }finally {
        cursorWrapper.close();
    }
        return recordList;

    }
    public Record getRecord(UUID id){
       /* for(Record record:mRecords){
            if (record.getId().equals(id)) {
                return record;
            }
        }*/
      RecordCuesorWrapper cursorWrapper = queryRecords(RecordDbSchema.RecordTable.Cols.UUID+"=?",new String[]{id.toString()});
        try{
            if(cursorWrapper.getCount() == 0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getRcord();
        }finally {
            cursorWrapper.close();
        }
    }
    public void updateRecord(Record record){
        String uuidString = record.getId().toString();
        ContentValues contentValues = getContentValues(record);
        mDatabase.update(RecordDbSchema.RecordTable.NANE,contentValues,RecordDbSchema.RecordTable.Cols.UUID+"=?",new String[]{uuidString});

    }
  public static ContentValues getContentValues(Record record){
      ContentValues contentValues = new ContentValues();
      contentValues.put(RecordDbSchema.RecordTable.Cols.UUID,record.getId().toString());
      contentValues.put(RecordDbSchema.RecordTable.Cols.TITLE,record.getTitle().toString());
      contentValues.put(RecordDbSchema.RecordTable.Cols.DATE,record.getDate().toString());
      contentValues.put(RecordDbSchema.RecordTable.Cols.SOLVED,record.isSolved()?1:0);
      return contentValues;
  }
  /*  private Cursor queryRecords(String whereClause ,String[] whereArgs){
       Cursor cursor  = mDatabase.query(RecordDbSchema.RecordTable.NANE,null,whereClause,whereArgs,null,null,null);

        return cursor;
    }*/
    public RecordCuesorWrapper queryRecords(String whereClause,String[]whereArgs){
        Cursor cursor  = mDatabase.query(RecordDbSchema.RecordTable.NANE,null,whereClause,whereArgs,null,null,null);

        return new RecordCuesorWrapper(cursor);

    }
    public void addRecord(Record record){
         ContentValues contentValues = getContentValues(record);
        mDatabase.insert(RecordDbSchema.RecordTable.NANE,null,contentValues);
    }

}
