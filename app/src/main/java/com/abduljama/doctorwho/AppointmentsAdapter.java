package com.abduljama.doctorwho;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by abduljama on 6/28/17.
 */

public class AppointmentsAdapter extends RecyclerView.Adapter<AppointmentsAdapter.MyViewHolder> {


    private Context mContext;
    private List<AppointmentsModel> appointList;
    AppointmentsModel appointmentsModel;


    public AppointmentsAdapter(Context mContext, List<AppointmentsModel> doctorsList) {
        this.mContext = mContext;
        this.appointList = doctorsList;
    }



    public void setItemList(List<AppointmentsModel> data){
        this.appointList = data;
        notifyItemRangeChanged(0,data.size());
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.app_item_list, parent, false);

        MyViewHolder myViewHolder = null;
        myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        appointmentsModel = appointList.get(position);
        holder.doctor.setText(appointmentsModel.getDoctor());
        holder.hospital.setText(appointmentsModel.getHospital());
        holder.timeDate.setText( "Date :"+appointmentsModel.getDate() +  " Time :"+ appointmentsModel.getTime());
        // Picasso.with(mContext).load(favouriteModel.getImageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(defaultImage).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return appointList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView doctor , hospital, timeDate;


        public MyViewHolder(View view) {
            super(view);
            doctor = (TextView)view.findViewById(R.id.username);
             hospital =(TextView)view.findViewById(R.id.txtprofession);
            timeDate =(TextView)view.findViewById(R.id.location);
        }
    }
}
