//package com.example.faceapp;
//
//import android.net.Uri;
//import android.widget.Button;
//
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.PickVisualMediaRequest;
//
//public class AddingPhoto {
//    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
//    private ActivityResultLauncher<Uri> mGetContent;
//    Boolean picCheck;
//    public AddingPhoto() {
//        picCheck = true;
//        Button photo_from_gallery = view.findViewById(R.id.photo_from_gallery);
//        Button photo_from_camera = view.findViewById(R.id.photo_from_camera);
//    }
//
//    public void addPhoto() {
//        // Registers a photo picker activity launcher in single-select mode.
//        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
//            // Callback is invoked after the user selects a media item or closes the
//            // photo picker.
//            if (uri != null) {
//                Log.d("PhotoPicker", "Selected URI: " + uri);
//                imageUri = uri;
//                picturePreview.setImageURI(imageUri);
//            } else {
//                Log.d("PhotoPicker", "No media selected");
//            }
//        });
//
//        mGetContent = registerForActivityResult(
//                new ActivityResultContracts.TakePicture(),
//                new ActivityResultCallback<Boolean>() {
//                    @Override
//                    public void onActivityResult(Boolean result) {
//                        if (result) {
//                            Log.d("PhotoPicker", "Photo saved to: " + imageUri);
//                            picturePreview.setImageURI(imageUri);
//                        } else {
//                            Log.d("PhotoPicker", "No photo saved");
//                        }
//                    }
//                });
//    }
//}
