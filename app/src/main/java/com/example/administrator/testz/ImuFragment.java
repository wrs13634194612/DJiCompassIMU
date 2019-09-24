package com.example.administrator.testz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
/**
 * Created by wrs on 2019/9/4,15:15
 * projectName: Testz
 * packageName: com.example.administrator.testz
 */

public class ImuFragment extends Fragment implements View.OnClickListener {
    private LinearLayout start_imu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imu_fragment, container, false);
        Log.e("TAG","ImuFragment onCreateView ");
        start_imu = (LinearLayout) view.findViewById(R.id.start_imu);

        start_imu.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_imu:
                Log.e("LeftFragment", "Click start imu");
                //这个地方要把原来的dialogFragment清除掉

                break;
        }

    }
}