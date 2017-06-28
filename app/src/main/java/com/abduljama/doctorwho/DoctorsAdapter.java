package com.abduljama.doctorwho;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by abduljama on 6/28/17.
 */

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.MyViewHolder>  {


    private Context mContext;
    private List<DoctorsModel> doctorsList;
    DoctorsModel doctorsModel;

    String defaultImage = "http://api.androidhive.info/gcm/panda.jpg";

    public DoctorsAdapter(Context mContext, List<DoctorsModel> doctorsList) {
        this.mContext = mContext;
        this.doctorsList = doctorsList;
    }

    public void setItemList(List<DoctorsModel> data){
        this.doctorsList = data;
        notifyItemRangeChanged(0,data.size());
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.people_list_item, parent, false);

       MyViewHolder myViewHolder = null;
        myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        doctorsModel = doctorsList.get(position);
        holder.username.setText(doctorsModel.getName());
        holder.profession.setText(doctorsModel.getSpeciality());
        holder.location.setText(doctorsModel.getHospital());
        // Picasso.with(mContext).load(favouriteModel.getImageUrl()).into(holder.imageView);
        Glide.with(mContext).load(defaultImage).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, profession, location;
        public ImageView imageView, overflow;
        public Button undoButton;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView)view.findViewById(R.id.username);
            profession =(TextView)view.findViewById(R.id.txtprofession);
            location =(TextView)view.findViewById(R.id.location);
            imageView =(ImageView)view.findViewById(R.id.profile);
            undoButton = (Button) itemView.findViewById(R.id.undo_button);
        }
    }
}
