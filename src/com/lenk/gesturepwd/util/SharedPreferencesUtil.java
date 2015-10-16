package com.lenk.gesturepwd.util;

import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * <sp文件管理类>
 * <工具类,copy以前的,作用不大>
 * 
 * @author  李令 NJ003345
 * @version  [版本号, 2015-10-16]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SharedPreferencesUtil
{
    
    /**
     * 通用分享文件名
     */
    private final String commonShareName = "SmartCard";
    
    private Context ctx;
    
    private SharedPreferences sharedPreferences;
    
    /**
     * 关键字
     */
    public interface Key
    {
        /**
         * Key
         */
        public final static String TESTKEY = "key";
        
        // 字符串默认数据
        public final static String DEFAULT_VALUE = "";
        
        // 整数默认数据
        public final static int DEFAULT_INT_VALUE = -1;
        
        // 系统引导页面的Key
        public final static String GUIDE_KEY = "guide_key";
        
        // 登陆用户名
        public final static String LOGIN_NAME = "login_name";
        
        // 登陆密码
        public final static String LOGIN_PASS = "login_pass";
        
        // 登陆的token
        public final static String LOGIN_TOKEN = "login_token";
        
        // 默认公司Id
        public final static String DEFAULT_COMPANY_ID = "default_company_id";
        
        // 登录者的userId
        public final static String LOGIN_USER_ID = "login_user_id";
        
        // 存储联系人数据的etag
        public final static String CONTACT_ETAG = "contact_etag";
        
        // 存储公司部门etag
        public final static String DEPARTMENT_ETAG = "department_etag";
        
        // 声音
        public final static String VOICE = "voice";
        
        // 震动
        public final static String SHAKE = "shake";
        
        // 状态栏通知
        public final static String NOTCIE = "notice";
        
        // 提醒的计数器
        public final static String REMIND_CLOCK = "remind_clock";
        
        // 提醒的计数器
        public final static String REMIND_NOTIFY_ID = "notify_id";
        
        // 通知详情
        public final static String NOTICE_DETAIL = "notice_detail";
        
        // 所有人查看我的任务
        public final static String READ_ALL_TASK = "read_all_task";
        
        /**
         * 手势密码tag
         */
        public final static String GESTURE_PWD_TAG = "gesture_pwd_tag";
        
        
        
        /**
         * 获取任务概要的tag
         */
        public final static String TASK_SUMMMARY_ETAG = "task_summmary_etag";
        
        /**
         * 会议召集人的tag
         */
        public final static String MEETING_SUMMON_ETAG = "meeting_summon_etag";
        
        /**
         * 提醒概要的tag
         */
        public final static String REMIND_SUMMMARY_ETAG = "remind_summmary_etag";
        
        /**
         * 是否显示tip的标记
         */
        public final static String IS_SHOW_FIRST_TIP = "is_show_first_tip";
        
        /**
         * 公告etag
         */
        public final static String NOTICE_ETAG = "notice_etag";
        
        public final static String SMALL_SCRET_LASTTIME = "small_scret_lastTime";
        
        public final static String SMALL_SCRET_CONTENT = "small_scret_content";
        
        /**
         * 是否是第一次进入提醒的标记
         */
        public final static String IS_FIRST_IN_REMINDER = "is_first_in_reminder";
        
        /**
         * 存储免打扰时间的标记
         */
        public final static String SET_FREE_TIME = "set_Free_time";
        
        /**
         * 设置免打扰时间开关的标记
         */
        public final static String FREE_TIME_IS_TOGGLE = "free_time_is_toggle";
        
    }
    
    /**
     * 默认构造函数
     */
    public SharedPreferencesUtil()
    {
        
    }
    
    /**
     * <默认构造函数>
     */
    public SharedPreferencesUtil(final Context ctx)
    {
        this.ctx = ctx;
        sharedPreferences = this.ctx.getSharedPreferences(commonShareName, Activity.MODE_PRIVATE);
    }
    
    /**
     * 保存键值 包含事务，如果一次要保存多值不建议使用，性能有损耗
     * 
     * @param key 保存的键
     * @param value 保存的值
     * @see [类、类#方法、类#成员]
     */
    public void saveStringValue(String key, String value)
    {
        if (sharedPreferences == null)
        {
            return;
        }
        
        Editor edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }
    
    /**
     * 获取String值
     * 
     * @param key 保存的键
     * @param defaultValue 默认的值
     * @return 键对应的值
     */
    public String readStringValue(String key, String defaultValue)
    {
        if (sharedPreferences == null)
        {
            return defaultValue;
        }
        
        return sharedPreferences.getString(key, defaultValue);
    }
    
    /**
     * 保存键值 包含事务，如果一次要保存多值不建议使用，性能有损耗
     * 
     * @param key 保存的键
     * @param value 保存的值
     * @see [类、类#方法、类#成员]
     */
    public void saveBooleanValue(String key, boolean value)
    {
        if (sharedPreferences == null)
        {
            return;
        }
        
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }
    
    /**
     * 获取String值
     * 
     * @param key 保存的键
     * @param defaultValue 默认的值
     * @return 键对应的值
     */
    public boolean readBooleanValue(String key, boolean defaultValue)
    {
        if (sharedPreferences == null)
        {
            return defaultValue;
        }
        
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    
    /**
     * 保存键值 包含事务，如果一次要保存多值不建议使用，性能有损耗
     * 
     * @param key 保存的键
     * @param value 保存的值
     * @see [类、类#方法、类#成员]
     */
    public void saveIntValue(String key, int value)
    {
        if (sharedPreferences == null)
        {
            return;
        }
        
        Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }
    
    /**
     * 获取String值
     * 
     * @param key 保存的键
     * @param defaultValue 默认的值
     * @return 键对应的值
     */
    public int readIntValue(String key, int defaultValue)
    {
        if (sharedPreferences == null)
        {
            return defaultValue;
        }
        return sharedPreferences.getInt(key, defaultValue);
    }
    
    /**
     * 
     * 保存内容到SharedPreferences
     * 
     * @param context
     * @param map 保存的内容，键值对的形式
     */
    public void saveSharedPreferences(Context context, Map<String, Object> map)
    {
        Editor editor = sharedPreferences.edit();
        
        Set<String> set = map.keySet();
        for (String key : set)
        {
            editor.putString(key, (String)map.get(key));
        }
        
        editor.commit();
    }
    
    /**
     * 
     * 删除某条SharedPreferences
     * 
     * @param context
     * @param map 保存的内容，键值对的形式
     */
    public void removeSharedPreferences(Context context, String key)
    {
        Editor editor = sharedPreferences.edit();
        
        editor.remove(key);
        
        editor.commit();
    }
    
}
