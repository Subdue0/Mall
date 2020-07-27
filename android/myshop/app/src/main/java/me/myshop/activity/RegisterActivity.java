package me.myshop.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mzlion.easyokhttp.HttpClient;

import me.myshop.R;
import me.myshop.common.constant.Mall;
import me.myshop.common.utils.EnhancePassword;
import me.myshop.common.utils.JsonParse;
import me.myshop.entity.Result;
import me.myshop.entity.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText mETPassword1;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText et_nickname = findViewById(R.id.et_nickname);

        mETPassword1 = findViewById(R.id.et_password1);

        final EditText et_password2 = findViewById(R.id.et_password2);

        Button btn_register_now = findViewById(R.id.btn_register_now);

        btn_register_now.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String nickname = et_nickname.getText().toString();
                String password1 = mETPassword1.getText().toString();
                String password2 = et_password2.getText().toString();

                if (nickname.isEmpty() || password1.isEmpty() || password2.isEmpty() ) {
                    Toast.makeText(RegisterActivity.this, "请完整输入用户信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password1.equals(password2)) {

                    String password = EnhancePassword.coding(password1);

                    final User user = new User(nickname, password);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String responseData = HttpClient
                                    .textBody(Mall.BASE_URL + Mall.USER_REL_PATH + "/register")
                                    .json(JsonParse.toJson(user))
                                    .execute()
                                    .asString();

                            final Result result = JsonParse.fromJson(responseData, Result.class);

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (result.getStatus().equals(Mall.SUCCESS)) {
                                        String uid = result.getId();

                                        String password = mETPassword1.getText().toString();

                                        Intent intent_message = new Intent(RegisterActivity.this, MessageActivity.class);
                                        intent_message.putExtra("uid", uid);
                                        intent_message.putExtra("password", password);
                                        startActivity(intent_message);

                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }).start();

                } else {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //回退到登录界面，使用特殊关闭activity的动画覆盖默认的动画
        if (getIntent().getIntExtra("which", 1) == 2) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
}
