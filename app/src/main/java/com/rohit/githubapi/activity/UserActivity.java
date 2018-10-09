package com.rohit.githubapi.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.rohit.githubapi.R;
import com.rohit.githubapi.model.GitHubUser;
import com.rohit.githubapi.rest.APIClient;
import com.rohit.githubapi.rest.GitHubUserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ImageView mImageViewAvtarImage;
    TextView mTextViewUserName;
    TextView mTextViewFollowers;
    TextView mTextViewFollowing;
    TextView mTextViewLogin;
    TextView email;
    Button mButtonWonedRepository;

    Bundle extras;
    String newString;

    Bitmap myImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mImageViewAvtarImage = findViewById(R.id.avatar);
        mTextViewUserName =  findViewById(R.id.username);
        mTextViewFollowers = findViewById(R.id.followers);
        mTextViewFollowing = findViewById(R.id.following);
        mTextViewLogin =  findViewById(R.id.logIn);
        email = findViewById(R.id.email);
        mButtonWonedRepository = findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);
        loadData();
    }

    public void loadOwnRepos(View view){

        Intent intent = new Intent(UserActivity.this,Repositories.class);
        intent.putExtra("username", newString);
        startActivity(intent);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }
            return null;
        }
    }


    public void loadData() {
        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser>
                    response) {

                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar()).get();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                mImageViewAvtarImage.setImageBitmap(myImage);
                mImageViewAvtarImage.getLayoutParams().height=220;
                mImageViewAvtarImage.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    mTextViewUserName.setText("No name provided");
                } else {
                    mTextViewUserName.setText("Username: " + response.body().getName());
                }

                mTextViewFollowers.setText("Followers: " + response.body().getFollowers());
                mTextViewFollowing.setText("Following: " + response.body().getFollowing());
                mTextViewLogin.setText("LogIn: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setText("No email provided");
                } else{
                    email.setText("Email: " + response.body().getEmail());
                }

            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });
    }
}

