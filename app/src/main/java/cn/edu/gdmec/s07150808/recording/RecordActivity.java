package cn.edu.gdmec.s07150808.recording;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.os.Bundle;

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

    }*/
    @Override
    protected Fragment createFragment(){
        return new RecordFragment();
    }
}
