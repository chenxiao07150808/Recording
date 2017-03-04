package cn.edu.gdmec.s07150808.recording;

import android.support.v4.app.Fragment;

/**
 * Created by chen on 2017/3/4.
 */
public class RecordListActivity extends SingFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new RecordListFragment();
    }
}
