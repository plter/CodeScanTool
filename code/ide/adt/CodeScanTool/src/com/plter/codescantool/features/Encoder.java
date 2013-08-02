package com.plter.codescantool.features;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.client.android.R;
import com.google.zxing.client.android.encode.EncodeActivity;
import com.plter.lib.java.lang.ICallback;

public abstract class Encoder extends FeatureListCellData{

	public Encoder(Context context, int labelRes) {
		super(context, labelRes);
	}

	public Intent makeIntent(CharSequence type, CharSequence data){
		Intent intent = new Intent();
		intent.addCategory(Intent.CATEGORY_DEFAULT);
		intent.setClass(getContext(),EncodeActivity.class);
		intent.setAction(BS_PACKAGE + ".ENCODE");
		intent.putExtra("ENCODE_TYPE", type);
		intent.putExtra("ENCODE_DATA", data);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		return intent;
	}
	
	public Intent makeIntent(CharSequence type, Bundle data){
		Intent i = makeIntent(type, data.toString());
		i.putExtra("ENCODE_DATA", data);
		return i;
	}
	
	public void showDialog(final View view,final ICallback<Void> okCb){
		if (view.getParent()!=null) {
			((ViewGroup) view.getParent()).removeView(view);
		}
		
		new AlertDialog.Builder(getContext()).setView(view).setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				okCb.execute(null);
			}
		}).setNegativeButton(R.string.button_cancel, null).setTitle(com.plter.codescantool.R.string.please_input_content).show();
	}
	
	private static final String BS_PACKAGE = "com.google.zxing.client.android";
}
