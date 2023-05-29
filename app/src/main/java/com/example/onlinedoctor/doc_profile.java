package com.example.onlinedoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class doc_profile extends AppCompatActivity {

    TextInputLayout full_name,email,phoneNo;
    TextView fullName_label,usernameLabel,ex,qu;

    String _USERNAME, _EXP,_EMAIL,_PHONENO,_QUA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_doc_profile);


        ex=findViewById(R.id.exp);
        qu=findViewById(R.id.qua);

        full_name =findViewById(R.id.profile_fullname);
        email =findViewById(R.id.profile_email);
        phoneNo =findViewById(R.id.profile_phone);

        fullName_label =findViewById(R.id.full_name_label);
        usernameLabel=findViewById(R.id.user_label);

        showAllUserData();


    }

    private void showAllUserData() {

        Intent intent = getIntent();
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