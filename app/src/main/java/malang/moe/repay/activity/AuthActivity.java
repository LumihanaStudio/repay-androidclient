package malang.moe.repay.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import malang.moe.repay.R;
import malang.moe.repay.data.User;
import malang.moe.repay.utils.AnimateNetworkImageView;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Retrofit retrofit;
    TextView login, register;
    AnimateNetworkImageView image;
    ImageLoader loader;
    User user;
    Call<User> userLogin, userLogout, autoLogin, registerUser;
    NetworkService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setDefault();
        setRestAdapter();
//        loader = ImageSingleTon.getInstance(this).getImageLoader();
//        image = (AnimateNetworkImageView) findViewById(R.id.auth_imageview);
//        image.setImageUrl("http://bamtoll.moe/asdf.jpg", loader);
    }

    private void setDefault() {
        sharedPreferences = getSharedPreferences("Repay", 0);
        editor = sharedPreferences.edit();
        login = (TextView) findViewById(R.id.auth_login);
        register = (TextView) findViewById(R.id.register_register);

        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://bamtoll.moe:2000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auth_login:
                setLogin();
                break;
            case R.id.register_register:
                setRegister();
        }
    }

    private void setLogin() {
        EditText id = (EditText) findViewById(R.id.auth_input_id);
        EditText password = (EditText) findViewById(R.id.auth_input_password);
        String idText = id.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        if (idText.equals("")) id.setError("아이디를 입력해주세요!");
        if (passwordText.equals("")) password.setError("비밀번호를 입력해주세요!");
        if (!idText.equals("") && !passwordText.equals("")) {
            userLogin = service.userLogin(idText, passwordText);
            userLogin.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Response<User> response, Retrofit retrofit) {
                    switch (response.code()) {
                        case 200:
                            editor.putString("name", response.body().name);
                            editor.putString("id", response.body().id);
                            editor.putString("password", response.body().password);
                            editor.putString("apikey", response.body().apikey);
                            editor.putBoolean("isParent", response.body().isParent);
                            editor.commit();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            break;
                        case 400:
                            Toast.makeText(AuthActivity.this, "아이디 또는 비밀번호가 잘못되었습니다!", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    Toast.makeText(AuthActivity.this, "서버와의 통신에 문제가 있습니다.\n인터넷 연결을 확인해주세요!", Toast.LENGTH_SHORT).show();
                    Log.e("Repay", t.getMessage());
                }
            });
        }
    }

    private void setRegister() {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
    }
}
