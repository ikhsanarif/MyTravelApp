package com.example.mytravelapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytravelapp.R;
import com.example.mytravelapp.adapters.PhotosAdapter;
import com.example.mytravelapp.models.PhotosModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class PhotosFragment extends Fragment {
    RecyclerView rvPhotos;
    ArrayList<PhotosModel> list;
    PhotosAdapter photosAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public PhotosFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos,null);

        rvPhotos = view.findViewById(R.id.rvPhotos);

        rvPhotos.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        photosAdapter = new PhotosAdapter(getActivity(), list);
        rvPhotos.setAdapter(photosAdapter);

        db.collection("photos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                PhotosModel photosModel = document.toObject(PhotosModel.class);
                                list.add(photosModel);
                                photosAdapter.notifyDataSetChanged();
                            }
                        }else {
                            Toast.makeText(getActivity(), "failed to load data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return view;
    }
}
