package com.yogi.financeapp.Fragments;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.yogi.financeapp.R;
import com.yogi.financeapp.models.Blog;

import org.w3c.dom.Text;

public class BlogDetailFragment extends Fragment {

    private static final String TAG = "BlogDetailFragment";

    private static final String ARG_USERNAME = "ARG_USERNAME";
    private static final String ARG_TITLE = "ARG_TITLE";
    private static final String ARG_TIMESTAMP = "ARG_TIMESTAMP";
    private static final String ARG_IMAGE_URI= "ARG_IMAGE_URI";
    private static final String ARG_CATEGORY = "ARG_CATEGORY";
    private static final String ARG_USER_ID = "ARG_USER_ID";
    private static final String ARG_DESCRIPTION = "ARG_DESCRIPTION";

    private TextView titleTextView, descriptionTextView, usernameTextView, timeStampTextView;
    ImageView imageView;
    Button button;

    public static BlogDetailFragment newInstance(Blog blog) {
        BlogDetailFragment blogDetailFragment = new BlogDetailFragment();
        Bundle args = new Bundle();

        final String timeAgo = (String) DateUtils.getRelativeTimeSpanString(blog.getTimestamp().getSeconds() * 1000);

        args.putString(ARG_USERNAME, blog.getUsername());
        args.putString(ARG_TITLE, blog.getTitle());
        args.putString(ARG_TIMESTAMP, timeAgo);
        args.putString(ARG_IMAGE_URI, blog.getImageUri());
        args.putString(ARG_CATEGORY, blog.getCategory());
        args.putString(ARG_USER_ID, blog.getUserId());
        args.putString(ARG_DESCRIPTION, blog.getDescription());
        blogDetailFragment.setArguments(args);
        return blogDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blog_detail, container, false);



        titleTextView = view.findViewById(R.id.blog_detail_title_text_view);
        usernameTextView = view.findViewById(R.id.blog_detail_username_text_view);
        descriptionTextView = view.findViewById(R.id.blog_detail_description_text_view);
        timeStampTextView = view.findViewById(R.id.blog_detail_date_text_view);
        imageView = view.findViewById(R.id.blog_detail_image_view);


        titleTextView.setText(getArguments().getString(ARG_TITLE));
        usernameTextView.setText(getArguments().getString(ARG_USERNAME));
        descriptionTextView.setText(getArguments().getString(ARG_DESCRIPTION));
        descriptionTextView.setText(getArguments().getString(ARG_DESCRIPTION));
        timeStampTextView.setText(getArguments().getString(ARG_TIMESTAMP));

        Glide.with(getContext())
                .load(getArguments().getString(ARG_IMAGE_URI))
                .into(imageView);

        return view;


    }


}
