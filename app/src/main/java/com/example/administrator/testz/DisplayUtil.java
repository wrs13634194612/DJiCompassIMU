package com.example.administrator.testz;


        import android.app.Activity;
        import android.content.Context;
        import android.content.res.Resources;
        import android.graphics.Rect;
        import android.os.Build;
        import android.util.DisplayMetrics;
        import android.util.TypedValue;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Toast;

        import java.lang.reflect.Method;

public class DisplayUtil {

    public static int getScreenSizeWidth(Activity con) {
        DisplayMetrics metric = new DisplayMetrics();
        con.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;
    }

    public static int getScreenSizeHeight(Activity con) {
        DisplayMetrics metric = new DisplayMetrics();
        con.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int heightPixels = metric.heightPixels;     // 屏幕宽度（像素）
        return heightPixels;
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取状态栏高度
     *
     * @param v
     * @return
     */
    public static int getStatusBarHeight(View v) {
        if (v == null) {
            return 0;
        }
        Rect frame = new Rect();
        v.getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 隐藏底部虚拟栏
     *
     * @param activity
     */
    public static void hideNavBar(Activity activity) {
        if (!hasNavigationBar(activity)) {
            return;
        }
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 显示底部虚拟栏
     *
     * @param activity
     */
    public static void showNavBar(Activity activity) {
        if (!hasNavigationBar(activity)) {
            return;
        }
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            //低版本sdk
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 隐藏迈冲android系统底部虚拟栏
     *
     * @param context
     * @return
     */
    public static boolean hideMCNavBar(Context context) {
        if (!hasNavigationBar(context)) {
            return true;
        }
        boolean ishide;
        try {
            String command;
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib service call activity 42 s16 com.android.systemui";
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", command});
            proc.waitFor();
            ishide = true;
        } catch (Exception ex) {
            Toast.makeText(context.getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ishide = false;
        }
        return ishide;
    }

    /**
     * 显示迈冲android系统底部虚拟栏
     *
     * @return
     */
    public static boolean showMCNavBar(Context context) {
        if (!hasNavigationBar(context)) {
            return true;
        }
        boolean isshow;
        try {
            String command;
            command = "LD_LIBRARY_PATH=/vendor/lib:/system/lib am startservice -n com.android.systemui/.SystemUIService";
            Process proc = Runtime.getRuntime().exec(new String[]{"su", "-c", command});
            proc.waitFor();
            isshow = true;
        } catch (Exception e) {
            isshow = false;
            e.printStackTrace();
        }
        return isshow;
    }

    /**
     * 判断是否存在虚拟按键
     *
     * @return
     */
    public static boolean hasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

}