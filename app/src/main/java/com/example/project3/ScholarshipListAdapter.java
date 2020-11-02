package com.example.project3;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;




import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ScholarshipListAdapter extends BaseAdapter {

    private Context context;
    private java.util.List<Scholarship> scholarshipList;

    public ScholarshipListAdapter(Context context, List<Scholarship> scholarshipList) {
        this.context = context;
        this.scholarshipList = scholarshipList;
    }

    @Override
    public int getCount() {
        return scholarshipList.size();
    }

    @Override
    public Object getItem(int i) {
        return scholarshipList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v =View.inflate(context,R.layout.scholarship, null);
        TextView scholarGroup =(TextView) v.findViewById(R.id.scholarGroup);
        TextView scholarAgency =(TextView) v.findViewById(R.id.scholarAgency);
        TextView scholarType =(TextView) v.findViewById(R.id.scholarType);
        TextView scholarName =(TextView) v.findViewById(R.id.scholarName);

        scholarGroup.setText(scholarshipList.get(i).getScholarGroup());
        scholarAgency.setText(scholarshipList.get(i).getScholarAgency());
        scholarType.setText(scholarshipList.get(i).getScholarType());
        scholarName.setText(scholarshipList.get(i).getScholarName());

        v.setTag(scholarshipList.get(i).getScholarID());
        return v;
    }




}
