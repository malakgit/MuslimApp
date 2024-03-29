package hamood.malak.muslimapp.MyUI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.UUID;

import hamood.malak.muslimapp.MyUtils.MyPost;
import hamood.malak.muslimapp.MyUtils.MyValidations;
import hamood.malak.muslimapp.R;

public class AddPost extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE =1000 ;
    private static final int PERMISSION_CODE =1001;
    private Button Post;
    private ImageButton imageView;
    private EditText location;
    private TextView code;
    private Button chooseimage_btn;
    private TextView PostTitle,TextMore;
    private Object Date;
    private Uri filePath;
    StorageTask uploadTask;
    private MyPost p;
    private Uri downladuri;
    private Uri toUploadimageUri;
    private MyPost myPost;


    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedByInstanceState){
        super.onCreate(savedByInstanceState);
        setContentView(R.layout.add_post);
        //find view by id
        code=findViewById(R.id.code);
        location=findViewById(R.id.location);
        PostTitle=findViewById(R.id.PostTitle);
        TextMore=findViewById(R.id.TextMore);
        Post=findViewById(R.id.Post);
        //upload: 3
        imageView=findViewById(R.id.imageView);
        chooseimage_btn=findViewById(R.id.chooseimage_btn);

        chooseimage_btn.setOnClickListener(new View.OnClickListener() { //when we click on the image btn:1
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "image", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        // permission not5 granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions,PERMISSION_CODE); //the window request(allow or no)
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else {
                    //system os is less then marshmallow
                    pickImageFromGallery();

                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // when we click in the image view
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                        // permission not5 granted, request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //show popup for runtime permission
                        requestPermissions(permissions,PERMISSION_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else {
                    //system os is less then marshmallow
                    pickImageFromGallery();

                }
            }
        });


        //listeners
        Post.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           validationForm();
        }
    });
}
    public void validationForm() //cheacks if the 7kol is all correct
    {
        String Forwhatthispost= PostTitle.getText().toString();
        String textmoe= TextMore.getText().toString();
        String loc=this.location.getText().toString();
        boolean isOk=true;
        if (Forwhatthispost.length()==0){
            isOk=false;
            PostTitle.setError("at least one sentence");
        }
        if (isOk){
            myPost=new MyPost();
            myPost.setTitle(Forwhatthispost);
            myPost.setTextmore(textmoe);
            myPost.setDatepost(new Date());
           // myPost.setLocation(new location);
            myPost.setLocation(loc);
            //createPost(myPost);
          uploadImage(toUploadimageUri);

        }
    }

    private void pickImageFromGallery(){ //request image
        //intent to pick image
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        //rmoz lano bdo sora (image)
        startActivityForResult(intent,IMAGE_PICK_CODE); //request code (image) (open gallery
    }

    @Override
    public void onRequestPermissionsResult (int requestCode, @NonNull String[] permissions,@NonNull int[] grantResults) { // if it is ok to access gallery
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);// permission from the user to access the gallery
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted/ok
                    pickImageFromGallery();
                } else {
                    // permission was denied/no
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){ //is ok+ is image
        super.onActivityResult(requestCode,resultCode,data); // is ok or cancel
        if(resultCode== RESULT_OK&& requestCode==IMAGE_PICK_CODE){//is that code for a pic
            //set image to image view
            toUploadimageUri = data.getData();// take the pic
            imageView.setImageURI(data.getData()); //put it in the button
        }
    }
    //upload: 5
    private void uploadImage(Uri filePath) { //yarfa3 3la firebase

        if(filePath != null)// if the user pick image
        {
            // a dialog showing a progress
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            //moving to firebase storage
            FirebaseStorage storage= FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            uploadTask=ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {//تشتغل حسب اللسينر
                                    downladuri = task.getResult();
                                    myPost.setImage(downladuri.toString());
                                    createPost(myPost);

                                }
                            });

                            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        //listener that is called
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }else
        {
            p.setImage("");
            createPost(p);
        }
    }

    private void createPost(final MyPost post){
        //1.
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference =
                database.getReference();
        //to get the user uid (or other details like email)
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        post.setEmployee(uid);

        String key = reference.child("posts").push().getKey();
        post.setKey(key);
        reference.child("posts").child(key).setValue(post).addOnCompleteListener(AddPost.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "add successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "add failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            }
        });
    }

}
