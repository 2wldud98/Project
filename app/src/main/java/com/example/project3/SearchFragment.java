package com.example.project3;

import android.app.AlertDialog;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


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

    final static String TAG = "XML";

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

    private ListView scholarshipListView;
    private ScholarshipListAdapter adapter;
    private List<Scholarship> scholarshipList;


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

        scholarshipListView = (ListView) getView().findViewById(R.id.scholarshipListView);
        scholarshipList = new ArrayList<Scholarship>();
        adapter = new ScholarshipListAdapter(getContext().getApplicationContext(), scholarshipList);
        scholarshipListView.setAdapter(adapter);

        Button searchButton = (Button) getView().findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new BackgroundTask().execute();
            }
        });

    }


    class BackgroundTask extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... voids) {

            try{
                AssetManager assetManager = getActivity().getAssets();

                InputStream is = assetManager.open("jsons/data1.json");
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(isr);

                StringBuffer stringBuffer = new StringBuffer();
                String line = bufferedReader.readLine();
                while(line!=null){
                   stringBuffer.append(line+"\n");
                   line = bufferedReader.readLine();
                }
                bufferedReader.close();
                is.close();

                String jsonData = stringBuffer.toString();
                /*JSONArray jsonArray = new JSONArray(jsonData);
                return jsonArray.toString().trim();*/
                return jsonData;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {

            /*try{
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchFragment.this.getContext());
                dialog = builder.setMessage(result)
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();

            }catch (Exception e){
                e.printStackTrace();
            }*/

            try{
                scholarshipList.clear();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("scholarship");
                int count = 0;

                int scholarID; //장학금 번호
                String scholarGroup;//구분(교내,교외)
                String scholarAgency;//운영기관명
                String scholarType;//장학금유형
                String scholarName;//장학명
                String scholarUniv;//대학구분
                String scholarGrade;//학과
                String scholarBenefit;//장학혜택
                String scholarStandard;//성적기준
                String scholarLink;//링크


                while(count<jsonArray.length())
                {
                    JSONObject object = jsonArray.getJSONObject(count);

                    scholarID = object.getInt("scholarID"); //장학금 번호
                    scholarGroup = object.getString("scholarGroup");//구분(교내,교외)
                    scholarAgency= object.getString("scholarAgency");//운영기관명
                    scholarType= object.getString("scholarType");//장학금유형
                    scholarName= object.getString("scholarName");//장학명
                    scholarUniv= object.getString("scholarUniv");//대학구분
                    scholarGrade= object.getString("scholarGrade");//학과
                    scholarBenefit= object.getString("scholarBenefit");//장학혜택
                    scholarStandard= object.getString("scholarStandard");//성적기준
                    scholarLink= object.getString("scholarLink");//링크

                    /*StringBuffer sb = new StringBuffer();
                    sb.append(scholarID+ " " +scholarStandard);
                    Log.d(TAG, sb.toString());*/

                    Scholarship scholarship = new Scholarship(scholarID, scholarGroup, scholarAgency, scholarType, scholarName, scholarUniv, scholarGrade, scholarBenefit, scholarStandard, scholarLink);
                    scholarshipList.add(scholarship);
                    count++;
                }
                if(count == 0)
                {
                    AlertDialog dialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchFragment.this.getActivity());
                    dialog = builder.setMessage("조회된 강의가 없습니다.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                }
                adapter.notifyDataSetChanged();


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }
}
