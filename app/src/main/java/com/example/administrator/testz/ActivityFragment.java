package com.example.administrator.testz;




        import java.util.ArrayList;
        import java.util.List;

        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
        import android.support.v4.view.ViewPager.OnPageChangeListener;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.RadioButton;
        import android.widget.RadioGroup;
        import android.widget.RadioGroup.OnCheckedChangeListener;


public class ActivityFragment extends BaseFragment implements OnCheckedChangeListener, OnPageChangeListener {
    private RadioGroup rgp;
    private ViewPager viewPager;
    /*    private View indicator_notify;
        private View indicator_vote;*/
    private List<Fragment> fragments;
    private FmPagerAdapter fmPagerAdapter;


    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_activity, null);


        rgp = (RadioGroup) view.findViewById(R.id.activity_rgp);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        // indicator_notify = (View)view.findViewById(R.id.indicator_notification);
        //    indicator_vote = (View)view.findViewById(R.id.indicator_vote);

        rgp.setOnCheckedChangeListener(this);


        return view;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        setAllListenner();
        fragments = new ArrayList<Fragment>();
        fragments.add(new ImuFragment());
        fragments.add(new CompassFragment());
        fmPagerAdapter = new FmPagerAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(fmPagerAdapter);
    }


    private void setAllListenner() {
        // TODO Auto-generated method stub
        rgp.setOnCheckedChangeListener(this);
        viewPager.setOnPageChangeListener(this);
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




