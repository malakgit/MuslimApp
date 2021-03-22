package hamood.malak.muslimapp.MyUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hamood.malak.muslimapp.R;

public class MainActivity extends AppCompatActivity {

     android.os.Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        handler = new android.os.Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, SignIn.class);
//                startActivity(intent);
//                finish();
//                return;
//
//            }
//        }, 3000);

    }

    @Override
    protected void onStop() {
        super.onStop();
//        handler.getLooper().getThread().interrupt();
    }
}