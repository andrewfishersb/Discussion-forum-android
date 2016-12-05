package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.adapters.CategoryListAdapter;
import com.epicodus.discussionforum.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.categoryButton) Button mCategoryButton;
    private ArrayList<Category> mCategories = new ArrayList<>();
    private DatabaseReference mAllCategoriesReference;
    private ValueEventListener mCategoryReferenceListener;
    private CategoryListAdapter mAdapter;
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAllCategoriesReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_CATEGORY);

        mCategoryReferenceListener = mAllCategoriesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot categorySnapshot : dataSnapshot.getChildren()){
                    String title = categorySnapshot.getValue(Category.class).getTitle();
                    String image = categorySnapshot.getValue(Category.class).getImage();
                    Category currentCategory = new Category(title,image);
                    mCategories.add(currentCategory);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        getCategories();
        mCategoryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mCategoryButton) {

            Intent intent = new Intent(MainActivity.this, NewCategoryActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAllCategoriesReference.removeEventListener(mCategoryReferenceListener);
    }

    private void getCategories(){

        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter = new CategoryListAdapter(getApplicationContext(), mCategories);
                mRecyclerView.setAdapter(mAdapter);
                RecyclerView.LayoutManager layoutManager =
                        new LinearLayoutManager(MainActivity.this);
                mRecyclerView.setLayoutManager(layoutManager);
                mRecyclerView.setHasFixedSize(true);
            }
        });
    }
}
