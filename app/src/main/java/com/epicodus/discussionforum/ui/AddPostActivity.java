package com.epicodus.discussionforum.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Category;
import com.epicodus.discussionforum.models.Post;

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
    }

    @Override
    public void onClick(View view) {
        if (view == mPostButton) {
            String postTitle = mAddPostTitleEditText.getText().toString();
            String postDetails = mTextBoxEditText.getText().toString();
            mCategory.getPosts().add(new Post(postTitle, postDetails));
        }
    }
}
