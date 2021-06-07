package hamood.malak.muslimapp.MyUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import hamood.malak.muslimapp.MyUtils.MyValidations;
import hamood.malak.muslimapp.R;

public class SignIn extends AppCompatActivity {
    private Button logIn,signUP;
    private EditText Password,Email;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in); //3

        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        logIn=findViewById(R.id.logIn);
        signUP=findViewById(R.id.signUP);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();

            }
        });
        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignIn.this,SignUp.class);
                startActivity(i);
            }
        });

    }
    //5
    private void validateForm()
    {
        String password=Password.getText().toString();
        String email=Email.getText().toString();
        boolean isOk=true;
        if(email.length()<5 || email.indexOf('@')==0 || email.indexOf('@')>=email.length()-2 ||
                email.indexOf('.')==0 || email.indexOf('.')>=email.length()-1 || email.lastIndexOf('.')<email.indexOf('@'))
        {
            isOk=false;
            Email.setError("wrong Gmail syntax");
        }
//        MyValidations myValidations=new MyValidations();
//        if (!myValidations.validatePassword(password)) {
//            isOk = false;
//            Password.setError("Invalid Password");
//        }
        if ((isOk)){
            signIn(email,password);
        }


    }

    private void signIn(String email, String password) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i=new Intent(SignIn.this,OwnerMainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(SignIn.this,"FAILED",Toast.LENGTH_SHORT).show();
                    Email.setError(task.getException().getMessage());
                }
            }
        });
    }


}
