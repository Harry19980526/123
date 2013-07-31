package com.android_servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import java.io.*;
import java.net.*;

public class DownloadService extends Service{
	private final String HTTP_URL = "http://140.126.11.163:8080/test.html";
	//private final String HTTP_URL = "http://192.168.1.27/music/At the End of the Day.mp3";
	
	@Override
	public void onCreate() {
		// TODO 自動產生的方法 Stub
		DownloadTask download = new DownloadTask();
		download.execute(HTTP_URL);
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO 自動產生的方法 Stub
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO 自動產生的方法 Stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO 自動產生的方法 Stub
		return null;
	}
	
	public class DownloadTask extends AsyncTask<String, Void, Void>{
		@SuppressWarnings("unused")
		@Override
		protected Void doInBackground(String... params) {
			// TODO 自動產生的方法 Stub
			URL url = null;
			try {
				url = new URL(params[0]);
			} catch (MalformedURLException e) {
				// TODO 自動產生的 catch 區塊
				Log.d("Message", "MalformedURLException~~!!" + e.getMessage());
			}
			try {
				HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
				urlConn.setRequestMethod("POST");
				urlConn.setDoInput(true);
				urlConn.setConnectTimeout(10000);
				//if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK)
				//{
					InputStream is = urlConn.getInputStream();
					File file = new File(android.os.Environment.getExternalStorageDirectory()+"/Download/test.html");
					FileOutputStream fos = new FileOutputStream(file);
					byte[] data = new byte[1024];
					int totalSize = 0,getPerSize = 0;
					while( (getPerSize = is.read(data)) != -1)
					{
						totalSize += getPerSize;
						fos.write(data,0,getPerSize);
					}
					//Toast.makeText(getBaseContext(), "下載完成~!!", Toast.LENGTH_SHORT).show();
					Log.d("Message", "Download Sccessful~!!");
					fos.flush(); fos.close();
					is.close(); urlConn.disconnect();
				//}
				//else Log.d("Message", urlConn.getResponseMessage());
			} catch (IOException e) {
				// TODO 自動產生的 catch 區塊
				Log.d("Message", "IOException~~!!\n" + e.getMessage());
				e.printStackTrace();
			}
			return null;
		}
	}
}
