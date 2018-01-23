package com.example.wifi;

import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class client extends Activity  {
	
	private Button setBtn = null;
	private Context mContext = null;
	private String streamurl ;
	private String controlurl ;
	private ImageButton forBtn=null;
	private ImageButton bacBtn=null;
	private ImageButton lefBtn=null;
	private ImageButton rigBtn=null;
	private ImageButton stoBtn=null;
	private char stop='s';
	private WebView webview; 
	private Button shuaxin=null;
	private HttpRequest http = null;
	private PreferencesService service;
	
	/** Called when the activity is first created. */
    @SuppressLint({ "CutPasteId", "SetJavaScriptEnabled" })
	public void onCreate(Bundle savedInstanceState) {
    	
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads()
				.detectDiskWrites().detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects()
				.penaltyLog().penaltyDeath().build());
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		setBtn = (Button) findViewById(R.id.button1);
		shuaxin= (Button) findViewById(R.id.shuaxin);
		forBtn=(ImageButton)findViewById(R.id.forbtn);
		bacBtn=(ImageButton)findViewById(R.id.bacbtn);
		lefBtn=(ImageButton)findViewById(R.id.lefbtn);
		rigBtn=(ImageButton)findViewById(R.id.rigbtn);
		stoBtn=(ImageButton)findViewById(R.id.stobtn);
		
		service = new PreferencesService(this);  
        Map<String, String> params = service.getPerferences();  
		if(params.get("streamurl")=="") 
		{
			streamurl="http://15ae544822.iask.in:25378/?action=stream";
		}
		else 
		{
			streamurl=params.get("streamurl");
		}
		
		if(params.get("controlurl")=="")
		{
			controlurl="http://15ae544822.iask.in:25378/";
		}
		else 
		{
			controlurl=params.get("controlurl");
		}
		
		webview = (WebView) findViewById(R.id.webview); 
	    //设置WebView属性，能够执行Javascript脚本 
	    webview.getSettings().setJavaScriptEnabled(true); 
	    //加载需要显示的网页 
	    webview.loadUrl(streamurl); 
	    webview.getSettings().setUseWideViewPort(true);
	    webview.getSettings().setLoadWithOverviewMode(true);
	    //设置Web视图 
	    webview.setWebViewClient(new HelloWebViewClient ()); 
	    
		mContext = this;
		http = new HttpRequest();
		
		forBtn.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				char command='F';
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					SendCmd(command);
					break;
				case MotionEvent.ACTION_UP:
					SendCmd(stop);
					break;
				default:
					break;
				}
				return false;
			}
			
		});
		
		bacBtn.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				char command='B';
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					SendCmd(command);
					break;
				case MotionEvent.ACTION_UP:
					SendCmd(stop);
					break;
				default:
					break;
				}
				return false;
			}
			
		});
		
		lefBtn.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				char command='L';
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					SendCmd(command);
					break;
				case MotionEvent.ACTION_UP:
					SendCmd(stop);
					break;
				default:
					break;
				}
				return false;
			}
			
		});
		
		rigBtn.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				char command='R';
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					SendCmd(command);
					break;
				case MotionEvent.ACTION_UP:
					SendCmd(stop);
					break;
				default:
					break;
				}
				return false;
			}
			
		});
		
		stoBtn.setOnTouchListener(new OnTouchListener(){

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				char command='S';
				switch(event.getAction()){
				case MotionEvent.ACTION_DOWN:
					SendCmd(command);
					break;
				case MotionEvent.ACTION_UP:
					SendCmd(stop);
					break;
				default:
					break;
				}
				return false;
			}
			
		});
		
		setBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Setting();
			}
		});
		
		shuaxin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webview.loadUrl(streamurl);
				webview.reload();
			}});
    }

	public void SendCmd(final char command){
		System.out.println("SendCommand: "+Thread.currentThread().getName());
		new Thread() {
            public void run() {
            	try {
            		http.doGet(controlurl+String.valueOf(command));						
            }catch (Exception e) {
				e.printStackTrace();
			}
            }
		}.start();
    }
    

	public void Setting() {
		LayoutInflater factory=LayoutInflater.from(mContext);
		final View v1=factory.inflate(R.layout.setting,null);
		AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
		dialog.setTitle("设置网址");
		dialog.setView(v1);
		EditText streamText=(EditText)v1.findViewById(R.id.streamtext);
		EditText controlText=(EditText)v1.findViewById(R.id.controltext);		
        
		service = new PreferencesService(this);  
        Map<String, String> params = service.getPerferences();  
		if(params.get("streamurl")=="") streamText.setText("15ae544822.iask.in:25378");
		else streamText.setText(params.get("streamurl"));
		if(params.get("controlurl")=="") controlText.setText("15ae544822.iask.in:25378");
		else controlText.setText(params.get("controlurl"));
		        
        dialog.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	EditText stream=(EditText)v1.findViewById(R.id.streamtext);
            	EditText control=(EditText)v1.findViewById(R.id.controltext);
            	streamurl="http://"+stream.getText().toString()+"/?action=stream";
            	controlurl="http://"+control.getText().toString()+"/"; 
                service.save(stream.getText().toString(), control.getText().toString());  
            	Toast.makeText(mContext, "设置成功!", Toast.LENGTH_SHORT).show(); 
            }
        });
        dialog.setNegativeButton("取消",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
        dialog.show();
	}
    
	   private class HelloWebViewClient extends WebViewClient { 
	        @Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) { 
	            view.loadUrl(url); 
	            return true; 
	        } 
	    } 
}



