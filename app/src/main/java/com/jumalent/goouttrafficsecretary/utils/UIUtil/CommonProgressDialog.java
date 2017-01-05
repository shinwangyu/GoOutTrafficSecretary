package com.jumalent.goouttrafficsecretary.utils.uiutil;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jumalent.goouttrafficsecretary.R;

public class CommonProgressDialog extends Dialog {

	Context mContext = null;
	ProgressBar mProgressBar = null;
	TextView mTextView = null;
	
	public CommonProgressDialog(Context context, int theme) {
		super(context, theme);
		
		this.mContext = context;
		
		initDialog();
	}
	
	public CommonProgressDialog(Context context) {
		super(context, R.style.CommonProgressDialog);
		
		this.mContext = context;
		
		initDialog();
	}
	
	public CommonProgressDialog(Context context, String message) {
		super(context, R.style.CommonProgressDialog);
		
		this.mContext = context;
		
		initDialog(message);
	}
	
	private void initDialog() {
		initDialog("");
	}
	
	private void initDialog(String message)
	{
		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.common_progress_dialog);
		
		mProgressBar = (ProgressBar)findViewById(R.id.progress);
		mTextView = (TextView)findViewById(R.id.message);
		
		if(!message.equalsIgnoreCase("")) {
			setText(message);
		}
	}
	
	public void setText(String message) {
		if(mTextView != null) 
			mTextView.setText(message);
	}
	
	
//	private void initDialog()
//	{
//		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//		
//		setContentView(R.layout.common_progress_dialog);
//		
//		mProgressBar = (ProgressBar)findViewById(R.id.progress);
//	}
}
