package malang.moe.repay.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import malang.moe.repay.R;

public class Tutorial extends Activity {
    private ViewPager mPager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        mPager = (ViewPager) findViewById(R.id.ViewPager);
        mPager.setAdapter(new PagerAdapterClass(getApplicationContext()));
        sharedPreferences = getSharedPreferences("Repay", 0);
        editor = sharedPreferences.edit();
    }

    private class PagerAdapterClass extends PagerAdapter {
        private LayoutInflater mInflater;

        public PagerAdapterClass(Context c) {
            super();
            mInflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object instantiateItem(View pager, int position) {
            View v = null;
            // TODO: Set each page's view
            if (position == 0) {
                v = mInflater.inflate(R.layout.tuto_1, null);
            } else if (position == 1) {
                v = mInflater.inflate(R.layout.tuto_2, null);
            } else if (position == 2) {
                v = mInflater.inflate(R.layout.tuto_3, null);
            } else if (position == 3) {
                v = mInflater.inflate(R.layout.tuto_4, null);
            } else {
                v = mInflater.inflate(R.layout.tuto_5, null);
                Button asdf = (Button) v.findViewById(R.id.start);
                asdf.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editor.putBoolean("isFirst", false);
                        editor.commit();
                        finish();
                    }
                });
            }

            ((ViewPager) pager).addView(v, 0);
            return v;
        }

        @Override
        public void destroyItem(View pager, int position, Object view) {
            ((ViewPager) pager).removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(View pager, Object obj) {
            return pager == obj;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }

        @Override
        public void finishUpdate(View arg0) {
        }
    }

    public void ShortToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}

