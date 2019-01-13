package app.itdivision.lightbulbfacilitator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeoutException;

import app.itdivision.lightbulbfacilitator.Model.Course;
import app.itdivision.lightbulbfacilitator.R;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.MyViewHolder> {

    private Context mContext;
    private List<Course> mData;

    public BalanceAdapter(Context mContext, List<Course> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public BalanceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.card_item_balance, viewGroup, false);
        return new BalanceAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BalanceAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.courseName.setText(mData.get(i).getCourseName());
        myViewHolder.courseCategory.setText(mData.get(i).getCourseCategory());
        String price = "IDR "+ Integer.toString(mData.get(i).getCoursePrice());
        myViewHolder.coursePrice.setText(price);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView courseName;
        TextView courseCategory;
        TextView coursePrice;

        public MyViewHolder(View itemView){
            super(itemView);
            courseName = (TextView)itemView.findViewById(R.id.course_name);
            courseCategory = (TextView)itemView.findViewById(R.id.course_div);
            coursePrice = (TextView) itemView.findViewById(R.id.course_price);
        }

    }
}
