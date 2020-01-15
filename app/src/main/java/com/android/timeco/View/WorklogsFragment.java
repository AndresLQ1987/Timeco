package com.android.timeco.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.timeco.MainActivity;
import com.android.timeco.Model.Worklog;
import com.android.timeco.R;
import com.android.timeco.ViewModel.WorklogsViewModel;

import java.util.ArrayList;

public class WorklogsFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    WorklogsViewModel worklogsViewModel;

    /**
     * Elements of Fragment
     */
    RecyclerView rv_worklogs;
    private Button btnBack;

    public WorklogsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorklogsFragment.
     */
    public static WorklogsFragment newInstance(String param1, String param2) {
        WorklogsFragment fragment = new WorklogsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_layout = inflater.inflate(R.layout.fragment_worklogs, container, false);

        //TODO Methods gets information to ViewModel
        worklogsViewModel = new WorklogsViewModel();
        rv_worklogs = ui_layout.findViewById(R.id.rv_worklogs);
        btnBack = ui_layout.findViewById(R.id.btn_w_Back);

        rv_worklogs.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<Worklog> listWorklogs = worklogsViewModel.getWorklogs(MainActivity.currentUser);
        RecyclerAdapterWorklogs adapterWorklogs = new RecyclerAdapterWorklogs(listWorklogs);
        rv_worklogs.setAdapter(adapterWorklogs);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new HomeFragment()).commit();
            }
        });

        return ui_layout;
    }

    //TODO Recycler View
    /**
     * Class Adapter for RecyclerView defines how to show the work logs in a list
     */
    public class RecyclerAdapterWorklogs extends RecyclerView.Adapter<RecyclerAdapterWorklogs.WorklogHolder> {

        private ArrayList<Worklog> worklogs;

        public RecyclerAdapterWorklogs(ArrayList<Worklog> worklogs) {
            this.worklogs = worklogs;
        }

        @NonNull
        @Override
        public RecyclerAdapterWorklogs.WorklogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();

            return new WorklogHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapterWorklogs.WorklogHolder holder, int position) {

            Worklog worklog = worklogs.get(position);

            holder.initDate.setText(worklog.getDateInit().toString());
            holder.endDate.setText(worklog.getDateEnd().toString());
            holder.breakHours.setText(worklog.convertIntoHours(worklog.getRestTime()));
            holder.totalHours.setText(worklog.convertIntoHours(worklog.getWorkedTime()));
        }

        @Override
        public int getItemCount() {
            return worklogs.size();
        }

        /**
         * Inner Class of Recycler View to define Holder work logs
         */
        public class WorklogHolder extends RecyclerView.ViewHolder {

            TextView initDate;
            TextView endDate;
            TextView breakHours;
            TextView totalHours;

            public WorklogHolder(LayoutInflater layoutInflater, ViewGroup parent) {
                super(layoutInflater.inflate(R.layout.worklogholder, parent,false));

                initDate = itemView.findViewById(R.id.d_init);
                endDate = itemView.findViewById(R.id.d_end);
                breakHours = itemView.findViewById(R.id.txt_breakhours);
                totalHours = itemView.findViewById(R.id.txt_totalHours);
            }
        }
    }
}
