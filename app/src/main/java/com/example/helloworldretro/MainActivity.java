package com.example.helloworldretro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    UserService userService;
    String TAG="user";
    TextView textView;
    String hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        //textView1 = findViewById(R.id.textview2);
        userService = Apiutils.getUserService();


        Call<ResponseBody> call = userService.login();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json_obj=response.body().string();
                    //Log.e(TAG, "Response" + response.body().string());

                    try {
                        // get JSONObject from JSON file
                        JSONArray jsonArray=new JSONArray(json_obj);
                        JSONObject obj = jsonArray.getJSONObject(0);
                        // fetch JSONObject named employee
                        JSONObject employee = obj.getJSONObject("employee");
                        // get employee name and salary
                        hello = employee.getString("string");
                       // salary = employee.getString("salary");
                        // set employee name and salary in TextView's
                        textView.setText(hello);
                     //   textView1.setText("Salary: "+salary);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //String res=response.body().string();
                    //JSONObject jsonObject=response.body().string();
                    ///https://www.tutorialspoint.com/android/android_json_parser.htm
                    /*
                    * http://192.168.0.31/PatientPortals/api/login

username:kavitha
password:aosta*/

                   // textView.setText();
                    //1. string to json
                    /*2 . get key value of the json obj
                    3.set value to modle
                    *  */

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "IOException:" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure:" + t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

    }
}