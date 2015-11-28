package malang.moe.repay.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;

/**
 * Created by kotohana5706 on 2015. 11. 28..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class AnimateNetworkImageView extends NetworkImageView {

    private static final int FADE_IN_TIME_MS = 250;

    public AnimateNetworkImageView(Context context) {
        super(context);
    }

    public AnimateNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimateNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        TransitionDrawable td = new TransitionDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(android.R.color.transparent)),
                new BitmapDrawable(getContext().getResources(), bm)
        });

        setImageDrawable(td);
        td.startTransition(FADE_IN_TIME_MS);
    }
}
