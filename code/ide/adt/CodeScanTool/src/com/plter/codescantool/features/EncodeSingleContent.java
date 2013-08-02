package com.plter.codescantool.features;

import com.plter.codescantool.R;
import com.plter.lib.java.lang.ICallback;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncodeSingleContent extends Encoder {

	public EncodeSingleContent(Context context, int labelRes,String contentType) {
		super(context, labelRes);
		
		this.contentType = contentType;

		buildUI();
	}



	public void buildUI(){
		view = LayoutInflater.from(getContext()).inflate(R.layout.input_dialog_view, null);
		etInput = (EditText) view.findViewById(R.id.etInput);
	}


	@Override
	public void launch() {

		showDialog(view, new ICallback<Void>() {

			@Override
			public boolean execute(Void arg) {
				if (!TextUtils.isEmpty(etInput.getText())) {
					getContext().startActivity(makeIntent(contentType, etInput.getText().toString()));
				}else{
					Toast.makeText(getContext(), R.string.content_cannot_be_null, Toast.LENGTH_SHORT).show();
				}

				return false;
			}
		});
	}


	private String contentType=null;
	private EditText etInput;
	private View view;

}
