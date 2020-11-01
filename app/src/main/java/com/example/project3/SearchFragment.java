package com.example.project3;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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

    private ArrayAdapter agencyAdapter;
    private Spinner agencySpinner;
    private ArrayAdapter typeAdapter;
    private Spinner typeSpinner;

    private String scGroup = "";
    private String scAgency ="";
    private String scType = "";

    @Override
    public void onActivityCreated(Bundle b) {
        super.onActivityCreated(b);

        final RadioGroup schoolGroup = (RadioGroup)getView().findViewById(R.id.schoolGroup);
        agencySpinner = (Spinner)getView().findViewById(R.id.agencySpinner);
        typeSpinner = (Spinner)getView().findViewById(R.id.typeSpinner);

        schoolGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton searchButton = (RadioButton)getView().findViewById(i);
                scGroup = searchButton.getText().toString();

                typeAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.type, android.R.layout.simple_spinner_dropdown_item);
                typeSpinner.setAdapter(typeAdapter);

                if(scGroup.equals("교내"))
                {
                    agencyAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.s_agency, android.R.layout.simple_spinner_dropdown_item);
                    agencySpinner.setAdapter(agencyAdapter);
                }
                else if(scGroup.equals("교외"))
                {
                    agencyAdapter = ArrayAdapter.createFromResource(getActivity(),R.array.oos_agency, android.R.layout.simple_spinner_dropdown_item);
                    agencySpinner.setAdapter(agencyAdapter);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}
