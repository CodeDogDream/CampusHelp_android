package com.dream.work.campushelp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.dream.work.campushelp.Interface.ImageDownloadCallback;
import com.dream.work.campushelp.R;
import com.dream.work.campushelp.entity.LocationInfo;
import com.dream.work.campushelp.entity.MenuInfo;
import com.dream.work.campushelp.utils.BitmapUtils;
import com.dream.work.campushelp.utils.DownImageService;
import com.dream.work.campushelp.view.adapter.MenuRecyclerViewAdapter;

import java.util.ArrayList;


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        thisActivity = this;
        mapView = (MapView) findViewById(R.id.activity_map_view);
        mapView.showZoomControls(false);
        initRecyclerView();
        initBaiduMap();
        initLocation();
        addMarker();
        event();
    }

    private void initRecyclerView() {
        ArrayList<MenuInfo> arrayList = new ArrayList<>();
        arrayList.add(new MenuInfo(R.drawable.login_background, "22ss"));
        arrayList.add(new MenuInfo(R.drawable.login_background, "22ss"));
        arrayList.add(new MenuInfo(R.drawable.login_background, "22ss"));
        arrayList.add(new MenuInfo(R.drawable.login_background, "22ss"));
        mRecyclerView = (RecyclerView) findViewById(R.id.activity_map_recycler_view);
        MenuRecyclerViewAdapter menuRecyclerViewAdapter = new MenuRecyclerViewAdapter(thisActivity, arrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(thisActivity));
        mRecyclerView.setAdapter(menuRecyclerViewAdapter);
    }

    private void event() {
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String text = marker.getExtraInfo().getString("info");
                if (!TextUtils.isEmpty(text)) {
                    Toast.makeText(thisActivity, text + "icon", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    private void initBaiduMap() {
        baiduMap = mapView.getMap();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(18f);
        baiduMap.setMapStatus(mapStatusUpdate);
        baiduMap.setMaxAndMinZoomLevel(20f, 18f);
        baiduMap.setMyLocationEnabled(true);
        baiduMap.getUiSettings().setCompassEnabled(false);
    }

    private void addMarker() {
        String text = Thread.currentThread().getName();
        Log.v("TAG", text);
        ArrayList<String> urls = new ArrayList<>();
        ArrayList<LatLng> latLngs = new ArrayList<>();
        urls.add("http://img.ph.126.net/mVDQm3RN0b0WZaNZoB1-WQ==/2567896212548203624.jpg");
        urls.add("http://img.ph.126.net/mVDQm3RN0b0WZaNZoB1-WQ==/2567896212548203624.jpg");
        latLngs.add(new LatLng(31.3055805188, 120.5921600276));
        latLngs.add(new LatLng(31.2785508589, 120.6403541565));
        DownImageService.getInstance().setData(thisActivity, urls, new ImageDownloadCallback() {
            @Override
            public void onDownLoadSuccess(ArrayList<LocationInfo> files) {
                String text = Thread.currentThread().getName();
                Log.v("TAG", text);
                for (LocationInfo info : files) {
                    BitmapDescriptor bitmapDescriptor = BitmapUtils.getMarker(info.file);
                    Bundle bundle = new Bundle();
                    bundle.putString("info", "icon1");
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
        }, latLngs).start();
    }

    private void initLocation() {
        mLocationClient = new LocationClient(thisActivity);
        mLocationClient.registerLocationListener(new MyLocationListener());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(span);
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
        if (mLocationClient.isStarted())
            mLocationClient.stop();
    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (isFirstIn) {
                current_location = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(current_location);
                baiduMap.setMapStatus(mapStatusUpdate);
                isFirstIn = false;
            }
            final BitmapDescriptor[] bitmapDescriptor = {null};

//            DownImageService.getInstance().setData(thisActivity, "http://k1.jsqq.net/uploads/allimg/1612/140F5A32-6.jpg", new ImageDownloadCallback() {
//                @Override
//                public void onDownLoadSuccess(File file) {
//                    bitmapDescriptor[0] = BitmapDescriptorFactory.fromBitmap(BitmapUtils.toRoundBitmap(file));
//                }
//
//                @Override
//                public void onDownLoadFail() {
//
//                }
//            }).run();
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
