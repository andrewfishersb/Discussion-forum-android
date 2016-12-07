package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Category;
import com.epicodus.discussionforum.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddPostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.addPostTitleEditText) EditText mAddPostTitleEditText;
    @Bind(R.id.textBoxEditText) EditText mTextBoxEditText;
    @Bind(R.id.postButton) Button mPostButton;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);
        mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));

        mPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mPostButton) {
            DatabaseReference postRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_POST);
            String postTitle = mAddPostTitleEditText.getText().toString();
            String postDetails = mTextBoxEditText.getText().toString();
            String postCatId = mCategory.getCategoryId();
            Post newPost = new Post(postTitle, postDetails, postCatId);

            postRef.push().setValue(newPost);

            Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
