package malang.moe.repay.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import malang.moe.repay.R;
import malang.moe.repay.utils.AnimateNetworkImageView;
import malang.moe.repay.utils.ImageSingleTon;

public class PictureViewActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<String> urlArr = new ArrayList<>();
    ArrayList<String> textArr = new ArrayList<>();
    ImageLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);
        setDefault();
    }

    private void setDefault() {
        gridView = (GridView) findViewById(R.id.image_view_gridview);
        for (int i = 0; i < 5; i++) {
            urlArr.add("http://bamtoll.moe/asdf.jpg");
            textArr.add(i + "번째 졸려ㅕㅑ");
        }
        loader = ImageSingleTon.getInstance(this).getImageLoader();
        gridView.setAdapter(new ImageGridAdapter());
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
            TextView title = (TextView) convertView.findViewById(R.id.gridview_title);
            image.setImageUrl(urlArr.get(position), loader);
            title.setText(textArr.get(position));
            return convertView;
        }
    }
}
