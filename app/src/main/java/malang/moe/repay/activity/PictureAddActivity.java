package malang.moe.repay.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;

import malang.moe.repay.R;
import malang.moe.repay.utils.NetworkService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class PictureAddActivity extends AppCompatActivity implements View.OnClickListener {

    private static int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    String shareType, picturePath, apikey, item_name, item_comment, item_place, item_reward, string_path, finalPath;
    Bitmap bitmap;
    MaterialDialog loading;
    FloatingActionButton add;
    ImageView imageView;
    RelativeLayout pictureSel;
    Call<String> postArticle;
    SharedPreferences sharedPreferences;
    NetworkService service;
    Retrofit retrofit;
    EditText title, contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_add);
        setDefault();
        setRestAdapter();
        setActionBar(getSupportActionBar());
    }

    private void setDefault() {
        sharedPreferences = getSharedPreferences("Repay", 0);
        pictureSel = (RelativeLayout) findViewById(R.id.photo_add_layout);
        imageView = (ImageView) findViewById(R.id.photo_add_imageview);
        pictureSel.setOnClickListener(this);
        add = (FloatingActionButton) findViewById(R.id.postbutton);
        add.setOnClickListener(this);
        apikey = sharedPreferences.getString("apikey", "");
        title = (EditText) findViewById(R.id.picture_add_title);
        contentEditText = (EditText) findViewById(R.id.picture_add_content);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            Log.e("파일 경로", picturePath);
            cursor.close();
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageDrawable(null);
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeFile(picturePath, opt);
            opt.inSampleSize = calculateBmpSampleSize(opt, imageView.getWidth(), imageView.getHeight());
            opt.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(picturePath);
            imageView.setImageBitmap(bitmap);//이미지뷰에 뿌려줍니다.
            Log.e("asdf", "Image Success");
        }
    }

    public int calculateBmpSampleSize(BitmapFactory.Options opt, int width, int height) {
        final int outHeight = opt.outHeight;
        final int outWidth = opt.outWidth;
        int sampleSize = 1;
        if (outHeight > height || outWidth > width) {
            final int heightRatio = Math.round((float) outHeight / (float) height);
            final int widthRatio = Math.round((float) outWidth / (float) width);
            sampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return sampleSize;
    }

    private void setActionBar(ActionBar ab) {
        ab.setTitle("추억 추가");
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.postbutton:
                upload();
                break;
            case R.id.photo_add_layout:
                setImage();
                break;
        }
    }

    private void upload() {
        loading = new MaterialDialog.Builder(PictureAddActivity.this)
                .title("업로드중입니다.")
                .content("잠시만 기다려주세요")
                .progress(true, 0)
                .show();
        File file = new File(picturePath);
        RequestBody image = RequestBody.create(MediaType.parse("image/jpeg"), file);
        postArticle = service.postArticle(image, title.getText().toString().trim(), contentEditText.getText().toString().trim(),
                sharedPreferences.getString("apikey", ""));
        postArticle.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response, Retrofit retrofit) {
                switch (response.code()) {
                    case 200:
                        loading.dismiss();
                        Toast.makeText(PictureAddActivity.this, "정상적으로 등록되었습니다!", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                loading.dismiss();
                Toast.makeText(PictureAddActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void setRestAdapter() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://malang.moe/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(NetworkService.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setImage() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //안드로이드 시스템에 있는 이미지들에서 선택(PICK)을 위한 인텐트 생성
        startActivityForResult(i, RESULT_LOAD_IMAGE);//위에서 선언한 1이라는 결과 코드로 액티비티를 선언
    }
}
