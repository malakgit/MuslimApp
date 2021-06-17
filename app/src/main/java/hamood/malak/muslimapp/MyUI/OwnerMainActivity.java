package hamood.malak.muslimapp.MyUI;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import hamood.malak.muslimapp.MyUI.ui.main.SectionsPagerAdapter;
import hamood.malak.muslimapp.R;

public class OwnerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fav);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(getApplicationContext(),AddPost.class));
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.message:
                Toast
                        .makeText(
                                getApplicationContext(),
                                "Shows share icon",
                                Toast.LENGTH_SHORT)
                        .show();
                return true;

            case R.id.picture:
                Toast
                        .makeText(
                                getApplicationContext(),
                                "Shows image icon",
                                Toast.LENGTH_SHORT)
                        .show();

                return (true);

            case R.id.mode:
                Toast
                        .makeText(
                                getApplicationContext(),
                                "Shows call icon",
                                Toast.LENGTH_SHORT)
                        .show();
                return (true);

            case R.id.about:
                Toast
                        .makeText(
                                getApplicationContext(),
                                "calculator menu",
                                Toast.LENGTH_SHORT)
                        .show();
                return (true);

            case R.id.exit:
                finish();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}
