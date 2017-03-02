package cn.edu.gdmec.s07150808.recording;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class SingFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_fragment);
        FragmentManager fm =getSupportFragmentManager();
        Fragment fragment =fm.findFragmentById(R.id.fragment_container);
        if(fragment==null){
            /*fragment = createFragment();*/
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}