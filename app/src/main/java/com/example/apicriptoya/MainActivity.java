package com.example.apicriptoya;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apicriptoya.client.RetrofitClient;
import com.example.apicriptoya.databinding.ActivityMainBinding;
import com.example.apicriptoya.entity.Message;
import com.example.apicriptoya.service.RetrofitApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private TextView mTextView;
    private ActivityMainBinding binding;
    private RetrofitApiService apiService;

    Button APIbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initValues();


        APIbtn= (Button) findViewById(R.id.APIButton);

        APIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Test", Toast.LENGTH_LONG).show();
                getMessage("usd");
            }
        });
    }

    private void initView(){
        mTextView = binding.APITextView;
    }

    private void initValues(){
        apiService = RetrofitClient.getApiService();
    }



    private void getMessage(String fiat){
        apiService.getMessageFiat(fiat).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message message = response.body();
                mTextView.setText(message.toString());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                mTextView.setText(t.getMessage());
            }
        });
    }









}