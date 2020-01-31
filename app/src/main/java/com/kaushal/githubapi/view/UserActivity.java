package com.kaushal.githubapi.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaushal.githubapi.R;
import com.kaushal.githubapi.model.APIClient;
import com.kaushal.githubapi.model.EndPoints;
import com.kaushal.githubapi.viewmodel.GitHubUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private ImageView avatar;
    private TextView mUsername;
    private TextView followers;
    private TextView following;
    private TextView email;
    private TextView login;
    private Button btnRepositories;

    Bundle extras;
    String currentUser;
    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatar = findViewById(R.id.avatar);
        mUsername = findViewById(R.id.username);
        followers = findViewById(R.id.followers);
        following = findViewById(R.id.following);
        login = findViewById(R.id.logIn);
        email = findViewById(R.id.email);
        btnRepositories = findViewById(R.id.btnRepositories);

        extras = getIntent().getExtras();
        currentUser = extras.getString("STRING_I_NEED");

        System.out.println(currentUser);

        loadData();

        btnRepositories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivity.this, RepositoriesActivity.class);
                intent.putExtra("username", currentUser);
                startActivity(intent);

            }
        });
    }

    public void loadData() {

        final EndPoints apiService = APIClient.getClient().create(EndPoints.class);

                                            //getUser is the method defined within EndPoints
        Call<GitHubUser> call = apiService.getUser(currentUser);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                /**
                 * This way you can fetch all that information of users that you want to.
                 */
                if (response.body().getName() == null) mUsername.setText("Username is not provided");
                else mUsername.setText("Username: "+response.body().getName());


                followers.setText("Followers: "+response.body().getFollowers());
                following.setText("Following: "+response.body().getFollowing());
                login.setText("login ID: "+response.body().getLogin());


                if (response.body().getEmail() == null) email.setText("No email found");
                else email.setText("email: "+response.body().getEmail());



                /**
                 * Lets download the image as well.
                 */
                ImageDownloader task = new ImageDownloader();

                try {

                    myImage = task.execute(response.body().getAvatar()).get();
                    avatar.setImageBitmap(myImage);
                    avatar.getLayoutParams().height = 220;
                    avatar.getLayoutParams().width = 220;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }

    //For downloading image from URL provided in API
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
}
