package me.myshop.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.mzlion.easyokhttp.HttpClient;

import java.util.Calendar;

import me.myshop.R;
import me.myshop.activity.ChangePasswordActivity;
import me.myshop.activity.MyOrderActivity;
import me.myshop.common.constant.Mall;
import me.myshop.common.utils.JsonParse;
import me.myshop.common.utils.MyData;
import me.myshop.entity.Result;
import me.myshop.entity.User;

public class MyFragment extends Fragment {
    private View mRootView;

    private MyLogoutListener mMyLogoutListener;

    private static TextView mTVNickname;

    private Handler mHandler = new Handler();

    public interface MyLogoutListener{
        void sendContent(Result result);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mMyLogoutListener = (MyLogoutListener) getActivity();
    }

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //登录后修改用户的昵称
    public static void modifyNickName() {
        if (mTVNickname != null) {
            mTVNickname.setText(MyData.getInstance().getUser().getNickname());
        }
    }

    /**
     * activity重建后保存数据状态
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_my, null);
        }
        return mRootView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        TextView tv_greeting = getActivity().findViewById(R.id.tv_greeting);
        Calendar calendar = Calendar.getInstance();
        int hour24 = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour24>=0 && hour24<=6) {
            tv_greeting.setText("夜深了，注意休息哦！");
        } else if (hour24>=6 && hour24<12) {
            tv_greeting.setText("早上好！");
        } else if (hour24>=12 && hour24<18) {
            tv_greeting.setText("下午好！");
        } else if (hour24>=18 && hour24<24) {
            tv_greeting.setText("晚上好！");
        }

        mTVNickname = getActivity().findViewById(R.id.tv_nickname);

        if (MyData.getInstance().getUser() == null) {
            mTVNickname.setText("XXX");
        } else {
            mTVNickname.setText(MyData.getInstance().getUser().getNickname());
        }

        TableRow tr_change_password = getActivity().findViewById(R.id.tr_change_password);
        TableRow tr_my_order = getActivity().findViewById(R.id.tr_my_order);
        TableRow tr_feedback = getActivity().findViewById(R.id.tr_feedback);
        TableRow tr_help = getActivity().findViewById(R.id.tr_help);
        TableRow tr_check_update = getActivity().findViewById(R.id.tr_check_update);
        TableRow tr_about_us = getActivity().findViewById(R.id.tr_about_us);

        Button btn_logout = getActivity().findViewById(R.id.btn_logout);

        View.OnClickListener my_click_listener = new MyClickListener();
        tr_change_password.setOnClickListener(my_click_listener);
        tr_my_order.setOnClickListener(my_click_listener);
        tr_feedback.setOnClickListener(my_click_listener);
        tr_help.setOnClickListener(my_click_listener);
        tr_check_update.setOnClickListener(my_click_listener);
        tr_about_us.setOnClickListener(my_click_listener);

        btn_logout.setOnClickListener(my_click_listener);

    }


    private class MyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tr_change_password:
                    Intent intent_change_password = new Intent(getActivity(), ChangePasswordActivity.class);
                    startActivity(intent_change_password);
                    break;
                case R.id.tr_my_order:
                    Intent intent_my_order = new Intent(getActivity(), MyOrderActivity.class);
                    startActivity(intent_my_order);
                    break;
                case R.id.tr_feedback:
                    Toast.makeText(getActivity(), "本功能待开发", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tr_help:
                    new AlertDialog.Builder(getActivity())
                            .setTitle("客服电话")
                            .setMessage("拨打13688888888")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String data = "tel:" + "13688888888";
                                    Uri uri = Uri.parse(data);
                                    Intent intent = new Intent();
                                    intent.setAction(Intent.ACTION_DIAL);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                    break;
                case R.id.tr_check_update:
                    Toast.makeText(getActivity(), "此版本为最新版，无需更新", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tr_about_us:
                    Toast.makeText(getActivity(), "作者：Subdue Challens", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_logout:
                    AlertDialog dialog = new AlertDialog.Builder(getActivity())
                            .setTitle("提示")
                            .setMessage("确认退出系统吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                    final User user = MyData.getInstance().getUser();
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            String responseData = HttpClient
                                                    .textBody(Mall.BASE_URL + Mall.USER_REL_PATH + "/logout")
                                                    .json(JsonParse.toJson(user))
                                                    .execute()
                                                    .asString();

                                            final Result result = JsonParse.fromJson(responseData, Result.class);

                                            mHandler.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    mMyLogoutListener.sendContent(result);
                                                }
                                            });
                                        }
                                    }).start();

                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                    //修改确定按钮颜色
                    dialog.getButton(dialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
                    //禁止点击 dialog 外部取消弹窗
                    dialog.setCanceledOnTouchOutside(false);
                    break;
                default:
                    break;
            }

        }
    }
}