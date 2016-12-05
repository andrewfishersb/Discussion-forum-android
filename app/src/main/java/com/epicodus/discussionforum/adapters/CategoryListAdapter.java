package com.epicodus.discussionforum.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by Guest on 12/5/16.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>{
    private ArrayList<Category> mCategories = new ArrayList<>();
    private Context mContext;

    public CategoryListAdapter(Context context, ArrayList<Category> categories){
        mCategories = categories;
        mContext = context;
    }

    @Override
    public CategoryListAdapter.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item, parent, false);
        CategoryViewHolder viewHolder = new CategoryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryListAdapter.CategoryViewHolder holder ,int position){
        holder.bindCategory(mCategories.get(position));
    }

    @Override
    public int getItemCount(){
        return mCategories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.categoryName) TextView mCategoryName;
        @Bind(R.id.cateogryImageView) ImageView mCategoryImageView;

        private Context mContext;

        public CategoryViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
        }

        public void bindCategory(Category category){
            mCategoryName.setText("This is a category");
            Picasso.with(mContext).load("https://i.ytimg.com/vi/nj7Qj8uCTrA/hqdefault.jpg").into(mCategoryImageView);

        }



    }

}
