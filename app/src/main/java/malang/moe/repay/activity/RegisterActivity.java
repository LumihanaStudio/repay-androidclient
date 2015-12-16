package malang.moe.repay.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import malang.moe.repay.R;
import malang.moe.repay.data.User;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class RegisterActivity extends Activity implements View.OnClickListener {

    EditText id, pw, name, repw;
    String id_, pw_, name_, repw_;
    TextView register;
    Call<User> registerUser;
    NetworkService service;
    Retrofit retrofit;
    private static final int SELECT_KEY = 1111;
    boolean isParent;
    Intent Select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setDefault();
        setRestAdapter();
        Select = new Intent(getApplicationContext(), SelectIsParentActivity.class);
        startActivityForResult(Select, SELECT_KEY);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SELECT_KEY:
                if (resultCode == RESULT_OK) {
                    isParent = intent.getExtras().getBoolean("isParent");
                } else if (resultCode == RESULT_CANCELED)
                    finish();
                break;
        }
    }

    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://malang.moe/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }

    private void setDefault() {
        id = (EditText) findViewById(R.id.register_input_id);
        pw = (EditText) findViewById(R.id.register_input_password);
        name = (EditText) findViewById(R.id.register_input_name);
        repw = (EditText) findViewById(R.id.register_input_re_password);
        register = (TextView) findViewById(R.id.register_register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_register:
                setLogin();
        }
    }

    private void setLogin() {
        id_ = id.getText().toString().trim();
        pw_ = pw.getText().toString().trim();
        name_ = name.getText().toString().trim();
        repw_ = repw.getText().toString().trim();

        if (!id_.equals("") && !pw_.equals("") && !name_.equals("") && !repw_.equals("")) {
            if (pw_.equals(repw_)) {
                registerUser = service.registerUser(id_, pw_, name_, isParent);
                registerUser.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Response<User> response, Retrofit retrofit) {
                        switch (response.code()) {
                            case 200:
                                Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                finish();
                                break;
                            case 409:
                                Toast.makeText(RegisterActivity.this, "중복된 아이디입니다.\n다른 아이디로 시도해주세요!", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(RegisterActivity.this, "서버와의 연동에 문제가 있습니다.\n네트워크 연결을 확인해주세요!", Toast.LENGTH_SHORT).show();
                        Log.e("Repay", t.getMessage());
                    }
                });
            } else
                Toast.makeText(RegisterActivity.this, "비밀번호를 동일하게 입력해주세요!", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(RegisterActivity.this, "빈칸 없이 입력해주세요!", Toast.LENGTH_SHORT).show();
    }
}
