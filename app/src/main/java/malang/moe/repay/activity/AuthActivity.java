package malang.moe.repay.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import malang.moe.repay.R;
import malang.moe.repay.utils.AnimateNetworkImageView;


public class AuthActivity extends Activity implements View.OnClickListener {

    TextView login;
    AnimateNetworkImageView image;
    ImageLoader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
//        loader = ImageSingleTon.getInstance(this).getImageLoader();
//        image = (AnimateNetworkImageView) findViewById(R.id.auth_imageview);
//        image.setImageUrl("http://bamtoll.moe/asdf.jpg", loader);
        login = (TextView) findViewById(R.id.auth_login);
        login.setOnClickListener(this);
     }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.auth_login:
                break;
        }
    }
}
