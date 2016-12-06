package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Category;
import com.epicodus.discussionforum.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewCategoryActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.categoryTitleEditText) EditText mCategoryTitleEditText;
    @Bind(R.id.categoryImageEditText) EditText mCategoryImageEditText;
    @Bind(R.id.addCategoryButton) Button mAddCategoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        ButterKnife.bind(this);

        mAddCategoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mAddCategoryButton) {
            DatabaseReference categoryRef = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_CATEGORY);
            String categoryName = mCategoryTitleEditText.getText().toString();
            String categoryImageUrl = mCategoryImageEditText.getText().toString();
            if(!categoryImageUrl.equals("")&&categoryImageUrl!=null){
                if(!categoryName.equals("")&&categoryName!=null){
                    Category newCategory = new Category(categoryName, categoryImageUrl);

                    DatabaseReference pushRef = categoryRef.push();
                    String pushId = pushRef.getKey();
                    newCategory.setCategoryId(pushId);

                    pushRef.setValue(newCategory);
                    Intent intent = new Intent(NewCategoryActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }else{
                Toast.makeText(this, "You are a failure, please leave", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
