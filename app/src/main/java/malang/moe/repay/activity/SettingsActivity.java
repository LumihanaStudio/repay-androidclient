package malang.moe.repay.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import malang.moe.repay.R;
/**
 * Created by kotohana5706 on 2015. 11. 21.
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class SettingsActivity extends AppCompatActivity {

    public class SettingsData{
        public int icon;
        public String title;
        public String description;
        public SettingsData(int icon, String title, String description){
            this.icon = icon;
            this.title = title;
            this.description = description;
        }
    }

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    ListView listview;
    ArrayList<SettingsData> array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setDefault();
        setActionbar(getSupportActionBar());
    }

    private void setDefault() {
        sharedPref = getSharedPreferences("Repay", 0);
        editor = sharedPref.edit();
        listview = (ListView)findViewById(R.id.settings_listview);
        array = new ArrayList<>();
        array.add(new SettingsData(1, "로그인이 필요합니다!", "모든 서비스를 이용하려면 로그인해주세요!"));
        array.add(new SettingsData(1, "부모 전화번호 설정!", "현재 설정되어있지 않습니다."));
        array.add(new SettingsData(1, "위치 설정!", "현재 설정되어 있지 않습니다."));
        SettingsAdapter adapter = new SettingsAdapter(SettingsActivity.this, array);
        listview.setAdapter(adapter);
    }

    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("설정");
    }


    public class SettingsAdapter extends ArrayAdapter<SettingsData> {
        // 레이아웃 XML을 읽어들이기 위한 객체
        private LayoutInflater mInflater;

        public SettingsAdapter(Context context, ArrayList<SettingsData> object) {
            // 상위 클래스의 초기화 과정
            // context, 0, 자료구조
            super(context, 0, object);
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            View view = null;
            // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
            if (v == null) {
                // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
                view = mInflater.inflate(R.layout.listview_settings_content, null);
            } else {
                view = v;
            }
            // 자료를 받는다.
            final SettingsData data = this.getItem(position);
            if (data != null) {
                //화면 출력
                TextView title = (TextView)view.findViewById(R.id.settings_listview_title);
                TextView description = (TextView)view.findViewById(R.id.settings_listview_content);

                title.setText(data.title);
                description.setText(data.description);
            }
            return view;
        }
    }

}
