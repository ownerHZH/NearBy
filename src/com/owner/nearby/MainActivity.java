package com.owner.nearby;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.owner.adapter.CommonAdapter;
import com.owner.adapter.ViewHolder;
import com.owner.constant.Constants;
import com.owner.entity.Location;
import com.owner.entity.PoiEntity;
import com.owner.iface.Ilocation;
import com.owner.util.HttpUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @author owner
 * @date 2014-09-05
 * @qq 756699074
 */
public class MainActivity extends Activity implements Ilocation,OnGetPoiSearchResultListener,OnClickListener  {

	Context context;
	Button btnPre,btnNext;
	ListView lvContent;
	private CommonAdapter<PoiEntity> mAdapter;//ListView显示的适配器
	List<PoiEntity> poiEntityList=new ArrayList<PoiEntity>();//保存搜索信息列表
	LocationClient mLocationClient;//定位对象
	private PoiSearch mPoiSearch = null;//查询对象声明
	boolean ldup=true;//防止多查询 控制是否查询
	BDLocation location;//保存定位地址
	int pageNum=0;//页数
	String queryKeyWord="餐饮";//搜索附近的关键字
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=MainActivity.this;
		btnPre=(Button) findViewById(R.id.btnPre);
		btnPre.setOnClickListener(this);
		btnNext=(Button) findViewById(R.id.btnNext);
		btnNext.setOnClickListener(this);
		lvContent=(ListView) findViewById(R.id.lvContent);
		
		mLocationClient=((NearByApplication)getApplication()).mLocationClient;
		((NearByApplication)getApplication()).setiLocation(this);//注册定位监听器
		mLocationClient.start();
		
		mPoiSearch=PoiSearch.newInstance();//搜索对象初始化
		mPoiSearch.setOnGetPoiSearchResultListener(this);//注册搜索监听器
		
		//通用adapter初始化
		mAdapter=new CommonAdapter<PoiEntity>(context, poiEntityList, R.layout.list_item) {
			
			@Override
			public void convert(ViewHolder helper, PoiEntity item) {
				helper.setText(R.id.tvTitle, item.getName());
				helper.setText(R.id.tvAddress, item.getAddress());
				helper.setText(R.id.tvPhone, item.getTelephone());
			}
		};
		lvContent.setAdapter(mAdapter);//为ListView注册adapter适配器
		
		//getWebData("酒店");
		
		/*HttpUtil.get(url, new JsonHttpResponseHandler(){

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONObject response) {
				super.onSuccess(statusCode, headers, response);
				try {
					int status=response.getInt("status");
					String message=response.getString("message");
					JSONArray results=response.getJSONArray("results");
					Log.e("status", status+"");
					Log.e("message", message+"");
					Log.e("results", results.length()+"");
					for(int i=0;i<results.length();i++)
					{
						PoiEntity poiEntity=new PoiEntity();
						JSONObject result= results.getJSONObject(i);
						poiEntity.setName(result.getString("name"));
						JSONObject locObj=result.getJSONObject("location");
						poiEntity.setLocation(new Location(locObj.getDouble("lat"), locObj.getDouble("lng")));
						poiEntity.setAddress(result.getString("address"));
						poiEntity.setStreet_id(result.getString("street_id"));
						poiEntity.setTelephone(result.getString("telephone"));
						poiEntity.setUid(result.getString("uid"));
						poiEntityList.add(poiEntity);
					}
					mAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				super.onFailure(statusCode, headers, responseString, throwable);
				Log.e("status", statusCode+"");
			}
			
		});*/
		
	}
	private void getWebData(String q) {
		try {
			q=URLEncoder.encode(q, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String uuurl="http://api.map.baidu.com/place/v2/search?ak="+Constants.BDWEB_AK+"&output=json&query="+q+"&page_size=10&page_num=0&scope=1&location=39.915,116.404&radius=2000";
		//String url="http://api.map.baidu.com/place/v2/search?&query=%E9%93%B6%E8%A1%8C&region=%E6%B5%8E%E5%8D%97&output=json&ak=E4805d16520de693a3fe707cdc962045";
		
		Log.e("url==", uuurl);
		HttpUtil.get(uuurl, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				try {
					Log.e("结果===", new String(arg2,"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
					Log.e("结果 失败===", "结果 失败===");
			}
		});
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mLocationClient!=null)
			mLocationClient.stop();
		if(mPoiSearch!=null)
		    mPoiSearch.destroy();
	}
	
	//定位的回调函数
	@Override
	public void processLocation(BDLocation location) {	
		this.location=location;
		if(ldup)//第一次查询 因为地点是没秒更新一次，防止连续查询
		{
			searchPOI(0,queryKeyWord);
		}
		ldup=false;
	}
	/**
	 * 根据多少页查询
	 * @param pageNum
	 */
	private void searchPOI(int pageNum,String query) {
		PoiNearbySearchOption op=new PoiNearbySearchOption();
		op.keyword(query).location(new LatLng(location.getLatitude(),location.getLongitude())).pageNum(pageNum).radius(2000);
		mPoiSearch.searchNearby(op);
	}
	
	//查询的详细回调函数
	@Override
	public void onGetPoiDetailResult(PoiDetailResult detailResult) {
		
	}
	//查询的简单结果
	@Override
	public void onGetPoiResult(PoiResult result) {
		if (result == null
				|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {		
			List<PoiInfo> poiInfos=result.getAllPoi();
			if(poiInfos!=null&&poiInfos.size()>0)
			{
				poiEntityList.clear();
				for(int i=0;i<poiInfos.size();i++)
				{
					PoiInfo poiInfo =poiInfos.get(i);
					if(poiInfo!=null)
					{
						PoiEntity poiEntity=new PoiEntity();
						poiEntity.setAddress(poiInfo.address);
						poiEntity.setLocation(new Location(poiInfo.location.latitude, poiInfo.location.longitude));
						poiEntity.setName(poiInfo.name);
						poiEntity.setStreet_id(poiInfo.city+poiInfo.postCode);
						poiEntity.setTelephone(poiInfo.phoneNum);
						poiEntity.setUid(poiInfo.uid);
						poiEntityList.add(poiEntity);
					}
				}
				mAdapter.notifyDataSetChanged();
			}
			return;
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
			Toast.makeText(context, "附近没有你想找的呢...", Toast.LENGTH_LONG).show();
		}
	}
	//按钮点击事件
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnPre:
			pageNum--;
			if(pageNum<=0)
			{
				pageNum=0;
			}			
			searchPOI(pageNum,queryKeyWord);
			break;
        case R.id.btnNext:
        	pageNum++;
        	searchPOI(pageNum,queryKeyWord);
			break;
		}
	}
	
	//获取当前搜索的关键字
	public String getQueryKeyWord() {
		return queryKeyWord;
	}
	//设置搜索关键字
	public void setQueryKeyWord(String queryKeyWord) {
		this.queryKeyWord = queryKeyWord;
	}
	
	
}
