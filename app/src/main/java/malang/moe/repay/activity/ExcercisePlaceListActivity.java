package malang.moe.repay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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
    ListView listView;
    ExcercisePlaceAdapter adapter;
    ArrayList<ExcercisePlaceData> arrayList;
    TextView asdf;
    // Service
    NetworkService service;
    Retrofit retrofit;
    List<MedicalRow> medicalRows;
    Call<MedicalCenter_Response> medicalCenter_Response;
    Call<TravelPark_Response> travelPark_Response;
    Call<WelfareCenter_Response> welfareCenter_Response;
    List<TravelRow> travelRows;
    MaterialDialog loading;
    List<WelfareRow> welfareRows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_place_list);
        setDefault();
        setActionbar(getSupportActionBar());
        setRestAdapter();
        parseData();
    }

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
            }
        });
    }

    public void setActionbar(ActionBar actionbar) {
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("밖에서 운동하기");
    }

    public void setListView(List<TravelRow> list) {
        arrayList = new ArrayList<>();
        for (TravelRow travelRow : list) {
            arrayList.add(new ExcercisePlaceData(travelRow.PARK_NM, travelRow.PROVINCIALCAPITAL_ROADADDR, travelRow.LOC_ARE,
                    travelRow.LA, travelRow.LO));
        }
        adapter = new ExcercisePlaceAdapter(ExcercisePlaceListActivity.this, arrayList);
        listView.setAdapter(adapter);
        loading.dismiss();
    }
//    private void parseData() {
//        welfareCenter_Response.enqueue(new Callback<WelfareCenter_Response>() {
//            @Override
//            public void onResponse(Response<WelfareCenter_Response> response, Retrofit retrofit) {
//                Log.e("asdf", response.code()+"");
//                if(response.code()==200){
//                    welfareRows = response.body().welfareCenter.row;
//                }
//                for(WelfareRow welfareRow : welfareRows){
//                    asdf.append(welfareRow.WELFARE_NM);
//                }
//            }
//
//
//            @Override
//            public void onFailure(Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//


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
                .progress(true, 0).cancelable(false)
                .show();
        listView = (ListView) findViewById(R.id.excercise_place_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView la = (TextView) view.findViewById(R.id.excercise_listview_place_la);
                TextView lo = (TextView) view.findViewById(R.id.excercise_listview_place_lo);
                TextView title = (TextView) view.findViewById(R.id.excercise_listview_place_title);
                TextView address = (TextView) view.findViewById(R.id.excercise_listview_place_content);
                startActivity(new Intent(getApplicationContext(), ExcercisePlaceShowActivity.class)
                        .putExtra("LA", la.getText().toString().trim())
                        .putExtra("LO", lo.getText().toString().trim())
                        .putExtra("title", title.getText().toString())
                        .putExtra("address", address.getText().toString()));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
