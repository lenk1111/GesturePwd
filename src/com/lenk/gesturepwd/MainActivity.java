package com.lenk.gesturepwd;





import com.lenk.gesturepwd.util.SharedPreferencesUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
    
    private static MainActivity activity;
    
    public static MainActivity getInstance(){
        return activity;
    }
    private RelativeLayout mSettingGuesture;
    private TextView mSettingTitle;
    private TextView mGesturePwdStateTv;
    /** 手势密码 设置/未设置 textview标记 **/
    private String gesturePwdStr;
    private SharedPreferencesUtil spUtils;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        initView();
        setListener();
        initData();
    }

    public void initView(){
        mSettingTitle = (TextView)findViewById(R.id.top_bar_title_tv);
        mSettingGuesture = (RelativeLayout)findViewById(R.id.setting_guesture);
        mGesturePwdStateTv = (TextView)findViewById(R.id.setting_gesture_pwd_state_tv);

    }
    
    public void setListener()
    {
        // TODO Auto-generated method stub
        mSettingGuesture.setOnClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        switch (v.getId())
        {
            case R.id.setting_guesture:// 手势解锁
            {
                if (gesturePwdStr.length()>0)//已设置手势
                {
                    startActivity(new Intent(MainActivity.this, LeftMenuSettingGesturePwdActivity.class));
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, LeftMenuSettingGesturePwdCreateActivity.class));
                }
                break;
            }
        }
    }
    public void initData()
    {
        // TODO Auto-generated method stub
        
        mSettingTitle.setText(getResources().getString(R.string.personal_center_settings));
        
        spUtils = new SharedPreferencesUtil(MainActivity.this);
        refreshGesturePwdTv();
    }
    /** 刷新 手势密码设置/未设置 textview **/
    public void refreshGesturePwdTv()
    {
        gesturePwdStr = spUtils.readStringValue(SharedPreferencesUtil.Key.GESTURE_PWD_TAG, "");
        if (gesturePwdStr.length()>0)//已设置手势
        {
            mGesturePwdStateTv.setText(getResources().getString(R.string.setting_set));
        }
        else
        {
            mGesturePwdStateTv.setText(getResources().getString(R.string.setting_not_set));
        }
    }
    
}
