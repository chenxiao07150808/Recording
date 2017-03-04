package cn.edu.gdmec.s07150808.recording;



import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;

import java.util.UUID;

public class RecordActivity extends SingFragmentActivity {

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
      Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if(fragment == null){
            fragment=new RecordFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

    }*/ /*public static final String EXTRA_RECORD_ID="cn.edu.gdmec.s07150808.recording.record_id";*/

    private  static final String EXTRA_RECORD_ID="cn.edu.gdmec.s07150808.recording.record_id";
    public static Intent newIntent(Context packageContext , UUID recordId){
           Intent intent = new Intent(packageContext,RecordActivity.class);
            intent.putExtra(EXTRA_RECORD_ID,recordId);
            return intent;
    }
    @Override
    protected Fragment createFragment(){
      /*  return new RecordFragment();*/
        UUID recordId = (UUID) getIntent().getSerializableExtra(EXTRA_RECORD_ID);
        return RecordFragment.newInstance(recordId);
    }
}
