package malang.moe.repay.activity;

<<<<<<< HEAD
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import malang.moe.repay.R;

public class ExcercisePlaceShowActivity extends AppCompatActivity {
=======
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import malang.moe.repay.R;

public class ExcercisePlaceShowActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Intent intent;
    Double la, lo;
    TextView titleText, addressText;
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        setContentView(R.layout.activity_excercise_place_show);
=======
        setContentView(R.layout.activity_map_show);
        setActionbar(getSupportActionBar());
        intent = getIntent();
        la = Double.parseDouble(intent.getStringExtra("LA"));
        lo = Double.parseDouble(intent.getStringExtra("LO"));
        titleText = (TextView) findViewById(R.id.map_title);
        addressText = (TextView) findViewById(R.id.map_content);
        titleText.setText(intent.getStringExtra("title"));
        addressText.setText(intent.getStringExtra("address"));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(lo, la);
        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16));
    }

    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("지도 보기");
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
>>>>>>> 6787408c2c6019d8d1a4530bcff49587082d0a68
    }
}
