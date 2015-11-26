package malang.moe.repay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import malang.moe.repay.R;

public class HealthSelectActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout inside, outside;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_select);
        setActionBar(getSupportActionBar());
        setDefault();
    }

    private void setActionBar(ActionBar supportActionBar) {
        supportActionBar.setTitle("운동하기");
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setDefault() {
        inside = (RelativeLayout)findViewById(R.id.health_select_inside);
        outside = (RelativeLayout)findViewById(R.id.health_select_outside);
        outside.setOnClickListener(this);
        inside.setOnClickListener(this);
    }

    @Override
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.health_select_inside:
                startActivity(new Intent(getApplicationContext(), ExcerciseVideoListActivity.class));
                break;
            case R.id.health_select_outside:
                startActivity(new Intent(getApplicationContext(), ExcercisePlaceListActivity.class));
                break;
        }
    }
}
