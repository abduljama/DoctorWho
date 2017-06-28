package com.abduljama.doctorwho;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppointmentsFragment extends Fragment {

    RecyclerView recyclerView;
    AppointmentsAdapter adapter;
    List<AppointmentsModel> appointmentsList;

    public AppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_appointments, container, false);

        recyclerView = (RecyclerView) x.findViewById(R.id.recycler_view2);
        adapter = new AppointmentsAdapter(getActivity(), prepareCategories());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        return x;

    }

    private List<AppointmentsModel> prepareCategories() {
        appointmentsList = new ArrayList<>();

        appointmentsList.add(new AppointmentsModel("Dr.Kumal Shah ", "Nairobi Hospital", " 28/06/2017", "15:00hrs"));
        appointmentsList.add(new AppointmentsModel("Dr.Kumal Shah ", "Nairobi Hospital", " 27/06/2017", "15:00hrs"));
        appointmentsList.add(new AppointmentsModel("Dr.Kumal Shah ", "Nairobi Hospital", " 28/06/2017", "15:00hrs"));
        appointmentsList.add(new AppointmentsModel("Dr.Kumal Shah ", "Nairobi Hospital", " 28/06/2017", "15:00hrs"));

        return appointmentsList;
    }
}
