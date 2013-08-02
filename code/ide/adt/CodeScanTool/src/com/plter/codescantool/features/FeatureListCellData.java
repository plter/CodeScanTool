package com.plter.codescantool.features;

import android.content.Context;

public abstract class FeatureListCellData {


	public FeatureListCellData(Context context, String label) {
		this.label = label;
		this.context=context;
	}
	
	public FeatureListCellData(Context context, int labelRes) {
		this(context,context.getResources().getString(labelRes));
	}
	
	public Context getContext() {
		return context;
	}
	
	public abstract void launch();
	
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	private String label=null;
	private Context context=null;
}
