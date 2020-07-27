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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mzlion.easyokhttp.HttpClient;

import me.myshop.R;
import me.myshop.common.constant.Mall;
import me.myshop.common.utils.EnhancePassword;
import me.myshop.common.utils.JsonParse;
import me.myshop.common.utils.MyData;
import me.myshop.entity.Result;
import me.myshop.entity.User;
import me.myshop.fragment.MyFragment;

public class LoginActivity extends AppCompatActivity {
    private ProgressBar mLoading;

    private EditText mETUid;
    private EditText mETPassword;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mETUid = findViewById(R.id.et_uid);

        mETPassword = findViewById(R.id.et_password);

        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);


        mLoading = findViewById(R.id.loading);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String uid = mETUid.getText().toString();
                String password = mETPassword.getText().toString();

                if (uid.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mLoading.setVisibility(View.VISIBLE);

                    password = EnhancePassword.coding(password);

                    final User user = new User(Integer.valueOf(uid), password);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String responseData = HttpClient
                                    .textBody(Mall.BASE_URL + Mall.USER_REL_PATH + "/login")
                                    .json(JsonParse.toJson(user))
                                    .execute()
                                    .asString();

                            final Result result = JsonParse.fromJson(responseData, Result.class);

                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (result.getStatus().equals(Mall.SUCCESS)) {
                                        Integer uid = Integer.valueOf(mETUid.getText().toString());
                                        String nickname = result.getNickname();
                                        String password = EnhancePassword.coding(mETPassword.getText().toString());

                                        User user = new User(uid, nickname, password, true);

                                        //给单例共享对象传用户的所有值
                                        MyData.getInstance().setUser(user);

                                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                                        //更新用户的昵称显示
                                        MyFragment.modifyNickName();

                                        //标识来源
                                        Intent intent_main = getIntent();
                                        intent_main.putExtra("which", "login_success");
                                        setResult(RESULT_OK, intent_main);

                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                    mLoading.setVisibility(View.GONE);
                                }
                            }, 1000);
                        }
                    }).start();
                }

            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_login = new Intent(LoginActivity.this, RegisterActivity.class);
                //传2表示间接跳转
                intent_login.putExtra("which", 2);
                startActivity(intent_login);
                //间接跳转到注册页面，使用特殊开启activity的动画覆盖默认的动画
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

}