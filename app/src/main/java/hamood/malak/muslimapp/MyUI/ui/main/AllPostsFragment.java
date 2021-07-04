package hamood.malak.muslimapp.MyUI.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hamood.malak.muslimapp.MyUI.PostAdapter;
import hamood.malak.muslimapp.MyUtils.MyPost;
import hamood.malak.muslimapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllPostsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllPostsFragment extends Fragment {

    private PostAdapter postAdapter;
    private ListView lstv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imSearch;
    private EditText etTitleTosearch;



    public AllPostsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllPostsFragment.
     */
    // TODO: Rename and change types and number of parameters
    //fragment הוא מסך קטן

    public static AllPostsFragment newInstance(String param1, String param2) {
        AllPostsFragment fragment = new AllPostsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     *         imSearch.setOnClickListener(new View.OnClickListener() { //click the search button
     *         readpostsFromFirebase(finalEtTitleTosearch.getText().toString()); //if the word is excit in firebase
     *
     * @param container
     * @param savedInstanceState
     * @return
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        postAdapter=new PostAdapter(getContext(),R.layout.item_post);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_posts, container, false);
        EditText etTitleTosearch= view.findViewById(R.id.etTitleTosearch);
        lstv=view.findViewById(R.id.lstv);
        lstv.setAdapter(postAdapter);
        //2 search:
        imSearch=view.findViewById(R.id.imSearch);
        etTitleTosearch=view.findViewById(R.id.etTitleTosearch);
        //3 search event:
        final EditText finalEtTitleTosearch = etTitleTosearch;
        imSearch.setOnClickListener(new View.OnClickListener() { //1
            @Override
            public void onClick(View view) {
               readpostsFromFirebase(finalEtTitleTosearch.getText().toString()); //2

            }
        });
        return view;
    }

    @Override
    public void onResume() {//  return to the back screen
        super.onResume();
        //6 search: delete method calling

        readpostsFromFirebase("");
    }
    //4 search: add parameter toi search
    public void readpostsFromFirebase(final String stTosearch)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();//to connect to database
        FirebaseAuth auth=FirebaseAuth.getInstance();//to get current UID
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
        //orderByChild("title").equalTo(stTosearch)// 5+6
        reference.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)//list
            {
                postAdapter.clear();
                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    MyPost t=d.getValue(MyPost.class);
                    Log.d("MYTASK",t.toString());
                    //5 search:
                    if(stTosearch==null || stTosearch.length()==0)
                    {
                        postAdapter.add(t);
                    }
                    else //6 search:
                        if(t.getTitle().contains(stTosearch))//ya7toy 3la nfs el word
                            postAdapter.add(t);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}