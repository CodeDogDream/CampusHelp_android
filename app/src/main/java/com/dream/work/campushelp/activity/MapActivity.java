package com.dream.work.campushelp.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dream.work.campushelp.Interface.ImageDownloadCallback;
import com.dream.work.campushelp.Interface.OnItemClickListener;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.entity.HelpInfo;
import com.dream.work.campushelp.entity.LocationInfo;
import com.dream.work.campushelp.entity.MenuInfo;
import com.dream.work.campushelp.entity.UserInfo;
import com.dream.work.campushelp.helper.Constants;
import com.dream.work.campushelp.helper.ToastHelper;
import com.dream.work.campushelp.network.HelpApiManager;
import com.dream.work.campushelp.network.HttpData;
import com.dream.work.campushelp.network.MySubscriber;
import com.dream.work.campushelp.network.bean.DataBean;
import com.dream.work.campushelp.utils.BitmapUtils;
import com.dream.work.campushelp.utils.DownImageService;
import com.dream.work.campushelp.utils.IntentUtils;
import com.dream.work.campushelp.utils.ParseJsonUtils;
import com.dream.work.campushelp.utils.RuntimeInfo;
import com.dream.work.campushelp.view.adapter.MenuRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HTTP;


/**
 * Created by Dream on 2017/4/12.
 */

public class MapActivity extends AppCompatActivity {
    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient mLocationClient;
    private Activity thisActivity;
    private boolean isFirstIn = true;
    private LatLng current_location;
    private RecyclerView mRecyclerView;
    private RelativeLayout topLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        thisActivity = this;
        mapView = (MapView) findViewById(R.id.activity_map_view);
        mapView.showZoomControls(false);
        topLayout = (RelativeLayout) findViewById(R.id.left_layout_top);

        initRecyclerView();
        initBaiduMap();
        initLocation();

