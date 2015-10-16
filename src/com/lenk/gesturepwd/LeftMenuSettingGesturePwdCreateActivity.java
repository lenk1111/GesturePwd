package com.lenk.gesturepwd;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * <解锁欢迎页> <侧滑菜单->设置 手势密码>
 * 
 * @author 李令 NJ003345
 * @version [版本号, 2015-10-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class LeftMenuSettingGesturePwdCreateActivity extends Activity implements OnClickListener
{
    private ImageView mBackIv;
    
    private TextView mTitleTv;
    /** 下一步按钮 **/
    private Button createGesturePwdBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_menu_setting_gesture_pwd_create);
        
        initViews();
        setListener();
        initData();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.left_menu_setting_gesture_pwd_create, menu);
        return true;
    }
    public void initViews()
    {
        mBackIv = (ImageView)findViewById(R.id.top_bar_left_iv);
        mTitleTv = (TextView)findViewById(R.id.top_bar_title_tv);
        createGesturePwdBtn = (Button)findViewById(R.id.gesture_pwd_create_btn);
    }

    public void setListener()
    {
        mBackIv.setOnClickListener(this);
        createGesturePwdBtn.setOnClickListener(this);
    }

    public void initData()
    {
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText(getResources().getString(R.string.setting_gesture_pwd_title));
    }
    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        switch (v.getId())
        {
            case R.id.top_bar_left_iv:
            {
                finish();
                break;
            }
            case R.id.gesture_pwd_create_btn:
            {
                Intent intent = new Intent(LeftMenuSettingGesturePwdCreateActivity.this,LeftMenuSettingGesturePwdActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
    
}
