package com.lenk.gesturepwd;

import java.util.ArrayList;
import java.util.List;

import com.lenk.gesturepwd.util.SharedPreferencesUtil;
import com.lenk.gesturepwd.view.GesturePwdView;
import com.lenk.gesturepwd.view.GesturePwdView.OnGestureFinishListener;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LeftMenuSettingGesturePwdActivity extends Activity implements OnClickListener
{
 private ImageView mBackIv;
    
    private TextView mTitleTv;
    
    /** 滑动锁对象 **/
    private GesturePwdView mGesturePwdView;
    
    /** 忘记密码 **/
    private TextView mForgetTv;
    
    /** 密码提示图片id集合 **/
    private int[] tipsIvIds = new int[] {R.id.gesture_pwd_iv0, R.id.gesture_pwd_iv1, R.id.gesture_pwd_iv2,
        R.id.gesture_pwd_iv3, R.id.gesture_pwd_iv4, R.id.gesture_pwd_iv5, R.id.gesture_pwd_iv6, R.id.gesture_pwd_iv7,
        R.id.gesture_pwd_iv8};
    
    /** 提示文本 **/
    private TextView mtipsTv;
    
    private LinearLayout tipsIvLayout;
    
    /** 密码提示图片集合 **/
    private List<ImageView> tipsIvList = new ArrayList<ImageView>();
    
    private Animation shakeAnimation;
    
    private SharedPreferencesUtil spUtils;
    
    /** 手势密码 设置/未设置 textview标记 **/
    private String gesturePwdStr;
    
    /** 已设置过手势 并且修改手势验证成功,重启activity准备再次记录手势标示 **/
    private boolean isChangeValidateSuccess = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu_setting_gesture_pwd);
        initViews();
        setListener();
        initData();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.left_menu_setting_gesture_pwd, menu);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.top_bar_left_iv:
            {
                finish();
                break;
            }
            case R.id.setting_gesture_pwd_forget:
            {
                spUtils.saveStringValue(SharedPreferencesUtil.Key.GESTURE_PWD_TAG, "");
                // 刷新设置 界面textview
                MainActivity.getInstance().refreshGesturePwdTv();
                finish();
                break;
            }
        }
    }
    public void initViews()
    {
        mBackIv = (ImageView)findViewById(R.id.top_bar_left_iv);
        mTitleTv = (TextView)findViewById(R.id.top_bar_title_tv);
        mGesturePwdView = (GesturePwdView)findViewById(R.id.setting_gesture_pwd_gesture_view);
        mtipsTv = (TextView)findViewById(R.id.setting_gesture_pwd_tips_tv);
        tipsIvLayout = (LinearLayout)findViewById(R.id.gesture_pwd_iv_layout);
        mForgetTv = (TextView)findViewById(R.id.setting_gesture_pwd_forget);
        for (int i = 0; i < tipsIvIds.length; i++)
        {
            ImageView iv = (ImageView)findViewById(tipsIvIds[i]);
            tipsIvList.add(iv);
        }
    }
    
    public void setListener()
    {
        // TODO Auto-generated method stub
        mBackIv.setOnClickListener(this);
        mForgetTv.setOnClickListener(this);
        // 左右移动动画
        shakeAnimation = AnimationUtils.loadAnimation(LeftMenuSettingGesturePwdActivity.this, R.anim.shake);
        // 手势完成后回调
        mGesturePwdView.setOnGestureFinishListener(new OnGestureFinishListener()
        {
            
            @Override
            public void OnGestureFinish(int status, String key, List<Integer> linedCycles)
            {
                // TODO Auto-generated method stub
                switch (status)
                {
                    case GesturePwdView.GesturePwdStatus.NUMBER_ERROR:// 连接点数少于4个
                        mtipsTv.setText(getResources().getString(R.string.setting_gesture_pwd_number));
                        mtipsTv.startAnimation(shakeAnimation);
                        break;
                    case GesturePwdView.GesturePwdStatus.VALIDATE_AGAIN:// 第一次手势绘制成功 二次验证状态
                        mtipsTv.setText(getResources().getString(R.string.setting_gesture_pwd_drawing_again));
                        refreshTipsImg(linedCycles);
                        break;
                    case GesturePwdView.GesturePwdStatus.VALIDATE_AGAIN_ERROR:// 手势设置失败 二次验证状态
                        mtipsTv.setText(getResources().getString(R.string.setting_gesture_pwd_drawing_inconsistent));
                        mtipsTv.startAnimation(shakeAnimation);
                        break;
                    case GesturePwdView.GesturePwdStatus.VALIDATE_AGAIN_SUCCESS:// 手势设置成功 二次验证状态
                        spUtils.saveStringValue(SharedPreferencesUtil.Key.GESTURE_PWD_TAG, key);
                        // 刷新设置 界面textview
                        MainActivity.getInstance().refreshGesturePwdTv();
                        if (isChangeValidateSuccess)
                        {
                            Toast.makeText(LeftMenuSettingGesturePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(LeftMenuSettingGesturePwdActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                        }
                        LeftMenuSettingGesturePwdActivity.this.finish();
                        break;
                    case GesturePwdView.GesturePwdStatus.VALIDATE_SUCCESS:// 对比一致 与成功记录手势对比
                        LeftMenuSettingGesturePwdActivity.this.finish();
                        Intent intent =
                            new Intent(LeftMenuSettingGesturePwdActivity.this, LeftMenuSettingGesturePwdActivity.class);
                        intent.putExtra("isChangeValidateSuccess", true);
                        startActivity(intent);
                        break;
                    case GesturePwdView.GesturePwdStatus.VALIDATE_ERROR:// 对比不一致 与成功记录手势对比
                        mtipsTv.setText(getResources().getString(R.string.setting_gesture_pwd_validate_error));
                        mtipsTv.startAnimation(shakeAnimation);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    
    public void initData()
    {
        mBackIv.setVisibility(View.VISIBLE);// 显示top栏 后退img
        spUtils = new SharedPreferencesUtil(LeftMenuSettingGesturePwdActivity.this);
        
        isChangeValidateSuccess = getIntent().getBooleanExtra("isChangeValidateSuccess", false);
        
        gesturePwdStr = spUtils.readStringValue(SharedPreferencesUtil.Key.GESTURE_PWD_TAG, "");
        if (gesturePwdStr.length() > 0 && isChangeValidateSuccess == false)// 已设置密码但还没有与原密码验证的状态 相当于设置成功后第一次进入
                                                                           // 更改文字,title,隐藏tip img 更改滑动锁view初始状态,对比值
        {
            mTitleTv.setText(getResources().getString(R.string.setting_gesture_pwd_validate_title));
            mtipsTv.setText(getResources().getString(R.string.setting_gesture_pwd_validate));
            tipsIvLayout.setVisibility(View.INVISIBLE);
            mGesturePwdView.validationStatus = GesturePwdView.GesturePwdStatus.VALIDATE;
            mGesturePwdView.tempResString = new StringBuffer(gesturePwdStr);
        }
        else
        {// 准备存储密码
            mTitleTv.setText(getResources().getString(R.string.setting_gesture_pwd_title));
            mForgetTv.setVisibility(View.INVISIBLE);
        }
    }
    
    /** 刷新提示图片 **/
    public void refreshTipsImg(List<Integer> linedCycles)
    {
        for (int i = 0; i < tipsIvList.size(); i++)
        {
            tipsIvList.get(i).setImageResource(R.drawable.gesture_pwd_round_white);
            for (Integer integer : linedCycles)
            {
                if (i == integer)
                {
                    tipsIvList.get(i).setImageResource(R.drawable.gesture_pwd_round_blue);
                }
            }
        }
    }
}
