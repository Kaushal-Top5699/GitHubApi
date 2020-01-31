package com.kaushal.githubapi.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kaushal.githubapi.R;
import com.kaushal.githubapi.model.APIClient;
import com.kaushal.githubapi.model.EndPoints;
import com.kaushal.githubapi.viewmodel.GitHubRepo;
import com.kaushal.githubapi.viewmodel.RepoAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesActivity extends AppCompatActivity {

    private String recievedUsername;
    private TextView username;
    private RecyclerView mRecyclerView;
    private List<GitHubRepo> myDataSource = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);

        Bundle extras = getIntent().getExtras();
        recievedUsername = extras.getString("username");

        username = findViewById(R.id.username);
        username.setText(recievedUsername);

        mRecyclerView = findViewById(R.id.repo_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RepoAdapter(myDataSource, R.layout.layout_repo_list_view, getApplicationContext());
        mRecyclerView.setAdapter(adapter);

        loadRepositories();
    }

    private void loadRepositories() {

        EndPoints apiService = APIClient.getClient().create(EndPoints.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(recievedUsername);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {

                myDataSource.clear();
                myDataSource.addAll(response.body());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {

                Toast.makeText(RepositoriesActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
