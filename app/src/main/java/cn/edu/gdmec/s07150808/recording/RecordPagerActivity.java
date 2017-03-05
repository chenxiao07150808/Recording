package cn.edu.gdmec.s07150808.recording;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by chen on 2017/3/5.
 */
public class RecordPagerActivity extends AppCompatActivity {
    private static final String EXTRA_RECORD_ID="cn.edu.gdmec.s07150808.recording.record_id";
    private ViewPager mViewPager;
    private List<Record> mRecordList;

    static Intent newIntent(Context packageContent, UUID recordId){
        Intent intent = new Intent(packageContent,RecordPagerActivity.class);
        intent.putExtra(EXTRA_RECORD_ID,recordId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_pager);
        mViewPager = (ViewPager) findViewById(R.id.activity_view_pager);
        mRecordList = RecordLab.get(this).getRecords();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public int getCount() {
                return mRecordList.size();
            }

            @Override
            public Fragment getItem(int position) {
                Record record = mRecordList.get(position);

                return RecordFragment.newInstance(record.getId());
            }
        });
        for(int i = 0; i<mRecordList.size();i++){
            mViewPager.setCurrentItem(i);
            break;
        }
    }
}
