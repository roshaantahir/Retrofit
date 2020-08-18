package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getData();



    }

    public void getData(){

        MyInterface myInterface = MyInterface.retrofit.create(MyInterface.class);
        Call<List<Post>> call = myInterface.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (response.isSuccessful()) {



                    for (Post post : response.body()) {
                        showData(post);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

private void showData(Post post){
    TextView data = findViewById(R.id.data);
    data.append("userid: "+post.getUserId()+"/n");
    data.append("id: "+post.getId()+"/n");
    data.append("tittle: "+post.getTitle()+"/n");
    data.append("body: "+post.getBody()+"/n");
}
}