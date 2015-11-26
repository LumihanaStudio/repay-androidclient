package malang.moe.repay.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import malang.moe.repay.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    NavigationView navigationView;
    TextView sendSMS, sendCall;
    ImageView health, bokji, photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        setDefault();
    }

    private void setDefault() {
        health = (ImageView)findViewById(R.id.main_health);
        bokji = (ImageView)findViewById(R.id.main_bokji);
        photo = (ImageView)findViewById(R.id.main_photo);
        sendCall = (TextView) findViewById(R.id.main_send_call);
        sendSMS = (TextView)findViewById(R.id.main_send_sms);
        sendCall.setOnClickListener(this);
        sendSMS.setOnClickListener(this);
        health.setOnClickListener(this);
        bokji.setOnClickListener(this);
        photo.setOnClickListener(this);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_drawer_stretch:
                        Toast.makeText(MainActivity.this, "스트레칭", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_drawer_facilities:
                        Toast.makeText(MainActivity.this, "복지시설", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_drawer_pictures:
                        Toast.makeText(MainActivity.this, "추억사진", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_drawer_settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        break;
                    case R.id.main_drawer_tutorial:
                        startActivity(new Intent(getApplicationContext(), TutorialActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    private void setActionBar() {
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar ab = getSupportActionBar();
        if (null != ab) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        ab.setTitle("효은");
        ab.setElevation(0);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(dlDrawer.isDrawerOpen(GravityCompat.START)) dlDrawer.closeDrawer(GravityCompat.START);
                else dlDrawer.openDrawer(GravityCompat.START);
                return true;
            default:
                Toast.makeText(MainActivity.this, item.getItemId(), Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_send_sms:
                Toast.makeText(MainActivity.this, "SMS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_send_call:
                Toast.makeText(MainActivity.this, "CALL", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_health:
                break;
            case R.id.main_bokji:
                break;
            case R.id.main_photo:
                break;
            default:
                Toast.makeText(MainActivity.this, v.getId()+""  , Toast.LENGTH_SHORT).show();
        }
    }

    public void onResume(){
        dlDrawer.closeDrawer(Gravity.START);
        super.onResume();
    }
}
