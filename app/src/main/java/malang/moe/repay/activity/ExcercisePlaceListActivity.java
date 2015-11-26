package malang.moe.repay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import malang.moe.repay.R;
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
        parseData();
    }
//
    private void parseData() {
        medicalCenter_Response.enqueue(new Callback<MedicalCenter_Response>() {
            @Override
            public void onResponse(Response<MedicalCenter_Response> response, Retrofit retrofit) {
                Log.e("asdf", response.code()+"");
                if(response.code()==200){
                    medicalRows = response.body().medicalCenter.row;
                    for(MedicalRow medicalRow : medicalRows){
                        Log.e("asdf", medicalRow.MED_NM);
                        asdf.append(medicalRow.MED_NM+"\n");
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ExcercisePlaceListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
//    private void parseData() {
//        travelPark_Response.enqueue(new Callback<TravelPark_Response>() {
//            @Override
//            public void onResponse(Response<TravelPark_Response> response, Retrofit retrofit) {
//                if (response.code() == 200) {
//                    travelRows = response.body().travelPark.row;
//                    for (TravelRow travelRow : travelRows) {
//                        asdf.append(travelRow.PARK_NM);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://data.gwd.go.kr/apiservice/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);

        travelPark_Response = service.getTravelList(1,1000);
        medicalCenter_Response = service.getMedicalList(1,1000);
        welfareCenter_Response = service.getWelfareList(1,1000);
    }

    private void setDefault() {
        asdf = (TextView)findViewById(R.id.asdf);
    }
}
