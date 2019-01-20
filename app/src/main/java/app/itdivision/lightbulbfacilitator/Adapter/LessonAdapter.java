package app.itdivision.lightbulbfacilitator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.itdivision.lightbulbfacilitator.Model.Lesson;
import app.itdivision.lightbulbfacilitator.R;
import app.itdivision.lightbulbfacilitator.UpdateCourse;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder> {

    private Context mContext;
    private List<Lesson> mData;

    public LessonAdapter(Context mContext, List<Lesson> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public LessonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_item_update, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.MyViewHolder myViewHolder, int i) {
        final int x = i;
        myViewHolder.lessonName.setText(mData.get(i).getLessonName());
        myViewHolder.lessonDesc.setText(mData.get(i).getLessonDesc());
        myViewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String lessonName = mData.get(x).getLessonName();
                final String lessonDesc = mData.get(x).getLessonDesc();
                mBtnEditLessonClickListener.onBtnEditLessonClicked(lessonName, lessonDesc);
            }
        });
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String lessonName = mData.get(x).getLessonName();
                final String lessonDesc = mData.get(x).getLessonDesc();
                mBtnDeleteLessonClickListener.onBtnDeleteLessonClicked(lessonName,lessonDesc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView lessonName;
        TextView lessonDesc;
        ImageButton btnEdit;
        ImageButton btnDelete;

        public MyViewHolder(View itemView){
            super(itemView);
            lessonName = (TextView) itemView.findViewById(R.id.tv_lessonName);
            lessonDesc = (TextView) itemView.findViewById(R.id.tv_lessonDesc);
            btnEdit = (ImageButton) itemView.findViewById(R.id.btn_edit);
            btnDelete = (ImageButton) itemView.findViewById(R.id.btn_delete);
        }
    }

    public interface OnBtnEditLessonClickListener{
        void onBtnEditLessonClicked(String LessonName, String LessonDesc);
    }

    private OnBtnEditLessonClickListener mBtnEditLessonClickListener;

    public void setOnBtnEditLessonClickListener(OnBtnEditLessonClickListener listener){
        mBtnEditLessonClickListener = listener;
    }

    public interface OnBtnDeleteLessonClickListener{
        void onBtnDeleteLessonClicked(String LessonName, String LessonDesc);
    }

    private OnBtnDeleteLessonClickListener mBtnDeleteLessonClickListener;

    public void setOnBtnDeleteLessonClickListener(OnBtnDeleteLessonClickListener listener){
        mBtnDeleteLessonClickListener = listener;
    }

}
