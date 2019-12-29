package com.yogi.financeapp.Adapter;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yogi.financeapp.Fragments.BlogDetailFragment;
import com.yogi.financeapp.Fragments.BlogsFragment;
import com.yogi.financeapp.R;
import com.yogi.financeapp.models.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> implements Filterable {
    private static final String TAG = BlogAdapter.class.getSimpleName();
    private Context context;
    private FragmentManager fragmentManager;
    private List<Blog> blogList;
    private List<Blog> blogListFull;

    public BlogAdapter(Context context, List<Blog> blogList, FragmentManager fragmentManager) {
        this.context = context;
        this.blogList = blogList;
        this.fragmentManager = fragmentManager;
        blogListFull = new ArrayList<>(blogList);
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.blog_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Blog blog = blogList.get(position);
        String imageUrl;

        holder.title.setText(blog.getTitle());
//        holder.description.setText(blog.getDescription());
        holder.name.setText(blog.getUsername());

        final String timeAgo = (String) DateUtils.getRelativeTimeSpanString(blog.getTimestamp().getSeconds() * 1000);
        holder.timeStamp.setText(timeAgo);

        imageUrl = blog.getImageUri();
        Log.d(TAG, "onBindViewHolder: " + imageUrl);
        Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView);

//        holder.category.setText(blog.getCategory());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BlogDetailFragment blogDetailFragment = BlogDetailFragment.newInstance(blog);
                fragmentManager.beginTransaction().replace(R.id.frame_container, blogDetailFragment).addToBackStack("BlogFragment").commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, timeStamp, name, category;
        public ImageView imageView;
        String userId, username;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            context = context;

            title = itemView.findViewById(R.id.blog_title);
//            description = itemView.findViewById(R.id.blog_description_list);
            timeStamp = itemView.findViewById(R.id.blog_time_stamp_list);
            imageView = itemView.findViewById(R.id.blog_image);
            name = itemView.findViewById(R.id.blog_username);
            parentLayout = itemView.findViewById(R.id.parent_layout);
//            category = itemView.findViewById(R.id.blog_category_list);

        }
    }
}
