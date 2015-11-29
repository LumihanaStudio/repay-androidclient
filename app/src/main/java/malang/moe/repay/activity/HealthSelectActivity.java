package malang.moe.repay.activity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
=======
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
import android.view.View;
import android.widget.RelativeLayout;

import malang.moe.repay.R;

public class HealthSelectActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout inside, outside;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_select);
<<<<<<< HEAD
        setDefault();
    }

=======
        setActionBar(getSupportActionBar());
        setDefault();
    }

    private void setActionBar(ActionBar supportActionBar) {
        supportActionBar.setTitle("운동하기");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
    private void setDefault() {
        inside = (RelativeLayout)findViewById(R.id.health_select_inside);
        outside = (RelativeLayout)findViewById(R.id.health_select_outside);
        outside.setOnClickListener(this);
        inside.setOnClickListener(this);
    }

    @Override
<<<<<<< HEAD
=======
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.health_select_inside:
                startActivity(new Intent(getApplicationContext(), ExcerciseVideoListActivity.class));
                break;
            case R.id.health_select_outside:
<<<<<<< HEAD
                startActivity(new Intent(getApplicationContext(), ))
=======
                startActivity(new Intent(getApplicationContext(), ExcercisePlaceListActivity.class));
                break;
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
        }
    }
}
