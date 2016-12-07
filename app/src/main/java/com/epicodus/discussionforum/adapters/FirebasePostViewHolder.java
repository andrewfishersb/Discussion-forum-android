package com.epicodus.discussionforum.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.epicodus.discussionforum.R;
import com.epicodus.discussionforum.models.Post;

public class FirebasePostViewHolder extends RecyclerView.ViewHolder  {
    View mView;
    Context mContext;

    public FirebasePostViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindPost(Post post) {
        TextView mPostNameTextView = (TextView) mView.findViewById(R.id.postNameTextView);
        TextView mPostDetailsTextView = (TextView) mView.findViewById(R.id.postDetailsTextView);

        mPostNameTextView.setText(post.getTitle());
        mPostDetailsTextView.setText(post.getDetails());
    }

}
