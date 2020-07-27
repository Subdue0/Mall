package me.myshop.activity;

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
import me.myshop.common.utils.MyData;
import me.myshop.entity.Result;

public class ChangePasswordActivity extends AppCompatActivity {

    private String mNewPassword;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText et_original_password = findViewById(R.id.et_original_password);
        final EditText et_new_password = findViewById(R.id.et_new_password);

        Button btn_modify_now = findViewById(R.id.btn_modify_now);


        btn_modify_now.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String original_password = EnhancePassword.coding(et_original_password.getText().toString());

                /**
                 * java中的值比较不能直接用==号，必须采用equals。
                 * 解释：如果二者都是常量，那么==和equal方法没有区别，但是如果一个字符串是变量，另一个字符串是常量，则会出现问题。
                 */
                if (original_password.equals(MyData.getInstance().getUser().getPassword())) {
                    mNewPassword = EnhancePassword.coding(et_new_password.getText().toString());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String responseData = HttpClient
                                    .textBody(Mall.BASE_URL + Mall.USER_REL_PATH + "/changepassword")
                                    .queryString("newpassword", mNewPassword)
                                    .json(JsonParse.toJson(MyData.getInstance().getUser()))
                                    .execute()
                                    .asString();

                            final Result result = JsonParse.fromJson(responseData, Result.class);

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (result.getStatus().equals(Mall.SUCCESS)) {
                                        Toast.makeText(ChangePasswordActivity.this, "密码修改成功", Toast.LENGTH_LONG).show();

                                        //设置当前用户的新密码
                                        MyData.getInstance().getUser().setPassword(mNewPassword);

                                        finish();
                                        //使用特殊关闭activity的动画覆盖默认的动画
                                        overridePendingTransition(R.anim.unchanged, R.anim.slide_in_top);
                                    } else {
                                        Toast.makeText(ChangePasswordActivity.this, result.getMsg(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }).start();

                } else {
                    Toast.makeText(ChangePasswordActivity.this, "原密码输入错误，请重新输入", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}
