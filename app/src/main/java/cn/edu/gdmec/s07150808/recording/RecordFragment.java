package cn.edu.gdmec.s07150808.recording;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by chen on 2017/3/2.
 */
public class RecordFragment extends Fragment{
          private static final String ARE_RECORD_ID="record_id";
           private static final String DIALOG_DATE = "DialogDate";
          private final int REQUEST_DATE = 0;
    private Record mRecord;
    private EditText mTitlField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    public static RecordFragment newInstance(UUID recordId){
        Bundle args = new Bundle();
        args.putSerializable(ARE_RECORD_ID,recordId);
        RecordFragment fragment =new RecordFragment();
        fragment.setArguments( args);
        return  fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date)intent.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mRecord.setDate(date);
            mDateButton.setText(mRecord.getDate().toString());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* mRecord = new Record();*/
      /*  UUID recordId = (UUID) getActivity().getIntent().getSerializableExtra(RecordActivity.EXTRA_RECORD_ID);*/
           UUID recordId = (UUID) getArguments().getSerializable(ARE_RECORD_ID);
           mRecord=RecordLab.get(getActivity()).getRecord(recordId);

    }

    @Override
    public void onPause() {
        super.onPause();
      RecordLab.get(getActivity()).updateRecord(mRecord);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record,container,false);
        mTitlField = (EditText) v.findViewById(R.id.record_title);
              mTitlField.setText(mRecord.getTitle());
        mTitlField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton= (Button) v.findViewById(R.id.record_data);
        mDateButton.setText(mRecord.getDate().toString());
     /*   mDateButton.setEnabled(false);*/
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager =getFragmentManager();
              /*  DatePickerFragment datePickerFragment = new DatePickerFragment();*/
               DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(mRecord.getDate());
                datePickerFragment.setTargetFragment(RecordFragment.this,REQUEST_DATE);
                datePickerFragment.show(fragmentManager,DIALOG_DATE);
            }
        });
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.record_solve);
             mSolvedCheckBox.setChecked(mRecord.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mRecord.setSolved(isChecked);
            }
        });
        return v;
        }
}
