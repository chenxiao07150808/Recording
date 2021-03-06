package cn.edu.gdmec.s07150808.recording;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by chen on 2017/3/5.
 */
public class DatePickerFragment extends android.support.v4.app.DialogFragment {
      public static final String EXTRA_DATE = "cn.edu.gdmec.s07150808.recording.date";
      private static final String ARG_DATE = "date";
      private DatePicker mDatePicker;
      private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE,date);
          getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,intent);
    }
      public static DatePickerFragment newInstance(Date date){
           Bundle args = new Bundle();
          args.putSerializable(ARG_DATE,date);
          DatePickerFragment datePickerFragment = new DatePickerFragment();
          datePickerFragment.setArguments(args);
          return datePickerFragment;
      }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);

         mDatePicker= (DatePicker) view.findViewById(R.id.dialog_date_picker);
        mDatePicker.init(year,month,day,null);
        return new AlertDialog.Builder(getActivity()).setView(view).setTitle(R.string.date_picker).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();

                Date date1 =new GregorianCalendar(year,month,day).getTime();
                sendResult(Activity.RESULT_OK,date1);




            }
        }).create();
    }
}
