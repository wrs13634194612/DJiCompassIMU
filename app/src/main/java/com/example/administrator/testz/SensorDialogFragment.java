package com.example.administrator.testz;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * 这是传感器状态页面
 */
public class SensorDialogFragment extends DialogFragment implements DialogInterface.OnCancelListener,
        DialogInterface.OnDismissListener, View.OnClickListener,RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {



    private RadioGroup rgp;
    private ViewPager viewPager;
    /*    private View indicator_notify;
        private View indicator_vote;*/
    private List<android.support.v4.app.Fragment> fragments;
    private FmPagerAdapter fmPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sensor_status_dialog, container, false);
        initView(view);
        return view;
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


    private void initView(View view) {
        rgp = (RadioGroup) view.findViewById(R.id.activity_rgp);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        rgp.setOnCheckedChangeListener(this);

        rgp.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
        fragments = new ArrayList<android.support.v4.app.Fragment>();
        fragments.add(new ImuFragment());
        fragments.add(new CompassFragment());
        fmPagerAdapter = new FmPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fmPagerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
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
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        switch (checkedId) {
            case R.id.notification_rb:
                //  indicator_notify.setVisibility(View.VISIBLE);
                //   indicator_vote.setVisibility(View.INVISIBLE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.vote_rb:
                //   indicator_notify.setVisibility(View.INVISIBLE);
                //     indicator_vote.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(1);
                break;

            default:
                break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }


    @Override
    public void onPageSelected(int arg0) {
        // TODO Auto-generated method stub
        RadioButton rb = (RadioButton) rgp.getChildAt(arg0);
        rb.setChecked(true);
    }


}