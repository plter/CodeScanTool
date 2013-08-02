package com.plter.codescantool.features;

import com.google.zxing.client.android.CaptureActivity;

import android.content.Context;
import android.content.Intent;

public class ScanCode extends FeatureListCellData {

	public ScanCode(Context context, int labelRes) {
		super(context, labelRes);
	}

	@Override
	public void launch() {
		Intent i = new Intent(getContext(), CaptureActivity.class);
		getContext().startActivity(i);
	}

}
