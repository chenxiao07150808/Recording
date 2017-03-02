package cn.edu.gdmec.s07150808.recording;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by chen on 2017/3/2.
 */
public class RecordFragment extends Fragment{
    private Record mRecord;
    private EditText mTitlField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecord = new Record();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_record,container,false);
        mTitlField = (EditText) v.findViewById(R.id.record_title);
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
        mDateButton.setEnabled(false);
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.record_solve);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mRecord.setSolved(isChecked);
            }
        });
        return v;
        }
}
