package malang.moe.repay.activity;

import android.Manifest;
import android.app.Activity;
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
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    LinearLayout divier;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView sendSMS, sendCall, date, desc;
    ImageView health, bokji, hospital, photo;
    public static Activity activity;
    boolean isParent;
    boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        setActionBar();
        setDefault();
    }

    private void setDefault() {
        divier = (LinearLayout) findViewById(R.id.div_divider);
        date = (TextView) findViewById(R.id.date_text);
        desc = (TextView) findViewById(R.id.description_text);
        health = (ImageView) findViewById(R.id.main_health);
        bokji = (ImageView) findViewById(R.id.main_bokji);
        photo = (ImageView) findViewById(R.id.main_photo);
        sharedPreferences = getSharedPreferences("Repay", 0);

        number = sharedPreferences.getString("parent_number", "");
        isParent = sharedPreferences.getBoolean("isParent", false);
        health = (ImageView) findViewById(R.id.main_health);
        bokji = (ImageView) findViewById(R.id.main_bokji);
        photo = (ImageView) findViewById(R.id.main_photo);
        hospital = (ImageView) findViewById(R.id.main_hospital);
        if (!isParent) bokji.setVisibility(View.VISIBLE);
        else hospital.setVisibility(View.VISIBLE);
        sendCall = (TextView) findViewById(R.id.main_send_call);
        sendSMS = (TextView) findViewById(R.id.main_send_sms);
        sendCall.setOnClickListener(this);
        sendSMS.setOnClickListener(this);
        health.setOnClickListener(this);
        bokji.setOnClickListener(this);
        photo.setOnClickListener(this);
        hospital.setOnClickListener(this);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main_drawer_stretch:
                        startActivity(new Intent(getApplicationContext(), HealthSelectActivity.class));
                        break;
                    case R.id.main_drawer_facilities:
                        startActivity(new Intent(getApplicationContext(), WelfareListActivity.class));
                        break;
                    case R.id.main_drawer_medical:
                        startActivity(new Intent(getApplicationContext(), MedicalCenterListActivity.class));
                        break;
                    case R.id.main_drawer_pictures:
                        startActivity(new Intent(getApplicationContext(), PictureViewActivity.class));
                        break;
                    case R.id.main_drawer_settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        break;
                    case R.id.main_drawer_tutorial:
                        startActivity(new Intent(getApplicationContext(), Tutorial.class));
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
        ab.setTitle("효돌이");
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
                } else if (number.trim().equals("")) {
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
                } else startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number)));
                break;
            case R.id.main_hospital:
                startActivity(new Intent(getApplicationContext(), MedicalCenterListActivity.class));
                break;
            case R.id.main_health:
                startActivity(new Intent(getApplicationContext(), HealthSelectActivity.class));
                break;
            case R.id.main_bokji:
                startActivity(new Intent(getApplicationContext(), WelfareListActivity.class));
                break;
            case R.id.main_photo:
                startActivity(new Intent(getApplicationContext(), PictureViewActivity.class));
                break;
        }
    }

    public void onPause() {
        dlDrawer.closeDrawer(GravityCompat.START);
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        number = sharedPreferences.getString("parent_number", "");
        if(number.trim().equals("")){
            desc.setText("전화번호가\n설정되지 않았습니다");
            date.setVisibility(View.GONE);
            divier.setVisibility(View.GONE);
        } else {
            desc.setText("서로 연락한지\n8일이 지났습니다");
            divier.setVisibility(View.VISIBLE);
            date.setText("8일");
            date.setVisibility(View.VISIBLE);
        }
        isFirst = sharedPreferences.getBoolean("isFirst", true);
        if(isFirst) startActivity(new Intent(getApplicationContext(), Tutorial.class));

    }
}
