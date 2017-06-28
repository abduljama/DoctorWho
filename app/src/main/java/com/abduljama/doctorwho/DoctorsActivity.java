package com.abduljama.doctorwho;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DoctorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DoctorsAdapter  adapter;
    List<DoctorsModel> doctorsList;
    String category;

    String [] titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent in = getIntent();
        Bundle extras = in.getExtras();
        if ( !extras.equals(null)) {
            category = in.getStringExtra("cat_name");
        }
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view1);
        adapter = new DoctorsAdapter(getApplicationContext(),prepareCategories());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(getApplicationContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent in = new Intent(getApplicationContext() , DoctorProfileActivity.class);
                        DoctorsModel model = doctorsList.get(position);
                        Bundle myBundle = new Bundle();
                        myBundle.putString("Name" , model.getName());
                        myBundle.putString("Hospital", model.getHospital());
                        myBundle.putString("speciality",model.getSpeciality());
                        in.putExtras(myBundle);
                        startActivity(in);

                    }
                }));



    }

    private List<DoctorsModel> prepareCategories() {
       doctorsList = new ArrayList<>();
        final int[] covers = new int[]{
                R.drawable.big_image,
                R.drawable.big_image,
                R.drawable.big_image,
                R.drawable.big_image,
                R.drawable.big_image
        };

        if ( category.equals("Oncology")) {
            titles = getResources().getStringArray(R.array.Oncology);
        }else  if ( category.equals("Demartology")){
            titles = getResources().getStringArray(R.array.Demartology);
        }else  if ( category.equals("Cardiology") ){
            titles = getResources().getStringArray(R.array.Oncology);
        }else  if  ( category.equals("Gastroentology")){
            titles = getResources().getStringArray(R.array.Gastroentology);
        }else if ( category.equals("Orthopedics")){
            titles = getResources().getStringArray(R.array.Orthopedics);
        }


        final String [] profession  = new String []{
                " Oncologist  ",
                " Sample 1 ",
                " Sample 1 ",
                " Sample 1 ",
                " Sample 1 "
        };

        final String [] hospital  = new String []{
                "  Nairobi Hospital  ",
                " Sample 1 ",
                " Sample 1 ",
                " Sample 1 ",
                " Sample 1 "
        };
        for (int i =0 ; i < covers.length;  i++){
            doctorsList.add( new DoctorsModel( titles[i] ,profession[i], hospital[i] , covers[i]));
        }
        return doctorsList;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent in = new Intent(getApplication(), MainActivity.class);
                startActivity(in);
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }
}
