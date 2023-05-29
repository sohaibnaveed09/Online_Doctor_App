package com.example.onlinedoctor;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


public class ProfileFragment extends Fragment {

    TextInputLayout full_name,email,phoneNo;
    TextView fullName_label,usernameLabel,ex,qu;

    String _USERNAME, _EXP,_EMAIL,_PHONENO,_QUA;

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_profile, container, false);

        ex=view.findViewById(R.id.exp);
        qu=view.findViewById(R.id.qua);

        full_name =view.findViewById(R.id.profile_fullname);
        email =view.findViewById(R.id.profile_email);
        phoneNo =view.findViewById(R.id.profile_phone);

        fullName_label =view.findViewById(R.id.full_name_label);
        usernameLabel=view.findViewById(R.id.user_label);

        showAllUserData();

        return view;
    }
    private void showAllUserData() {

        Intent intent = getActivity().getIntent();
        _USERNAME=intent.getStringExtra("name1");
        _EXP =intent.getStringExtra("exp1");
        _EMAIL=intent.getStringExtra("email1");
        _PHONENO=intent.getStringExtra("phoneNo1");
        _QUA=intent.getStringExtra("qua1");

        fullName_label.setText(_USERNAME);
        usernameLabel.setText(_EXP);
        full_name.getEditText().setText(_USERNAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);

        ex.setText(_EXP);
        qu.setText(_QUA);


    }
}