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
    private Button Post,Draft,Code,uploadbtn;
    private ImageButton imagebttn;
    private TextView PostTitle,TextMore;
    private SeekBar seekBarlike;
    private EditText seeklike;


    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedByInstanceState){
        super.onCreate(savedByInstanceState);
        setContentView(R.layout.add_post);
        //find view by id
        uploadbtn=findViewById(R.id.uploadbtn);
        Draft=findViewById(R.id.Draft);
        imagebttn=findViewById(R.id.imagebttn);
        Code=findViewById(R.id.Code);
        PostTitle=findViewById(R.id.PostTitle);
        TextMore=findViewById(R.id.TextMore);
        seekBarlike=findViewById(R.id.seekBarlike);
        seeklike=findViewById(R.id.seeklike);
        //listeners
                Post.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new MyValidations();
        }
    });
}
    public void validationForm()
    {
        String Forwhatthispost= PostTitle.getText().toString();
        String textmoe= TextMore.getText().toString();
        int like=seekBarlike.getProgress();
        boolean isOk=false;
        if (Forwhatthispost.length()==0){
            isOk=false;
            PostTitle.setError("at least one sentence");
        }
        if (isOk){
            MyPost myPost=new MyPost();
        }
    }
    public class PickImageExActivity extends AppCompatActivity{
        private ImageView imageView;
        private Button chooseimage_btn;

        private static final int IMAGE_PICK_CODE =1000;
        private static final int PERMISSION_CODE =1001;

        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_post);

            imageView=findViewById(R.id.imageView);
            chooseimage_btn=findViewById(R.id.chooseimage_btn);
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
            imagebttn.setImageURI(data.getData());
        }
    }
    private void pickImageFromGallery(){
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);


    }




}
