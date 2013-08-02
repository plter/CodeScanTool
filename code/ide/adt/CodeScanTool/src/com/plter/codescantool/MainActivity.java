package com.plter.codescantool;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.plter.codescantool.features.EncodeContact;
import com.plter.codescantool.features.EncodeSingleContent;
import com.plter.codescantool.features.FeatureListCellData;
import com.plter.codescantool.features.ScanCode;
import com.plter.lib.android.java.controls.ArrayAdapter;
import com.tencent.exmobwin.MobWINManager;
import com.tencent.exmobwin.Type;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MobWINManager.init(this,Type.MOBWIN_BANNER);
		
		adapter = new ArrayAdapter<FeatureListCellData>(this,android.R.layout.simple_list_item_1) {
			
			@Override
			public void initListCell(int position, View listCell, ViewGroup parent) {
				TextView tv = (TextView) listCell;
				
				FeatureListCellData data = getItem(position);
				tv.setText(data.getLabel());
			}
		};
		
		setListAdapter(adapter);
		
		adapter.add(new ScanCode(this,R.string.scan_code));
		adapter.add(new EncodeSingleContent(this, R.string.encode_text, "TEXT_TYPE"));
		adapter.add(new EncodeSingleContent(this, R.string.encode_url, "TEXT_TYPE"));
		adapter.add(new EncodeSingleContent(this, R.string.encode_email, "EMAIL_TYPE"));
		adapter.add(new EncodeSingleContent(this, R.string.encode_sms, "SMS_TYPE"));
		adapter.add(new EncodeContact(this, R.string.encode_contact));
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		adapter.getItem(position).launch();
		super.onListItemClick(l, v, position, id);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		MobWINManager.destroy();
		super.onDestroy();
	}
	
	private ArrayAdapter<FeatureListCellData> adapter;

}
