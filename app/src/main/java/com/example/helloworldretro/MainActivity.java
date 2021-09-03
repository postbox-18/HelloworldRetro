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
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    UserService userService;
    String TAG="user";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textview);
        userService=Apiutils.getUserService();


        Call<ResponseBody> call = userService.login();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG,"Response"+response.body().string());
                    //String res=response.body().string();
                    //JSONObject jsonObject=response.body().string();
                    ///https://www.tutorialspoint.com/android/android_json_parser.htm
                    /*
                    * http://192.168.0.31/PatientPortals/api/login

username:kavitha
password:aosta*/

                    textView.setText(response.body().string());
                    //1. string to json
                    /*2 . get key value of the json obj
                    3.set value to modle
                    *  */

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"IOException:"+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG,"onFailure:"+t.getMessage());
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }
}