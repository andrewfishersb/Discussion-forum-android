package com.epicodus.discussionforum.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.discussionforum.Constants;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.adapters.FirebasePostViewHolder;
import com.epicodus.discussionforum.models.Category;
import com.epicodus.discussionforum.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryDetailActivity extends AppCompatActivity {
    @Bind(R.id.categoryDetailTitleTextView) TextView mCategoryDetailTitleTextView;
    @Bind(R.id.categoryDetailImageView) ImageView mCategoryDetailImageView;
    @Bind(R.id.postRecyclerView) RecyclerView mPostRecyclerView;

    private ArrayList<Category> mCategories = new ArrayList<>();
    private Category mCategory;
    private ArrayList<Post> mPosts = new ArrayList<>();
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private Query mAllPostsReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);



        mCategories = Parcels.unwrap(getIntent().getParcelableExtra("categories"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        mCategory = mCategories.get(startingPosition);

        //finds all of one id
        mAllPostsReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_POST)
                .orderByChild("categoryId")
                .equalTo(mCategory.getCategoryId());


        setUpPostFirebaseAdapter();

        mCategoryDetailTitleTextView.setText(mCategory.getTitle());
        Picasso.with(this).load(mCategory.getImage()).error(R.drawable.nophotoavailable).into(mCategoryDetailImageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_add_post:
                Intent intent = new Intent(CategoryDetailActivity.this, AddPostActivity.class);
                intent.putExtra("category", Parcels.wrap(mCategory));
                startActivity(intent);
                 return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setUpPostFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolder>(Post.class, R.layout.post_list_item, FirebasePostViewHolder.class, mAllPostsReference) {
            @Override
            protected void populateViewHolder(FirebasePostViewHolder viewHolder, Post model, int position) {
                viewHolder.bindPost(model);
            }
        };
        mPostRecyclerView.setHasFixedSize(true);
        mPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mPostRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
