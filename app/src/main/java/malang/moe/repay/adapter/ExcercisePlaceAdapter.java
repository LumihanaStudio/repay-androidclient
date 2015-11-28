package malang.moe.repay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import malang.moe.repay.R;
import malang.moe.repay.data.ExcercisePlaceData;

/**
 * Created by kotohana5706 on 2015. 11. 21.
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class ExcercisePlaceAdapter extends ArrayAdapter<ExcercisePlaceData> {
    // 레이아웃 XML을 읽어들이기 위한 객체
    private LayoutInflater mInflater;

    public ExcercisePlaceAdapter(Context context, ArrayList<ExcercisePlaceData> object) {
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
            view = mInflater.inflate(R.layout.listview_excerciseplace_content, null);
        } else {
            view = v;
        }
        // 자료를 받는다.
        final ExcercisePlaceData data = this.getItem(position);
        if (data != null) {
            //화면 출력
            TextView title = (TextView) view.findViewById(R.id.excercise_listview_place_title);
            TextView address = (TextView) view.findViewById(R.id.excercise_listview_place_content);
            TextView la = (TextView)view.findViewById(R.id.excercise_listview_place_la);
            TextView lo = (TextView)view.findViewById(R.id.excercise_listview_place_lo);
            title.setText(data.title);
            address.setText(data.address);
            la.setText(data.LA);
            lo.setText(data.LO);
        }
        return view;
    }
}
