package com.example.project1.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.project1.R;
import com.example.project1.addContactsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;


public class AddContactFragment extends Fragment {

    public final static String TAG = "addContact";

    private Button btnImage , btnRegister;
    private EditText lblName, lblSurname , lblEmail, lblPassword, lblPasswordAgain;
    private TextView CorrectChbRequest,CorrectPasswordAgainRequest,CorrectPasswordRequest,CorrectEmailRequest,CorrectSurNameRequest,CorrectNameRequest;
    private RadioGroup radioGroup;
    private Spinner spinner;
    private CheckBox checkBox;
    private ConstraintLayout parent;
    private RadioButton radioMale , radioFemale , radioOther;
    private ScrollView scrollView;
    private BottomNavigationView bottomNavigationView;

    public AddContactFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnImage = view.findViewById(R.id.btnPickImage);
        btnRegister = view.findViewById(R.id.btnRegister);
        lblName = view.findViewById(R.id.lblName);
        lblEmail = view.findViewById(R.id.lblEmaıl);
        lblSurname = view.findViewById(R.id.lblSurname);
        lblPassword = view.findViewById(R.id.lblPassword);
        lblPasswordAgain = view.findViewById(R.id.lblPasswordAgain);
        CorrectChbRequest = view.findViewById(R.id.CorrectChbRequest);
        CorrectPasswordAgainRequest = view.findViewById(R.id.CorrectPasswordAgainRequest);
        CorrectPasswordRequest = view.findViewById(R.id.CorrectPasswordRequest);
        CorrectEmailRequest = view.findViewById(R.id.CorrectEmailRequest);
        CorrectSurNameRequest = view.findViewById(R.id.CorrectSurNameRequest);
        CorrectNameRequest = view.findViewById(R.id.CorrectNameRequest);
        parent = view.findViewById(R.id.parent);
        checkBox = view.findViewById(R.id.checkboxAgree);
        radioMale = view.findViewById(R.id.radioMale);
        radioFemale = view.findViewById(R.id.radioFemale);
        radioOther = view.findViewById(R.id.radioOther);
        scrollView = view.findViewById(R.id.screen);


        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "How you dare to change Master Yoda's picture!! Go away!", Toast.LENGTH_SHORT).show();
            }
        });


        labelChangeListeners();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correction()) {

                    if(checkBox.isChecked())
                    {
                        showSnackBar();
                    }

                    else{

                        Toast.makeText(view.getContext(), "You have to check the agreement to proceed.", Toast.LENGTH_SHORT).show();


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


        Snackbar.make(getActivity().findViewById(android.R.id.content), "New Person Added", Snackbar.LENGTH_LONG).setAction("close", new View.OnClickListener() {
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
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        }).show();


        //// this Snackbars below only for activities. you have to make the snackbar like top for fragments.


//        Snackbar.make(parent,"new person added",Snackbar.LENGTH_INDEFINITE).setAction("close", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        }).show();

//        Snackbar.make(parent, "New person added", Snackbar.LENGTH_INDEFINITE).setAction("Close", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                // screen.arrowScroll(0);
//
//
//
//
//            }
//        }).show();
    }


    private void labelChangeListeners(){


        lblName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                CorrectNameRequest.setVisibility(View.GONE);
            }
        });


        lblSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                CorrectSurNameRequest.setVisibility(View.GONE);
            }
        });

        lblEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                CorrectEmailRequest.setVisibility(View.GONE);
            }
        });

        lblPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                CorrectPasswordRequest.setVisibility(View.GONE);
            }
        });

        lblPasswordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                CorrectPasswordRequest.setVisibility(View.GONE);
            }
        });
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


//    private void postRequest(){
//
//
//
//        String url = "https://raw.githubusercontent.com/EsadDogan/projectJson/main/db.json";
//
//        // Request a string response from the provided URL.
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, null,
//                new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("JSONPost", response.toString());
//                        //pDialog.hide();
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("JSONPost", "Error: " + error.getMessage());
//                //pDialog.hide();
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("name=someVal&comment=someOtherVal");
//                //params.put("comment", "someOtherVal");
//                return params;
//            }
//        };
//
//    }

}