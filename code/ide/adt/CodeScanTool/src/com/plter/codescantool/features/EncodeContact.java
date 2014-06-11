package com.plter.codescantool.features;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.plter.codescantool.R;

public class EncodeContact extends Encoder {

	public EncodeContact(Context context, int labelRes) {
		super(context, labelRes);
		
		buildUI();
	}
	
	
	private void buildUI(){
		view = LayoutInflater.from(getContext()).inflate(R.layout.input_contact_dialog_view, null);
		name = (EditText) view.findViewById(R.id.etNameInput);
		email = (EditText) view.findViewById(R.id.etEmailInput);
		phone = (EditText) view.findViewById(R.id.etPhoneNumInput);
		postal = (EditText) view.findViewById(R.id.etPostalInput);
	}
	

	@Override
	public void launch() {
		showDialog(view, new ICallback() {
			
			@Override
			public boolean execute() {
				if (!TextUtils.isEmpty(name.getText())) {
					Bundle bundle = new Bundle();
					bundle.putString(ContactsContract.Intents.Insert.NAME, TextUtils.isEmpty(name.getText())?"":name.getText().toString());
					bundle.putString(ContactsContract.Intents.Insert.PHONE, TextUtils.isEmpty(phone.getText())?"":phone.getText().toString());
					bundle.putString(ContactsContract.Intents.Insert.EMAIL, TextUtils.isEmpty(email.getText())?"":email.getText().toString());
					bundle.putString(ContactsContract.Intents.Insert.POSTAL, TextUtils.isEmpty(postal.getText())?"":postal.getText().toString());
					getContext().startActivity(makeIntent("CONTACT_TYPE", bundle));
				}else{
					Toast.makeText(getContext(), R.string.name_cannot_be_null, Toast.LENGTH_SHORT).show();
				}
				return false;
			}
		});
	}
	
	private View view;
	private EditText name,email,phone,postal;

}
