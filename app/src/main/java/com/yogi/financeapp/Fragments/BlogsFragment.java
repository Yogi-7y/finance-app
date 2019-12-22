package com.yogi.financeapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;
import com.yogi.financeapp.Adapter.BlogAdapter;
import com.yogi.financeapp.R;
import com.yogi.financeapp.models.Blog;

import java.util.ArrayList;
import java.util.List;


public class BlogsFragment extends Fragment {

    public static final String TAG = BlogsFragment.class.getSimpleName();

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;

    private List<Blog> blogList;
    private RecyclerView recyclerView;
    private BlogAdapter blogAdapter;

    private CollectionReference collectionReference = db.collection("Blog");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blogs, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        blogList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_blog);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Log.d(TAG, "onCreateView: " + collectionReference.getParent());
        collectionReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Log.d(TAG, "onSuccess: triggered");
                        Log.d(TAG, "onSuccess: " + queryDocumentSnapshots.isEmpty());
                        if (!queryDocumentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: Not empty");
                            for (QueryDocumentSnapshot blogs : queryDocumentSnapshots) {
                                Log.d(TAG, "onSuccess: " + blogs);
                                Blog blog = blogs.toObject(Blog.class);
                                blogList.add(blog);
                            }

                            blogAdapter = new BlogAdapter(getContext(), blogList);
                            recyclerView.setAdapter(blogAdapter);
                            blogAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getActivity(), "No Blogs Available", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }
                });


        return view;

    }

}
