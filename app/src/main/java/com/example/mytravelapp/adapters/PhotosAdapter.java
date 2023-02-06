package com.example.mytravelapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytravelapp.R;
import com.example.mytravelapp.models.PhotosModel;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    Context context;
    ArrayList<PhotosModel> list;

    public PhotosAdapter(Context context, ArrayList<PhotosModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_photos_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imgPhotosPost);
        holder.txtPhotosSubject.setText(list.get(position).getImg_subject());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhotosPost;
        TextView txtPhotosSubject;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhotosPost = (ImageView) itemView.findViewById(R.id.imgPhotosPost);
            txtPhotosSubject = (TextView) itemView.findViewById(R.id.txtPhotosSubject);
        }
    }
}
