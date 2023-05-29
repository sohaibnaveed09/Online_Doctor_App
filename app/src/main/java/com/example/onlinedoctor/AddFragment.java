package com.example.onlinedoctor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.BaseProgressIndicator;

import java.util.ArrayList;

public class AddFragment extends Fragment {

    ArrayList<Bemari> arrayList;
    private String[] txt;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add, container, false);


        

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        data();
        recyclerView=view.findViewById(R.id.recyclerview_bemari);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        AdapterforFragmentAdd adapterforFragmentAdd=new AdapterforFragmentAdd(getContext(),arrayList);
        recyclerView.setAdapter(adapterforFragmentAdd);
        adapterforFragmentAdd.notifyDataSetChanged();

    }

    private void data() {

        arrayList=new ArrayList<>();

        txt = new String[]{
            getString(R.string.B1),
            getString(R.string.B2),
            getString(R.string.B3),
            getString(R.string.B4),
            getString(R.string.B5),
            getString(R.string.B6),
        };

        for (int i=0;i<txt.length;i++){
            Bemari bemari=new Bemari(txt[i]);
            arrayList.add(bemari);
        }

    }
}