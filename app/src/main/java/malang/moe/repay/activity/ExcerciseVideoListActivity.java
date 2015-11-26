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

import java.util.ArrayList;
import java.util.List;

import malang.moe.repay.R;
import malang.moe.repay.adapter.ExcerciseVideoAdapter;
import malang.moe.repay.data.ExcerciseVideoData;
import malang.moe.repay.data.ExcerciseVideoResponse;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ExcerciseVideoListActivity extends AppCompatActivity {
    Retrofit retrofit;
    NetworkService service;
    Call<ExcerciseVideoResponse> response;
    ExcerciseVideoAdapter adapter;
    ListView listview;
    ArrayList<ExcerciseVideoData> arrayList;
    List<ExcerciseVideoResponse.Items> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_video);

        setDefault();
        setRestAdapter();
        setActionbar(getSupportActionBar());
        parseData();
    }

    private void parseData() {
        arrayList = new ArrayList<>();
        response.enqueue(new Callback<ExcerciseVideoResponse>() {
            @Override
            public void onResponse(Response<ExcerciseVideoResponse> response, Retrofit retrofit) {
                if(response.code()==200){
                    list = response.body().items;
                    for(ExcerciseVideoResponse.Items items : list){
                        arrayList.add(new ExcerciseVideoData("", items.snippet.title, items.snippet.description, items.snippet.channelTitle, items.id.videoId));
                    }
                    adapter = new ExcerciseVideoAdapter(ExcerciseVideoListActivity.this, arrayList);
                    listview.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(ExcerciseVideoListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDefault() {
        listview = (ListView)findViewById(R.id.exercise_listview);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title=  (TextView)view.findViewById(R.id.excercise_listview_title);
                TextView des = (TextView)view.findViewById(R.id.excercise_listview_content);
                TextView videoId = (TextView)view.findViewById(R.id.excercise_listview_videoId);
                startActivity(new Intent(getApplicationContext(), ExcerciseVideoShowActivity.class)
                        .putExtra("title", title.getText().toString().trim())
                        .putExtra("content", des.getText().toString().trim())
                        .putExtra("videoId", videoId.getText().toString().trim()));
            }
        });
    }
    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);

        response = service.getExcerciseVideoList("노인 체조", "");
    }
    public void setActionbar(ActionBar actionbar) {
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("집에서 운동하기");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
