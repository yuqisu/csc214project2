package camera;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import project2.csc214.socialnetwork.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class cameraFragament extends Fragment {
    private File mphotoFile;
    private List<File> mPhotoFiles;
    private ImageButton imageButton;
    private PhotoAdataper madapter;

    public cameraFragament() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera_fragament, container, false);
        mPhotoFiles = new ArrayList<>();
        RecyclerView browsephoto = (RecyclerView)view.findViewById(R.id.camera_recycler);
        browsephoto.setLayoutManager(new GridLayoutManager(getActivity(),2));
        
        imageButton =(ImageButton)view.findViewById(R.id.add_feed_picute_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeAphoto();
            }
        });
        return view;
    }

    private void takeAphoto() {
        Intent intent = new Intent();
    }

}
