package com.example.mytravelapp.fragments;

import android.annotation.SuppressLint;
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
import com.example.mytravelapp.adapters.HomeAdapter;
import com.example.mytravelapp.models.HomeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView rvHome;
    ArrayList<HomeModel> list;
    HomeAdapter homeAdapter;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        rvHome = view.findViewById(R.id.rvHome);

        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(), list);
        rvHome.setAdapter(homeAdapter);


        db.collection("updates")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeModel post = new HomeModel(document.getString("message"));
                                list.add(post);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "failed to load data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return view;
    }
}
