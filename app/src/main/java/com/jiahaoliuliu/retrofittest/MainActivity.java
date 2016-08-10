package com.jiahaoliuliu.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link the views
        mTextView = (TextView) findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<GitModel> repos = service.listRepos("jiahaoliuliu");
        repos.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                Log.v(TAG, "Response " + response.body().toString());
                mTextView.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Log.e(TAG, "Error on request ", t);
            }
        });
    }
}
