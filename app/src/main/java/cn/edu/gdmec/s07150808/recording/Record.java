package cn.edu.gdmec.s07150808.recording;

import android.provider.ContactsContract;

import java.util.UUID;
import java.util.*;
/**
 * Created by chen on 2017/3/2.
 */
public class Record {
    private UUID mId ;
    private String mTitile;
    private Date mDate;
    private boolean mSolved;
    public Record(){
     this(UUID.randomUUID());
    }
    public Record(UUID uuid){
        mId=UUID.randomUUID();
        mDate= new Date();
    }
    public UUID getId(){
        return mId;
    }
    public String getTitle(){return mTitile;}
    public void setTitle(String title){this.mTitile=title;}
    public Date getDate(){return mDate;}
    public void setDate(Date date){mDate=date; }
    public boolean isSolved(){return mSolved;}
    public void setSolved(boolean solved){mSolved=solved;}


}
