package com.example.administrator.testz;


import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


//这是指南针校准页面
public class CompassDialogFragment extends DialogFragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener, View.OnClickListener {
    private LinearLayout compass_cancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compass_detail_dialog, container, false);
        initView(view);
        EventBus.getDefault().register(this);
        return view;
    }

    private void initView(View V_AboutDialog) {

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        getDialog().setCanceledOnTouchOutside(true);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setWindowAnimations(R.style.dialogWindowAnim);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.dimAmount = 0f;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Subscribe
    public void showTextEvent(NettyClientEvent stringEvent) {
        //        textView.setText(stringEvent.getTexto());
        Log.e("CompassDialogFragment", "NettyClientEventStatus: " + stringEvent.getStatus()
                + ",Compassing: " + stringEvent.getCompassing());



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}