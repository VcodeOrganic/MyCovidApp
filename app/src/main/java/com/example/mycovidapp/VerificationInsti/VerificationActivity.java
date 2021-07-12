package com.example.mycovidapp.VerificationInsti;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycovidapp.R;
import com.example.mycovidapp.SignInInstitute;
import com.example.mycovidapp.databinding.ActivityVerificationBinding;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class VerificationActivity extends AppCompatActivity {

    ActivityVerificationBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;

    private Button mBtnChooseImage, mBtnUpload;
    private TextView mTvShowUploads;
    private EditText mEtFilename;
    private ImageView mImageView;
    private ProgressBar mPrgBarUploads;

    private ProgressDialog progressDialogUpload;
    private Uri mImageUri;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;
    private String userIDName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mBtnChooseImage = findViewById(R.id.btnChooseFiles);
        mBtnUpload = findViewById(R.id.btnUpload);
        mEtFilename = findViewById(R.id.etFileName);
        mImageView = findViewById(R.id.imgUpload);
        mPrgBarUploads = findViewById(R.id.progBarUpload);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference("verification");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("verification");

        if (user!=null) {
            userIDName = user.getUid().toString();
        }


        progressDialogUpload = new ProgressDialog(VerificationActivity.this);
        progressDialogUpload.setTitle("Uploading Documents");
        progressDialogUpload.setMessage("Please wait while your documents are being uploaded");


        mBtnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

       mBtnUpload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               progressDialogUpload.show();
               uploadFile();
           }
       });
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(mImageView);

        }
    }

    private String getFileExtension(Uri uri){
        //get filetype extension from uploaded image : jpg, png
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile(){
        if(mImageUri!=null){

            StorageReference fileReference =  mStorageRef.child(userIDName+"_"+System.currentTimeMillis()+"."+getFileExtension(mImageUri));
                fileReference.putFile(mImageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
                {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        return fileReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        progressDialogUpload.dismiss();
                        if (task.isSuccessful())
                        {
                            Uri downloadUri = task.getResult();
                            Log.e(TAG, "then: " + downloadUri.toString());


                            Upload upload = new Upload(mEtFilename.getText().toString().trim(),
                                    downloadUri.toString());

                            Toast.makeText(VerificationActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                            Random ran = new Random();
                            int code = ran.nextInt(10000);

                            //String uploadID = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(userIDName+"_"+code).setValue(upload);

                        } else
                        {
                            Toast.makeText(VerificationActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


// Deprecated method to do the same, just uses a better UI by showing progress bar updates instead of progress dialog

//            //To name a file uniquely, best way is to store it as the current time in milliseconds
//            StorageReference fileReference =  mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
//            fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    //Delays the progress bar at 100% for some time so that user sees this, just UI things
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            mPrgBarUploads.setProgress(0);
//                        }
//                    },5000);
//                    Toast.makeText(VerificationActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
//                    Upload upload = new Upload(mEtFilename.getText().toString().trim(),
//                            taskSnapshot.)
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(VerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                    double progress = (100.0 * snapshot.getBytesTransferred()/snapshot.getTotalByteCount()); //gives current progress on a scale of 100
//                    mPrgBarUploads.setProgress((int)progress);
//                }
//            });
        }
        else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}