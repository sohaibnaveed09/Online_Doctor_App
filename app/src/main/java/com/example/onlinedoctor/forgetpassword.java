 package com.example.onlinedoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

 public class forgetpassword extends AppCompatActivity {
     Button btn1;
     TextInputLayout phone_no;
     ProgressBar progressBar;
     String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        progressBar=findViewById(R.id.progress);
        btn1=findViewById(R.id.f_button);
        phone_no =findViewById(R.id.f_phone);
        progressBar.setVisibility(View.GONE);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatPhone()) {
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                number=phone_no.getEditText().getText().toString().trim();

                Query checkUser= FirebaseDatabase.getInstance().getReference("doctor").orderByChild("contact_no").equalTo(number);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            phone_no.setError(null);
                            phone_no.setErrorEnabled(false);

                            Intent intent=new Intent(getApplicationContext(),VerifyOTP.class);
                            intent.putExtra("phone",number);
                            startActivity(intent);
                            finish();

                            progressBar.setVisibility(View.GONE);
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            phone_no.setError("No such User");
                            phone_no.requestFocus();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

    public void verifyPhonenumber(View view){




    }

     private boolean validatPhone() {
         String val = phone_no.getEditText().getText().toString();
         if (val.isEmpty()) {
             phone_no.setError("Field Cannot be empty");
             return false;
         } else {
             phone_no.setError(null);
             phone_no.setErrorEnabled(false);
             return true;
         }
     }


 }