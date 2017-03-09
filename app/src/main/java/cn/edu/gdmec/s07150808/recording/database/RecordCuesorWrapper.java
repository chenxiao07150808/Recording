package cn.edu.gdmec.s07150808.recording.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import cn.edu.gdmec.s07150808.recording.Record;

/**
 * Created by chen on 2017/3/8.
 */
public class RecordCuesorWrapper extends CursorWrapper {

    public RecordCuesorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Record getRcord(){
        String uuidString = getString(getColumnIndex(RecordDbSchema.RecordTable.Cols.UUID));
        String title = getString(getColumnIndex(RecordDbSchema.RecordTable.Cols.TITLE));
        Long date = getLong(getColumnIndex(RecordDbSchema.RecordTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(RecordDbSchema.RecordTable.Cols.SOLVED));


         Record record =new Record(UUID.fromString(uuidString));
        record.setTitle(title);
        record.setDate(new Date(date));
        record.setSolved(isSolved != 0);

        return  record;
    }
}
