package com.example.administrator.testz;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.testz.netty.NettyClient;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by wrs on 2019/9/4,15:15
 * projectName: Testz
 * packageName: com.example.administrator.testz
 */

public class CompassFragment extends Fragment implements View.OnClickListener {
    private LinearLayout start_compass;
    private int nums = 0;
    private ZProgressBar mProgressBar;
    private TextView tv2;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            int num = msg.what;
            mProgressBar.setAnimProgress(num);                   //设置进度
            tv2.setText(String.valueOf(num));
            //设置进度颜色   red  -2870516 green -8795045  yellow -994507
            // 如果小于3 绿色  如果3-6 为黄色  如果大于6 为红色
            if (num < 300) {
                mProgressBar.setProgressColor(-8795045);
            } else if (num < 600) {
                mProgressBar.setProgressColor(-994507);
            } else {
                mProgressBar.setProgressColor(-2870516);
            }
            return false;
        }
    });


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "CompassFragment onCreate ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", "CompassFragment onStart ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", "CompassFragment onResume ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "CompassFragment onPause ");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("TAG", "CompassFragment onSaveInstanceState ");
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "CompassFragment onActivityCreated ");
     /*   mProgressBar.setMax(1000);        //设置最大进度
        mProgressBar.setDefBackgroundColor(R.color.black); //设置背景颜色
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        nums = (int) (Math.random() * 1000 + 1);
                        Message msg = new Message();
                        msg.what = nums;
                        mHandler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compass_fragment, container, false);

        start_compass = (LinearLayout) view.findViewById(R.id.start_compass);
        tv2 = (TextView) view.findViewById(R.id.tv2);

        mProgressBar = (ZProgressBar) view.findViewById(R.id.progressBar);
        start_compass.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_compass:
                Log.e("LeftFragment", "Click start imu");
                //这个地方要把原来的dialogFragment清除掉
                CompassDialogFragment mCompassDetailFragment = new CompassDialogFragment();
                mCompassDetailFragment.show(getActivity().getFragmentManager(), "CompassDetailFragment");
                break;
        }
    }


}