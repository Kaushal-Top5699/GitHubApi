package com.kaushal.githubapi.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kaushal.githubapi.R;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {


    private List<GitHubRepo> repos;
    private int rowLayout;
    private Context context;

    public RepoAdapter(List<GitHubRepo> repos, int rowLayout, Context context) {
        this.setRepos(repos);
        this.setRowLayout(rowLayout);
        this.setContext(context);
    }

    public List<GitHubRepo> getRepos() {
        return repos;
    }

    public void setRepos(List<GitHubRepo> repos) {
        this.repos = repos;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }




    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout ,parent, false);

        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {

        holder.repoName.setText("Repository: "+repos.get(position).getRepository());
        holder.repoDescription.setText("Description: "+repos.get(position).getDescription());
        holder.repoLanguage.setText("Language: "+repos.get(position).getLanguage());

    }

    @Override
    public int getItemCount() {
        return repos.size();
    }


    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        LinearLayout repoLayout;
        TextView repoName;
        TextView repoDescription;
        TextView repoLanguage;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);

            repoLayout = itemView.findViewById(R.id.repo_item_layout);
            repoName = itemView.findViewById(R.id.repo_name);
            repoDescription = itemView.findViewById(R.id.repo_description);
            repoLanguage = itemView.findViewById(R.id.repo_language);
        }
    }
}
