package malang.moe.repay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import malang.moe.repay.R;
import malang.moe.repay.data.ExcerciseVideoData;
import malang.moe.repay.utils.AnimateNetworkImageView;
import malang.moe.repay.utils.ImageSingleTon;

/**
 * Created by kotohana5706 on 2015. 11. 21.
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class ExcerciseVideoAdapter extends ArrayAdapter<ExcerciseVideoData> {
    private LayoutInflater mInflater;
    ExcerciseVideoData data;

    AnimateNetworkImageView image;
    ImageLoader loader;

    public ExcerciseVideoAdapter(Context context, ArrayList<ExcerciseVideoData> object) {
        super(context, 0, object);
        loader = ImageSingleTon.getInstance(context).getImageLoader();
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = null;
        if (v == null) {
            view = mInflater.inflate(R.layout.listview_excercisevideo_content, null);
        } else {
            view = v;
        }
        final ExcerciseVideoData data = this.getItem(position);
        if (data != null) {
            //화면 출력
            setView(view, data);
        }
        return view;
    }

    public void setView(View view, ExcerciseVideoData data) {
        TextView title = (TextView) view.findViewById(R.id.excercise_listview_title);
        TextView descrip = (TextView) view.findViewById(R.id.excercise_listview_content);
        TextView videoId = (TextView)view.findViewById(R.id.excercise_listview_videoId);
        image = (AnimateNetworkImageView) view.findViewById(R.id.thumbnail);
        image.setImageUrl(data.imageLink, loader);
        title.setText(data.title);
        if (!data.description.trim().equals("")) descrip.setText(data.description);
        else descrip.setVisibility(View.GONE);
        videoId.setText(data.videoId);
    }
}
