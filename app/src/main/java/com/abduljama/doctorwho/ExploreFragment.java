package com.abduljama.doctorwho;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {

    RecyclerView recyclerView;
    ExploreAdapter adapter;
    private List<ExploreModel> categoryList;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View x = inflater.inflate(R.layout.fragment_explore, container, false);

        recyclerView = (RecyclerView)x.findViewById(R.id.recycler_view);
        adapter = new ExploreAdapter(getActivity(), prepareCategories());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration(5);
        recyclerView.addItemDecoration(spaceItemDecoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(getActivity(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent in  = new Intent( getActivity() , DoctorsActivity.class);
                        ExploreModel exploreModel = categoryList.get(position);
                        Bundle mBundle = new Bundle();
                        mBundle.putString("cat_name",exploreModel.getCategoryname());
                        in.putExtras(mBundle);
                        startActivity(in);
                    }
                }));
        return  x;
    }


    private List<ExploreModel> prepareCategories() {
        categoryList = new ArrayList<>();
        final int[] covers = new int[]{
                R.drawable.big_image,
                R.drawable.big_image,
                R.drawable.big_image,
                R.drawable.big_image,
                R.drawable.big_image
        };
        final String [] titles  = getResources().getStringArray(R.array.categories);

        for (int i =0 ; i < covers.length;  i++){
            categoryList.add( new ExploreModel( titles[i] ,covers[i]));
        }

        return categoryList;

    }

}
