package cn.edu.gdmec.s07150808.recording;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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

   private RecordAdapter mAdater;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_record_list,container,false);

        mRecordRecyclerView = (RecyclerView) view.findViewById(R.id.record_recyler_view);

        mRecordRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }
    private class RecordHolder extends RecyclerView.ViewHolder{
       public TextView mTitleTextView;
      /*  private TextView mTitleTextView;*/
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Record mRecord;
        public RecordHolder(View itemView) {
            super(itemView);
            mTitleTextView= (TextView) itemView;
           /* mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_record_title_text_view);*/
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_record_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_record_solved_check_box);
        }

        public void bindRecord(Record record) {
            mRecord = record;
            mTitleTextView.setText(mRecord.getTitle());
            mDateTextView.setText(mRecord.getDate().toString());
            mSolvedCheckBox.setChecked(mRecord.isSolved());
        }
    }
    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        private List<Record> mRecord;
     public RecordAdapter(List<Record>records){
         mRecord = records;
     }

        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);

            return new RecordHolder(view);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            Record record = mRecord.get(position);
            holder.mTitleTextView.setText(record.getTitle());
           /* holder.bindRecord(record);*/
        }

        @Override
        public int getItemCount() {
            return mRecord.size();
        }
    }
    private void updateUI(){
        RecordLab recordLab =RecordLab.get(getActivity());

        List<Record> records = recordLab.getRecords();

        mAdater= new RecordAdapter(records);

        mRecordRecyclerView.setAdapter(mAdater);
    }
}
