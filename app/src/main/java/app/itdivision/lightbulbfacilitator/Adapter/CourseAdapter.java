package app.itdivision.lightbulbfacilitator.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.itdivision.lightbulbfacilitator.Instance.ActiveIdPassing;
import app.itdivision.lightbulbfacilitator.Model.Course;
import app.itdivision.lightbulbfacilitator.R;
import app.itdivision.lightbulbfacilitator.UpdateCourse;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    private Context mContext;
    private List<Course> mData;

    public CourseAdapter(Context mContext, List<Course> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_item_courses, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder myViewHolder, int i) {
        final int x = i;
        myViewHolder.course_title.setText(mData.get(i).getCourseName());
        myViewHolder.course_category.setText(mData.get(i).getCourseCategory());
        myViewHolder.imgThumb.setImageBitmap(mData.get(i).getThumbnail());
        myViewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateCourse.class);
                ActiveIdPassing activeIdPassing = ActiveIdPassing.getInstance();
                String courseName = mData.get(x).getCourseName();
                String courseCategory = mData.get(x).getCourseCategory();
                activeIdPassing.setActiveCourseCategory(courseCategory);
                activeIdPassing.setActiveCourseName(courseName);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView course_title;
        TextView course_category;
        ImageView imgThumb;
        Button btnUpdate;

        public MyViewHolder(View itemView){
            super(itemView);
            course_title = (TextView) itemView.findViewById(R.id.courseTitle);
            course_category = (TextView) itemView.findViewById(R.id.courseDiv);
            imgThumb = (ImageView) itemView.findViewById(R.id.courseImg);
            btnUpdate = (Button) itemView.findViewById(R.id.btnUpdate);
        }
    }
}
