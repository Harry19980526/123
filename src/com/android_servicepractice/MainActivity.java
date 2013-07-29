package com.android_servicepractice;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity implements OnClickListener {
	
	private Button download_button;
	private TextView messageView;
	private Intent it;
	private DownloadService download;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		download_button = (Button)findViewById(R.id.DownloadButton);
		messageView = (TextView)findViewById(R.id.message_textview);
		download_button.setOnClickListener(this);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onClick(View v) {
		// TODO 自動產生的方法 Stub
		it = new Intent(MainActivity.this,DownloadService.class);
		startService(it);
		stopService(it);
	}

}
