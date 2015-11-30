package malang.moe.repay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import malang.moe.repay.R;
import malang.moe.repay.adapter.ExcercisePlaceAdapter;
import malang.moe.repay.data.ExcercisePlaceData;
import malang.moe.repay.data.MedicalCenter_Response;
import malang.moe.repay.data.MedicalRow;
import malang.moe.repay.data.TravelPark_Response;
import malang.moe.repay.data.TravelRow;
import malang.moe.repay.data.WelfareCenter_Response;
import malang.moe.repay.data.WelfareRow;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ExcercisePlaceListActivity extends AppCompatActivity {

    // Widgets
    View navView;
    TextView asdf;
    ExcercisePlaceAdapter adapter;
    ArrayList<ExcercisePlaceData> arrayList;
    ListView listView;
    MaterialDialog loading;

    // Service
    NetworkService service;
    Retrofit retrofit;
    Call<MedicalCenter_Response> medicalCenter_Response;
    Call<TravelPark_Response> travelPark_Response;
    Call<WelfareCenter_Response> welfareCenter_Response;
    List<TravelRow> travelRows;
    List<MedicalRow> medicalRows;
    List<WelfareRow> welfareRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_place_list);
        setDefault();
        setRestAdapter();
        setActionbar(getSupportActionBar());
        parseData();
    }

//
    private void parseData() {
        travelPark_Response.enqueue(new Callback<TravelPark_Response>() {
            @Override
            public void onResponse(Response<TravelPark_Response> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    travelRows = response.body().travelPark.row;
                    setListView(travelRows);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://data.gwd.go.kr/apiservice/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);

        travelPark_Response = service.getTravelList(1, 1000);
        medicalCenter_Response = service.getMedicalList(1, 1000);
        welfareCenter_Response = service.getWelfareList(1, 1000);
    }

    private void setDefault() {
        loading = new MaterialDialog.Builder(ExcercisePlaceListActivity.this)
                .title("데이터를 로드합니다")
                .content("잠시만 기다려주세요")
                .progress(true, 0)
                .cancelable(false)
                .show();
        listView = (ListView) findViewById(R.id.excercise_place_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.excercise_listview_place_title);
                TextView address = (TextView) view.findViewById(R.id.excercise_listview_place_content);
                TextView la = (TextView) view.findViewById(R.id.excercise_listview_place_la);
                TextView lo = (TextView) view.findViewById(R.id.excercise_listview_place_lo);
                startActivity(new Intent(getApplicationContext(), ExcercisePlaceShowActivity.class)
                .putExtra("LA", la.getText().toString().trim())
                .putExtra("LO", lo.getText().toString().trim())
                .putExtra("title", title.getText().toString().trim())
                .putExtra("address", address.getText().toString().trim()));
            }
        });
    }

    public void setListView(List<TravelRow> list) {
        arrayList = new ArrayList<>();
        for(TravelRow travelRow : list){
            arrayList.add(new ExcercisePlaceData(travelRow.PARK_NM, travelRow.PROVINCIALCAPITAL_ROADADDR, travelRow.LOC_ARE, travelRow.LA, travelRow.LO));
        }
        adapter = new ExcercisePlaceAdapter(ExcercisePlaceListActivity.this, arrayList);
        listView.setAdapter(adapter);
        loading.dismiss();
    }

    public void setActionbar(ActionBar actionbar){
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("밖에서 운동하기");
    }
}
