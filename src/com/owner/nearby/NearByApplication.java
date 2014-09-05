package com.owner.nearby;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.owner.constant.Constants;
import com.owner.iface.Ilocation;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class NearByApplication extends Application {

	private static NearByApplication application;

	public LocationClient mLocationClient;
	public MyLocationListener mMyLocationListener;
	Ilocation iLocation;

	public void setiLocation(Ilocation iLocation) {
		this.iLocation = iLocation;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		application = this;	
		///�ٶȶ�λ begin
		mLocationClient = new LocationClient(this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient.registerLocationListener(mMyLocationListener);
		InitLocation();
		//mLocationClient.start();
		SDKInitializer.initialize(this);//��ͼ
		///�ٶȶ�λ end
	}

	//ȫ�ֵ�������
	public static synchronized NearByApplication getInstance() {
		if(application!=null)
		    return application;
		else
			return new NearByApplication();
	}

	//ȫ�ֵ�SharedPreferences����
	public static synchronized SharedPreferences getDefaultSharedPreferences(){
		return application.getSharedPreferences("nearby", Context.MODE_PRIVATE);
	}
	
	//��ʼ���ٶȶ�λ����
	private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λģʽ
		option.setCoorType("gcj02");//���صĶ�λ����ǰٶȾ�γ�ȣ�Ĭ��ֵgcj02
		option.setOpenGps(true);
		option.setScanSpan(1000);//���÷���λ����ļ��ʱ��Ϊ5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}
	
	/**
	 * ʵ�ְٶȶ�λʵλ�ص�����
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			iLocation.processLocation(location);
			//Receive Location 
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			Constants.Latitude=location.getLatitude();
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			Constants.Longitude=location.getLongitude();
			Log.i("Longitude==", location.getLongitude()+"");
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation){
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				Constants.AddrStr=location.getAddrStr();
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				Constants.AddrStr=location.getAddrStr();
				//��Ӫ����Ϣ
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
		}
	}
	
}
