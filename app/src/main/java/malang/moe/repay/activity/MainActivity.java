package malang.moe.repay.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
<<<<<<< HEAD
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
=======
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import malang.moe.repay.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    NavigationView navigationView;
    String number;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView sendSMS, sendCall;
    ImageView health, bokji, photo;
<<<<<<< HEAD
=======

>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBar();
        setDefault();
    }

    private void setDefault() {
<<<<<<< HEAD
        health = (ImageView)findViewById(R.id.main_health);
        bokji = (ImageView)findViewById(R.id.main_bokji);
        photo = (ImageView)findViewById(R.id.main_photo);
=======
        sharedPreferences = getSharedPreferences("Repay", 0);
        number = sharedPreferences.getString("parent_number", "");
        health = (ImageView) findViewById(R.id.main_health);
        bokji = (ImageView) findViewById(R.id.main_bokji);
        photo = (ImageView) findViewById(R.id.main_photo);
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
        sendCall = (TextView) findViewById(R.id.main_send_call);
        sendSMS = (TextView) findViewById(R.id.main_send_sms);
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
                        startActivity(new Intent(getApplicationContext(), HealthSelectActivity.class));
                        break;
                    case R.id.main_drawer_facilities:
<<<<<<< HEAD
                        Toast.makeText(MainActivity.this, "복지시설", Toast.LENGTH_SHORT).show();
=======
                        startActivity(new Intent(getApplicationContext(), MedicalCenterListActivity.class));
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
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
                if (dlDrawer.isDrawerOpen(GravityCompat.START))
                    dlDrawer.closeDrawer(GravityCompat.START);
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
                if (number.trim().equals(""))
                    new MaterialDialog.Builder(MainActivity.this)
                            .content("아직 번호가 설정되지 않았습니다! 설정창에서 부모님의 번호를 설정해주세요!")
                            .positiveText("설정")
                            .negativeText("취소")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                                }
                            })
                            .show();
                else {
                    final View view = getLayoutInflater().inflate(R.layout.view_main_gettext, null);
                    new MaterialDialog.Builder(MainActivity.this)
                            .title("메세지 전송")
                            .customView(view, false)
                            .positiveText("전송")
                            .negativeText("취소")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    EditText asdf = (EditText) view.findViewById(R.id.parent_number);
                                    String result = asdf.getText().toString().trim();
                                    if (!result.equals("")) {
                                        smsManager.sendTextMessage(number, null, asdf.getText().toString(), null, null);
                                        Toast.makeText(MainActivity.this, "전송되었습니다!", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(MainActivity.this, "메시지가 입력되지 않았습니다!", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }
                break;
            case R.id.main_send_call:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                } else startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+number)));
                break;
            case R.id.main_health:
                startActivity(new Intent(getApplicationContext(), HealthSelectActivity.class));
                break;
            case R.id.main_bokji:
                startActivity(new Intent(getApplicationContext(), MedicalCenterListActivity.class));
                break;
            case R.id.main_photo:

                break;
            case R.id.main_health:
                break;
            case R.id.main_bokji:
                break;
            case R.id.main_photo:
                break;
            default:
                Toast.makeText(MainActivity.this, v.getId() + "", Toast.LENGTH_SHORT).show();
        }
    }

<<<<<<< HEAD
    public void onResume(){
        dlDrawer.closeDrawer(Gravity.START);
        super.onResume();
=======
    public void onPause() {
        dlDrawer.closeDrawer(GravityCompat.START);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        number = sharedPreferences.getString("parent_number", "");
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
    }
}
