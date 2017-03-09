package cn.edu.gdmec.s07150808.recording;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chen on 2017/3/4.
 */
public class RecordListFragment extends Fragment{


    private RecyclerView mRecordRecyclerView;
    private static String SIVED_SUBTITLE_VISIBLE="subtitle";
    private RecordAdapter mAdater;
    private boolean mSubtitleVisible;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_record_list,container,false);

        mRecordRecyclerView = (RecyclerView) view.findViewById(R.id.record_recyler_view);

        mRecordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SIVED_SUBTITLE_VISIBLE);
        }
        updateUI();

        return view;
    }

   @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SIVED_SUBTITLE_VISIBLE,mSubtitleVisible);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_record_list,menu);
       MenuItem subtitle = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubtitleVisible){
            subtitle.setTitle(R.string.hide_subtitle);
        }else{
            subtitle.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_record:
                Record record = new Record();
                RecordLab.get(getActivity()).addRecord(record);
                Intent intent = RecordPagerActivity.newIntent(getActivity(),record.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible=!mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void updateSubtitle(){
        RecordLab recordLab = RecordLab.get(getActivity());

        int recordCount = recordLab.getRecords().size();
        String subtitle = getString(R.string.subtitle_format,recordCount);


        if(!mSubtitleVisible){
             subtitle = null;
         }
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
       appCompatActivity.getSupportActionBar().setSubtitle(subtitle);
    }
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private class RecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       public TextView mTitleTextView;
      /*  private TextView mTitleTextView;*/
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Record mRecord;
        public RecordHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            /*mTitleTextView= (TextView) itemView;*/
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_record_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_record_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_record_solved_check_box);
        }

        public void bindRecord(Record record) {
            mRecord = record;
            mTitleTextView.setText(mRecord.getTitle());
            mDateTextView.setText(mRecord.getDate().toString());
            mSolvedCheckBox.setChecked(mRecord.isSolved());
        }

        @Override
        public void onClick(View v) {
           /* Intent intent = RecordActivity.newIntent(getActivity(),mRecord.getId());*/
            Intent intent = RecordPagerActivity.newIntent(getActivity(),mRecord.getId());
            startActivity(intent);

        }
    }
    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        private List<Record> mRecords;
     public RecordAdapter(List<Record>records){
         mRecords = records;
     }

        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_record,parent,false);

            return new RecordHolder(view);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            Record record = mRecords.get(position);
         /*   holder.mTitleTextView.setText(record.getTitle());*/
           holder.bindRecord(record);
        }

        @Override
        public int getItemCount() {
            return mRecords.size();
        }
        public void setRecords(List<Record> recordList){
            mRecords =recordList;
        }
    }
    private void updateUI(){
        RecordLab recordLab =RecordLab.get(getActivity());

        List<Record> records = recordLab.getRecords();

        if(mAdater == null) {
            mAdater = new RecordAdapter(records);
            mRecordRecyclerView.setAdapter(mAdater);
        }else{
            mAdater.setRecords(records);
            mAdater.notifyDataSetChanged();
        }
       updateSubtitle();

    }

}
