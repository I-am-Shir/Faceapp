package com.example.faceapp;

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
import java.io.File;

public class PictureFrag4 extends Fragment {
    private Constraints constraints;
    private Button photo_from_gallery, photo_from_camera, finishSignUpBut, backBut;;
    private TextView galleryPicException, cameraPicException;
    private ImageView picturePreview;
    private Boolean picCheck;
    private Uri imageUri, uri;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private ActivityResultLauncher<Uri> mGetContent;

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
        finishSignUpBut = view.findViewById(R.id.finishSignUpBut);
        backBut = view.findViewById(R.id.backBut);
        photo_from_gallery = view.findViewById(R.id.photo_from_gallery);
        photo_from_camera = view.findViewById(R.id.photo_from_camera);
        galleryPicException = view.findViewById(R.id.galleryPicException);
        cameraPicException = view.findViewById(R.id.cameraPicException);
        galleryPicException.setVisibility(View.GONE);
        cameraPicException.setVisibility(View.GONE);
        finishSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picCheck) {
                    // Sign_up_page sign_up_page = (Sign_up_page) getActivity();
                    // sign_up_page.replaceFragments(NameFrag3.class);
                }
            }
        });

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
                picturePreview.setImageURI(imageUri);

                // Launch the photo picker and let the user choose only images.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });
        photo_from_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(requireActivity().getFilesDir(), "picFromCamera");
                uri = FileProvider.getUriForFile(requireActivity(), requireActivity().getPackageName() + ".provider", file);
                mGetContent.launch(uri);

                picturePreview = new ImageView(getContext());
                picturePreview = getView().findViewById(R.id.picturePreview);
                picturePreview.setImageURI(uri);
            }
        });
    }
}