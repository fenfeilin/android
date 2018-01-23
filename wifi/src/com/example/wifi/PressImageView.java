package com.example.wifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageButton;

public class PressImageView extends ImageButton {

	public PressImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public PressImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PressImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

	@SuppressLint("ClickableViewAccessibility")
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                setAlpha(0.5f);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_OUTSIDE:
                setAlpha(1.0f); 
                break;
        }
        return super.onTouchEvent(event);
    }
}
