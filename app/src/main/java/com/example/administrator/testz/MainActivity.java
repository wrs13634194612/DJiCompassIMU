package com.example.administrator.testz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.administrator.testz.netty.MessageListener;
import com.example.administrator.testz.netty.NettyClient;
import com.example.administrator.testz.netty.NettyListener;


/**
 * Created by wrs on 2019/5/27,11:59
 * projectName: Ztest5
 * packageName: com.example.admin.ztest
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, MessageListener, NettyListener {
    private   Button button4,btn_show,btn_hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 完全去掉标题栏和状态栏
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //netty连接服务端
        NettyClient.getInstance().setNettyListener(this);
        NettyClient.getInstance().setMessageListener(this);
        if (!NettyClient.getInstance().isConnected()) {
            NettyClient.getInstance().connect();
        }

       button4 = (Button) findViewById(R.id.button);
        btn_show = (Button) findViewById(R.id.btn_show);
        btn_hide = (Button) findViewById(R.id.btn_hide);


        button4.setOnClickListener(this);
        btn_show.setOnClickListener(this);
        btn_hide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                SensorDialogFragment aboutDialog = new SensorDialogFragment();
                aboutDialog.show(getSupportFragmentManager(), "AboutDialogFragment");

                break;
            case R.id.btn_show:
                DisplayUtil.showNavBar(MainActivity.this);
                break;
            case R.id.btn_hide:
                DisplayUtil.hideNavBar(MainActivity.this);
                break;
        }

    }


    @Override
    public void onConnected() {
        NettyClient.getInstance().sendHeartBeatData("连接成功");
    }

    @Override
    public void onDisConnect() {
        NettyClient.getInstance().sendHeartBeatData("连接失败");
    }

}