        event();
    }

    public void sendRequestForNearByHelp() {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                List<HelpInfo> infos = ParseJsonUtils.getDataFromList(dataBean.getData(), HelpInfo.class);
                ArrayList<LatLng> lats = new ArrayList<>();
                ArrayList<String> urls = new ArrayList<>();
                ArrayList<Integer> ids = new ArrayList<>();
                for (HelpInfo info : infos) {
                    lats.add(new LatLng(info.getLatitude(), info.getLongitude()));
                    urls.add(info.getUavatar());
                    ids.add(info.getInfoId());
                }
                addMarker(urls, lats, ids);
                sendRequestForUserInfo();
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.GET_NEARBY_HELP_INFO, RuntimeInfo.getInstance().uid, String.valueOf(current_location.longitude), String.valueOf(current_location.latitude));
    }

    public void sendRequestForUserInfo() {
        MySubscriber<DataBean> ss = new MySubscriber<DataBean>() {
            @Override
            public void onNext(DataBean dataBean) {
                super.onNext(dataBean);
                UserInfo userInfo = ParseJsonUtils.getData(dataBean.getData(), UserInfo.class);
                RuntimeInfo.getInstance().saveUserInfo(userInfo);
                changeUserInfo(userInfo);
            }

            @Override
            public void onError(Throwable t) {
                super.onError(t);
            }
        };
        HelpApiManager.getInstance().sendRequest(ss, HttpData.GET_USER_INFO, RuntimeInfo.getInstance().uid);
    }

    private void changeUserInfo(UserInfo userInfo) {
        final ImageView avatar = (ImageView) ((ViewGroup) topLayout.getChildAt(0)).getChildAt(0);
        ((TextView) ((ViewGroup) topLayout.getChildAt(0)).getChildAt(1)).setText(userInfo.getUname());
        ((TextView) topLayout.getChildAt(1)).setText(userInfo.getUintro());
        String url = userInfo.getUavatar();
        Glide.with(thisActivity).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(avatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(thisActivity.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                avatar.setImageDrawable(circularBitmapDrawable);
            }
        });
    }


    private void initRecyclerView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.left_layout_top);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Page_Type, Constants.USER_INFO);
                IntentUtils.Builder().activity(thisActivity).Bundle(bundle).target(MainActivity.class).build();
            }
        });


        ArrayList<MenuInfo> arrayList = new ArrayList<>();
        arrayList.add(new MenuInfo(R.drawable.my_message, "我发的求助信息"));
        arrayList.add(new MenuInfo(R.drawable.location, "附近的求助信息"));
        arrayList.add(new MenuInfo(R.drawable.about_us, "关于我们"));
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_map_recycler_view);
        MenuRecyclerViewAdapter menuRecyclerViewAdapter = new MenuRecyclerViewAdapter(thisActivity, arrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(thisActivity));
        menuRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                String data = null;
                switch (position) {
                    case 0:
                        data = Constants.MY_HELP_INFO;
                        break;
                    case 1:
                        data = Constants.NEARBY_INFO;
                        break;
                    case 2:
                        data = Constants.ABOUT_US;
                        break;
                }
                bundle.putString(Constants.Page_Type, data);
                IntentUtils.Builder().activity(thisActivity).Bundle(bundle).target(MainActivity.class).build();
            }
        });
        mRecyclerView.setAdapter(menuRecyclerViewAdapter);
    }

    private void event() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String text = marker.getExtraInfo().getString("info");
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Page_Type, Constants.HELP_DETAIL_INFO);
                bundle.putString("info_id", text);
                IntentUtils.Builder().activity(thisActivity).Bundle(bundle).target(MainActivity.class).build();
                return false;
            }
        });
        findViewById(R.id.location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestForNearByHelp();
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(current_location);
                baiduMap.animateMapStatus(mapStatusUpdate);

            }
        });
        findViewById(R.id.login_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RuntimeInfo.getInstance().clearData();
                IntentUtils.Builder().target(LoginActivity.class).activity(thisActivity).build();
                finish();
            }
        });
        findViewById(R.id.add_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Constants.Page_Type, Constants.ADD_HELP_INFO);
                IntentUtils.Builder().activity(thisActivity).Bundle(bundle).target(MainActivity.class).build();
            }
        });
    }

    private void initBaiduMap() {
        baiduMap = mapView.getMap();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(18f);
        baiduMap.setMapStatus(mapStatusUpdate);
        baiduMap.setMaxAndMinZoomLevel(20f, 10f);
        baiduMap.setMyLocationEnabled(true);
        baiduMap.getUiSettings().setCompassEnabled(false);
    }

    private void addMarker(ArrayList<String> urls, ArrayList<LatLng> latLngs, ArrayList<Integer> ids) {
        String text = Thread.currentThread().getName();
        Log.v("TAG", text);
        new Thread(DownImageService.getInstance().setData(thisActivity, urls, new ImageDownloadCallback() {
            @Override
            public void onDownLoadSuccess(ArrayList<LocationInfo> files) {
                String text = Thread.currentThread().getName();
                Log.v("TAG", text);
                for (LocationInfo info : files) {
                    BitmapDescriptor bitmapDescriptor = BitmapUtils.getMarker(info.file);
                    Bundle bundle = new Bundle();
                    bundle.putString("info", info.info_id + "");
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.icon(bitmapDescriptor)
                            .position(info.latLng)
                            .extraInfo(bundle);
                    baiduMap.addOverlay(markerOptions);
                }
            }

            @Override
            public void onDownLoadFail() {

            }
        }, latLngs, ids)).start();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(thisActivity);
        mLocationClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(30000);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps
        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mLocationClient.isStarted())
            mLocationClient.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendRequestForUserInfo();
        mapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //主动结束线程
        if (mLocationClient.isStarted())
            mLocationClient.stop();

    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (isFirstIn) {
                current_location = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                RuntimeInfo.getInstance().saveLocation(current_location);
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(current_location);
                baiduMap.setMapStatus(mapStatusUpdate);
                sendRequestForNearByHelp();
                isFirstIn = false;
            }
            final BitmapDescriptor[] bitmapDescriptor = {null};


            MyLocationData data = new MyLocationData.Builder()
                    .longitude(bdLocation.getLongitude())
                    .latitude(bdLocation.getLatitude())
                    .build();
            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, false, bitmapDescriptor[0]);
            baiduMap.setMyLocationConfiguration(configuration);
            baiduMap.setMyLocationData(data);
        }


        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }
}
