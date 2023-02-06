package com.example.mytravelapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mytravelapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity {

    TextInputEditText inputPost;
    ImageView imgCloseAddPost;
    MaterialButton btnAddPost;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        db = FirebaseFirestore.getInstance();

        imgCloseAddPost = (ImageView) findViewById(R.id.imgCloseAddPost);
        btnAddPost = (MaterialButton) findViewById(R.id.btnAddPost);
        inputPost = (TextInputEditText) findViewById(R.id.inputPost);

        imgCloseAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddPostActivity.this, MainActivity.class));
            }
        });

        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputPost.getText().length() > 0) {
                    saveData(inputPost.getText().toString());
                } else {
                    Toast.makeText(AddPostActivity.this, "post cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveData(String post) {
        Map<String, Object> update = new HashMap<>();
        update.put("message", post);

        db.collection("updates")
                .add(update)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddPostActivity.this, "post has been added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPostActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}