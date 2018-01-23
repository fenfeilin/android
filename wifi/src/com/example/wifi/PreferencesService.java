package com.example.wifi;

import java.util.HashMap;  
import java.util.Map;  
  
import android.content.Context;  
import android.content.SharedPreferences;  
import android.content.SharedPreferences.Editor;  
  
public class PreferencesService {  
    private Context context;  
  
    public PreferencesService(Context context) {  
        this.context = context;  
    }  
  
    /** 
     * 保存参数 
     * @param name  姓名 
     * @param age   年龄   
     * @param sex   性别 
     */  
    public void save(String streamurl, String controlurl) {  
        //获得SharedPreferences对象  
        SharedPreferences preferences = context.getSharedPreferences("wwj", Context.MODE_PRIVATE);  
        Editor editor = preferences.edit();  
        editor.putString("streamurl", streamurl);  
        editor.putString("controlurl", controlurl);  
       // editor.putInt("sex", sex);  
        editor.commit();  
    }  
  
    /** 
     * 获取各项参数 
     * @return 
     */  
    public Map<String, String> getPerferences() {  
        Map<String, String> params = new HashMap<String, String>();  
        SharedPreferences preferences = context.getSharedPreferences("wwj", Context.MODE_PRIVATE);  
        params.put("streamurl", preferences.getString("streamurl", ""));  
        params.put("controlurl", String.valueOf(preferences.getString("controlurl", "")));  
       // params.put("sex", String.valueOf(preferences.getInt("sex", 0)));  
        return params;  
    }  
      
      
      
}  