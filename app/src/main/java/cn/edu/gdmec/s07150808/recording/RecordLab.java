package cn.edu.gdmec.s07150808.recording;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by chen on 2017/3/2.
 */
public class RecordLab {
    private static RecordLab sRecordLab;
    private List <Record> mRecords;
    public static RecordLab get(Context context){
        if(sRecordLab==null){
            sRecordLab=new RecordLab(context);
        }
        return sRecordLab;
    }
    private RecordLab(Context context){
        mRecords = new ArrayList<>();

    }
    public List<Record> getRecord(){
        return mRecords;
    }
    public Record getId(UUID id){
        for(Record record:mRecords){
            if (record.getid().equals(id)) {
                return record;
            }
        }
        return null;
    }
}
