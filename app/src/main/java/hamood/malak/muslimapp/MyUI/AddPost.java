package hamood.malak.muslimapp.MyUI;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import androidx.appcompat.app.AppCompatActivity;

import hamood.malak.muslimapp.MyUtils.MyPost;
import hamood.malak.muslimapp.MyUtils.MyValidations;
import hamood.malak.muslimapp.R;

public class AddPost extends AppCompatActivity {
    private static final int IMAGE_PICK_CODE =1000 ;
    private static final int PERMISSION_CODE =1001;
    private Button Post;
    private ImageView imageView;
    private TextView location;
    private TextView code;
    private Button chooseimage_btn;
    private TextView PostTitle,TextMore;



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
        imageView=findViewById(R.id.imageView);
        chooseimage_btn=findViewById(R.id.chooseimage_btn);
        chooseimage_btn.setOnClickListener(new View.OnClickListener() {
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
        imageView.setOnClickListener(new View.OnClickListener() {
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
    public void validationForm()
    {
        String Forwhatthispost= PostTitle.getText().toString();
        String textmoe= TextMore.getText().toString();


        boolean isOk=false;
        if (Forwhatthispost.length()==0){
            isOk=false;
            PostTitle.setError("at least one sentence");
        }
        if (isOk){
            MyPost myPost=new MyPost();
            myPost.setTitle(Forwhatthispost);
            myPost.setTextmore();
            createTask(t);
//            MyTask task=new MyTask();
//            task.setCreatedAt(new Date());
//            //task.setDueDate(new Date(date));
//            task.setText(text);
//            task.setTitle(title);
//            task.setImportant(important);
//            task.setNecessary(necessary);
//
//            //get user email to set is as the owner of this task
//            FirebaseAuth auth = FirebaseAuth.getInstance();
//            task.setOwner(auth.getCurrentUser().getEmail());
//// to get the database root reference
//            DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
//
//           //to get uid(universal id)
//            String key=reference.child("MyTasks").push().getKey();
//            task.setKey(key);
//
//            reference.child("MyTasks").child(key).setValue(task).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful())
//                    {
//                        Toast.makeText(AddTaskActivity.this, "Add Successful", Toast.LENGTH_LONG).show();
//                        Intent intent=new Intent(getBaseContext(),AddTaskActivity.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//                        Toast.makeText(AddTaskActivity.this, "Add Faild", Toast.LENGTH_LONG).show();
//
//                    }
//                }
//            });
//
//



        }
    }


    public void onRequestPermissionsResult (int requestCode, String[] permissions,int[] grantResults){
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    //permission was denied
                    pickImageFromGallery();
                }
                else{
                    // permission was denied
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();

                }
            }
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode== RESULT_OK&& requestCode==IMAGE_PICK_CODE){
            //set image to image view
            imageView.setImageURI(data.getData());
        }
    }
    private void pickImageFromGallery(){
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);


    }




}
