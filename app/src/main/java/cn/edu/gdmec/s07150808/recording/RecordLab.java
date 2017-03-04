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
    /*List<Object> 定义名= new ArrayList<Object>();*/
    private List <Record> mRecords;
    public static RecordLab get(Context context){
        if(sRecordLab==null){
            sRecordLab=new RecordLab(context);
        }
        return sRecordLab;
    }
    private RecordLab(Context context){
        mRecords = new ArrayList<>();
        for(int i=0 ;i<100;i++){
            Record record = new Record();
            record.setTitle("记录 #"+i);
            record.setSolved(i%2==0);
            mRecords.add(record);
        }

    }
    public List<Record> getRecords(){
        return mRecords;
    }
    public Record getRecord(UUID id){
        for(Record record:mRecords){
            if (record.getId().equals(id)) {
                return record;
            }
        }
        return null;
    }

}
