package malang.moe.repay.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import malang.moe.repay.R;
import malang.moe.repay.utils.AnimateNetworkImageView;
import malang.moe.repay.utils.ImageSingleTon;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PictureViewActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> urlArr = new ArrayList<>();
    ArrayList<String> textArr = new ArrayList<>();
    ImageLoader loader;
    Call<List<String>> listArticle;
    SharedPreferences sharedPreferences;
    NetworkService service;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);
        setRestAdapter();
        setDefault();
        setActionBar(getSupportActionBar());
    }

    private void setActionBar(ActionBar ab) {
        ab.setTitle("추억 보기");
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://bamtoll.moe:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
        listArticle = service.listArticle();
    }

    private void setDefault() {
        gridView = (GridView) findViewById(R.id.image_view_gridview);
        listArticle.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Response<List<String>> response, Retrofit retrofit) {
                for (String string : response.body()) {
                    urlArr.add("http://bamtoll.moe/imgs/" + string);
                }
                loader = ImageSingleTon.getInstance(PictureViewActivity.this).getImageLoader();
                gridView.setAdapter(new ImageGridAdapter());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    public class ImageGridAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public ImageGridAdapter() {
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return urlArr.size();    //그리드뷰에 출력할 목록 수
        }

        @Override
        public Object getItem(int position) {
            return urlArr.get(position);    //아이템을 호출할 때 사용하는 메소드
        }

        @Override
        public long getItemId(int position) {
            return position;    //아이템의 아이디를 구할 때 사용하는 메소드
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.gridview_view, parent, false);
            }
            AnimateNetworkImageView image = (AnimateNetworkImageView) convertView.findViewById(R.id.gridview_image);
            image.setImageUrl(urlArr.get(position), loader);
            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add,  menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.plus:
                startActivity(new Intent(getApplicationContext(), PictureAddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        setRestAdapter();
        setDefault();
        super.onResume();
    }
}
