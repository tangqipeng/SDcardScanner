package com.androidcycle.sdcardscanner.storage;

import java.io.File;
import java.util.ArrayList;

import com.androidcycle.sdcardscanner.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class GetMountedDevicesActivity extends Activity {
	/** Called when the activity is first created. */
	private TextView tvInternal, tvExternal;
	private ListView lvInternal, lvExternal;
	private TextView tvEmpty;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvEmpty = new TextView(this);
		tvEmpty.setText("No Files or Dirs.");
		Dev_MountInfo dev = Dev_MountInfo.getInstance();
		tvInternal = (TextView) findViewById(R.id.Internal_SDview);
		tvExternal = (TextView) findViewById(R.id.External_SDview);

		lvInternal = (ListView) findViewById(R.id.listviewInternalSD);
		lvExternal = (ListView) findViewById(R.id.listviewExternalSD);
		lvInternal.setEmptyView(tvEmpty);
		lvExternal.setEmptyView(tvEmpty);

		Dev_MountInfo.DevInfo info = dev.getInternalInfo();
		ArrayList<String> inArry = new ArrayList<String>();
		if(info != null){
			tvInternal.setText(info.getPath());
			File[] internal = new File(info.getPath()).listFiles();
			for (File inF : internal)
				inArry.add(inF.getName());
		}
		lvInternal.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, inArry));
		
		info = dev.getExternalInfo();
		ArrayList<String> exArry = new ArrayList<String>();
		if(info != null){
			tvExternal.setText(info.getPath());
	
			File[] external = new File(info.getPath()).listFiles();
			if (null != external){
				for (File exF : external)
					exArry.add(exF.getName());
			}
		}
		lvExternal.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, exArry));
	}
}