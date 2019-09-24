package com.example.administrator.testz;




        import android.content.Context;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {

    public View view;
    public Context context;


    /*
     * 因为我们在Activity页面中经常需要使用“this”，但是到处都是用this显得不太好，
     * 这时候我们可以在BaseActivity中定义一个Actiivty类型的mContext成员变量并在
     * BaseActivity中的onCreate方法中赋值为this
     * ，这样我们在子Activity中需要使用this的地方直接使用mContext成员变量就好了。
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        this.context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = initView(inflater);
        System.out.println("onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("initData");
        initData(savedInstanceState);
    }

    // 视图
    public abstract View initView(LayoutInflater inflater);

    // 数据
    public abstract void initData(Bundle savedInstanceState);
}
