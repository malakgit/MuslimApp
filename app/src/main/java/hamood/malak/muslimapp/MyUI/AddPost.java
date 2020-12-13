package hamood.malak.muslimapp.MyUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import hamood.malak.muslimapp.MyUtils.MyPost;
import hamood.malak.muslimapp.MyUtils.MyValidations;
import hamood.malak.muslimapp.R;

public class AddPost extends AppCompatActivity {
    private Button Post,Draft,Code;
    private ImageButton imagebttn;
    private TextView PostTitle,TextMore;
    private SeekBar seekBarlike;
    private EditText seeklike;


    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedByInstanceState){
        super.onCreate(savedByInstanceState);
        setContentView(R.layout.add_post);
        //find view by id
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



}
