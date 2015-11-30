package malang.moe.repay.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;

import malang.moe.repay.R;
import malang.moe.repay.adapter.WelfareCenterAdapter;
import malang.moe.repay.data.WelfareCenter_Response;
import malang.moe.repay.data.WelfareData;
import malang.moe.repay.data.WelfareRow;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class WelfareListActivity extends AppCompatActivity {

    ListView welfareList;
    ArrayList<WelfareData> arrayList;
    WelfareCenterAdapter adapter;
    List<WelfareRow> welfareRows;
    Call<WelfareCenter_Response> welfareCenter_Response;
    MaterialDialog loading;
    Retrofit retrofit;
    NetworkService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_list);
        setDefault();
        setRestAdapter();
        parseData();
    }

    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://data.gwd.go.kr/apiservice/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);

        welfareCenter_Response = service.getWelfareList(1, 1000);
    }

    private void setDefault() {
        welfareList = (ListView) findViewById(R.id.welfare_listview);
        loading = new MaterialDialog.Builder(WelfareListActivity.this)
                .title("데이터를 로드합니다")
                .content("잠시만 기다려주세요")
                .progress(true, 0)
                .cancelable(false)
                .show();
        welfareList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.welfare_listview_place_title);
                TextView address = (TextView) view.findViewById(R.id.welfare_listview_place_content);
                final TextView tel = (TextView) view.findViewById(R.id.welfare_listview_place_tel);
                new MaterialDialog.Builder(WelfareListActivity.this)
                        .title(title.getText().toString().trim())
                        .content(address.getText().toString().trim() + "\n" + tel.getText().toString().trim())
                        .positiveText("전화걸기")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                if (ActivityCompat.checkSelfPermission(WelfareListActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel.getText().toString().trim())));
                            }
                        })
                        .negativeText("취소")
                        .show();
            }
        });
    }

    private void parseData() {
        welfareCenter_Response.enqueue(new Callback<WelfareCenter_Response>() {
            @Override
            public void onResponse(Response<WelfareCenter_Response> response, Retrofit retrofit) {
                if (response.code() == 200) {
                    welfareRows = response.body().welfareCenter.row;
                    setListview(welfareRows);
                }
            }


            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setListview(List<WelfareRow> listview) {
        arrayList = new ArrayList<>();
        for (WelfareRow row : listview) {
            arrayList.add(new WelfareData(row.WELFARE_NM, row.ADDRESS, row.TEL));
        }
        adapter = new WelfareCenterAdapter(WelfareListActivity.this, arrayList);
        welfareList.setAdapter(adapter);
        loading.dismiss();
    }
}
