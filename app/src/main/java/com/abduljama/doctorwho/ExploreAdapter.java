package com.abduljama.doctorwho;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by abduljama on 6/28/17.
 */

public class ExploreAdapter extends  RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {
    private Context mContext;
    private List<ExploreModel> categoryList;

    public ExploreAdapter(Context mContext, List<ExploreModel> albumList) {
        this.mContext = mContext;
        this.categoryList = albumList;
    }


    public void setItemList(List<ExploreModel> data){
        this.categoryList = data;
        notifyItemRangeChanged(0,data.size());

    }

    @Override
    public ExploreAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ExploreAdapter.MyViewHolder holder, int position) {
        ExploreModel categoryModel = categoryList.get(position);
        holder.title.setText(categoryModel.getCategoryname());
        Glide.with(mContext).load(categoryModel.getThumbnail()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.category_name);
            imageView = (ImageView)view.findViewById(R.id.profile);
        }
    }


}
