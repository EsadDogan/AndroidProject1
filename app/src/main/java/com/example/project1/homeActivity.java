package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.project1.home.AddContactFragment;
import com.example.project1.home.BlankFragment;
import com.example.project1.home.ContactsFragment;
import com.example.project1.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class homeActivity extends AppCompatActivity {

    public BottomNavigationView bottomNavigationView;
    FrameLayout myFrame;
    public RequestQueue mQueue;
    public String mesaj;
    public int odul;
    public ArrayList<Contacts> contacts = new ArrayList<>();
    public boolean contactsIsDownloaded = false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mQueue = Volley.newRequestQueue(this);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);





        Intent intent2 = getIntent();
             try {
                 if (intent2 != null){

                     String name = intent2.getExtras().getString("name");
                     Log.d("TAG", "onCreate: "+name+" alindi  ");
                 }
             }catch (Exception ex){
                 ex.printStackTrace();
             }






        bottomNavigationView.setOnItemSelectedListener(item -> {

            Intent intent;

            switch (item.getItemId()){

                case R.id.home:

                    goHome();
                    break;

                case R.id.contacts:

                    FragmentManager manager = getSupportFragmentManager();
//                    manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    manager.beginTransaction()
                            .replace(R.id.myFrameLay, new ContactsFragment(), ContactsFragment.TAG)
                            .addToBackStack(null)
                            .commit();
                    //intent = new Intent(homeActivity.this , seeContactsActivity.class);
                    //startActivity(intent);
                    break;
                case R.id.add:
//                     intent = new Intent(homeActivity.this , addContactsActivity.class);
//                    startActivity(intent);

//                    FragmentManager manager2 = getSupportFragmentManager();
////                    manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    manager2.beginTransaction()
//                            .replace(R.id.myFrameLay, BlankFragment.newInstance("esad","asd"), "tag")
//                            .addToBackStack(null)
//                            .commit();
                    AddContactFragment fragment = new AddContactFragment();
                    Bundle args = new Bundle();
                    args.putString("param1", "dogan");
                    args.putString("param2", "tt");
                    fragment.setArguments(args);
                  //  FrgmentHelper.setFragment(this,BlankFragment.newInstance("esad","asd"),"s" );
                  //  FrgmentHelper.setFragment(this,fragment,AddContactFragment.TAG );
                    // with that bottom part we call new fragment and without back button crowd
                    FragmentManager manager2 = getSupportFragmentManager();
                    manager2.beginTransaction()
                            .replace(R.id.myFrameLay, new AddContactFragment(), AddContactFragment.TAG)
                            .addToBackStack(null)
                            .commit();
                    break;

                default: break;
            }


            return true;
        });


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.myFrameLay, new HomeFragment(), HomeFragment.TAG)
                .commit();

        json();

    }

    private void goHome(){

        FragmentManager manager = getSupportFragmentManager();
        //manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        manager.beginTransaction()
                .replace(R.id.myFrameLay, new HomeFragment(), HomeFragment.TAG)
                .addToBackStack(null)
                .commit();
    }

    private void veriGeldi(JSONObject response){


        try {

             mesaj = response.getString("mesaj");
             odul = response.getInt("odul");



        } catch (JSONException e) {
            Log.d("TAG", "onResponse: json hata");
            e.printStackTrace();
            return;
        }

        HomeFragment fragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);

        if (fragment != null)
        {
            fragment.getData(mesaj);
            Log.d("TAG", "veriGeldi: 1 ");
        }
        else {
            Log.d("TAG", "veriGeldi: 2 ");
        }

    }

    private void json(){



        String url = "https://raw.githubusercontent.com/EsadDogan/projectJson/main/ayar.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                // ContactsFragment fragment = (ContactsFragment) getSupportFragmentManager().findFragmentByTag(ContactsFragment.TAG);
//
//                if (fragment != null)
//                {
//                    fragment.veriGeldi(response);
//                }

                veriGeldi(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });{

        }




            mQueue.add(request);


    }


}