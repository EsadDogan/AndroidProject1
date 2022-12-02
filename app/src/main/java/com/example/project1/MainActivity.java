package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private Button btnImage , btnRegister;
    private EditText lblName, lblSurname , lblEmail, lblPassword, lblPasswordAgain;
    private TextView CorrectChbRequest,CorrectPasswordAgainRequest,CorrectPasswordRequest,CorrectEmailRequest,CorrectSurNameRequest,CorrectNameRequest;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private CheckBox checkBox;
    private ConstraintLayout parent;
    private RadioButton radioMale , radioFemale , radioOther;
    private ScrollView screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);
        lblName = findViewById(R.id.lblName);
        lblEmail = findViewById(R.id.lblEmaıl);
        lblSurname = findViewById(R.id.lblSurname);
        lblPassword = findViewById(R.id.lblPassword);
        lblPasswordAgain = findViewById(R.id.lblPasswordAgain);
        CorrectChbRequest = findViewById(R.id.CorrectChbRequest);
        CorrectPasswordAgainRequest = findViewById(R.id.CorrectPasswordAgainRequest);
        CorrectPasswordRequest = findViewById(R.id.CorrectPasswordRequest);
        CorrectEmailRequest = findViewById(R.id.CorrectEmailRequest);
        CorrectSurNameRequest = findViewById(R.id.CorrectSurNameRequest);
        CorrectNameRequest = findViewById(R.id.CorrectNameRequest);
        parent = findViewById(R.id.parent);
        checkBox = findViewById(R.id.checkboxAgree);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);
        screen = findViewById(R.id.screen);

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "How you dare to change Master Yoda's picture!! Go away!", Toast.LENGTH_SHORT).show();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correction()) {

                    if(checkBox.isChecked())
                    {
                        showSnackBar();
                    }

                    else{

                        Toast.makeText(MainActivity.this, "You have to check the agreement to proceed.", Toast.LENGTH_SHORT).show();


                    }


                }

            }
        });

    }


    private void showSnackBar()
    {
        CorrectNameRequest.setVisibility(View.GONE);
        CorrectSurNameRequest.setVisibility(View.GONE);
        CorrectPasswordRequest.setVisibility(View.GONE);
        CorrectPasswordAgainRequest.setVisibility(View.GONE);
        CorrectEmailRequest.setVisibility(View.GONE);
        CorrectChbRequest.setVisibility(View.GONE);

        Snackbar.make(parent, "New person added", Snackbar.LENGTH_INDEFINITE).setAction("Close", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lblName.setText("");
                lblSurname.setText("");
                lblPassword.setText("");
                lblEmail.setText("");
                lblPasswordAgain.setText("");
                checkBox.setChecked(false);
                radioMale.setChecked(true);
                radioFemale.setChecked(false);
                radioOther.setChecked(false);
               // screen.arrowScroll(0);




            }
        }).show();
    }


    private boolean correction()
    {
         if (lblName.getText().toString().equals("")) {

             CorrectNameRequest.setVisibility(View.VISIBLE);
             return false;
         }


         if (lblSurname.getText().toString().equals("")) {

             CorrectSurNameRequest.setVisibility(View.VISIBLE);
             return false;
         }


         if (lblEmail.getText().toString().equals("")) {

             CorrectEmailRequest.setVisibility(View.VISIBLE);
             return false;
         }




         if (lblPassword.getText().toString().equals("")) {

             CorrectPasswordRequest.setVisibility(View.VISIBLE);
             return false;
         }


         if (lblPasswordAgain.getText().toString().equals("")) {

             CorrectPasswordAgainRequest.setVisibility(View.VISIBLE);
             return false;
         }

         if (!lblPasswordAgain.getText().toString().equals(lblPassword.getText().toString())) {

             CorrectPasswordAgainRequest.setText("Passwords have to be same.");
             CorrectPasswordAgainRequest.setVisibility(View.VISIBLE);
             return false;
         }

         else
         {
             return true;
         }
    }


}