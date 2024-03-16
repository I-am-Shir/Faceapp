package com.example.faceapp.view.fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.faceapp.R;
import com.example.faceapp.model.User;
import com.example.faceapp.utilities.Constraints;
import com.example.faceapp.view.Log_in_page;
import com.example.faceapp.view.Sign_up_page;

import java.io.File;
import java.util.UUID;

public class PictureFrag4 extends Fragment {
    private Constraints constraints;
    ;
    private TextView imageException;
    private ImageView picturePreview;
    private Boolean picCheck;
    private Uri imageUri, uri;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private ActivityResultLauncher<Uri> mGetContent;
    private User user;

    public PictureFrag4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Registers a photo picker activity launcher in single-select mode.
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: " + uri);
                imageUri = uri;
                picturePreview.setImageURI(imageUri);
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });

        mGetContent = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if (result) {
                            imageUri = uri;
                            picturePreview.setImageURI(imageUri);
                        } else {
                            Log.d("PhotoPicker", "No photo taken");
                        }
                    }
                });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        constraints = new Constraints();
        picCheck = true;
        Button finishSignUpBut = view.findViewById(R.id.finishSignUpBut);
        Button backBut = view.findViewById(R.id.backBut);
        Button photo_from_gallery = view.findViewById(R.id.photo_from_gallery);
        Button photo_from_camera = view.findViewById(R.id.photo_from_camera);
        imageException = view.findViewById(R.id.imageException);
        imageException.setVisibility(View.GONE);

        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                 Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                 sign_up_page.replaceFragments(Log_in_page.class);
            }
        });
        photo_from_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picturePreview = new ImageView(getContext());
                picturePreview = getView().findViewById(R.id.picturePreview);


                // Launch the photo picker and let the user choose only images.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });
        photo_from_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a unique file name based on timestamp and a unique identifier
                String fileName = "pic_" + System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + ".jpg";
                File file = new File(requireActivity().getFilesDir(), fileName);

                // Get URI for the file using FileProvider
                uri = FileProvider.getUriForFile(requireActivity(), requireActivity().getPackageName() + ".provider", file);
                mGetContent.launch(uri);

                picturePreview = new ImageView(getContext());
                picturePreview = getView().findViewById(R.id.picturePreview);
            }
        });

        finishSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picCheck = true;
                try{
                    constraints.imageCheck(picturePreview);
                } catch (Exception e) {
                    imageException.setText(e.getMessage());
                    imageException.setVisibility(View.VISIBLE);
                    picCheck = false;
                }
                if (picCheck) {
                    Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                    sign_up_page.addToList(4, imageUri.toString());
                    sign_up_page.storeUser(sign_up_page.setUser());
                }

            }
        });
    }
}