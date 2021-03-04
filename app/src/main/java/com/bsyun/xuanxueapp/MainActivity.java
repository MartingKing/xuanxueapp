package com.bsyun.xuanxueapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String DPG = "dipangan";
    private static final String TPG = "tianpangan";
    private static final String JX = "jiuxing";
    private static final String BS = "bashen";
    private static final String BM = "bamen";
    private long time;
    private TextView shi1, shi2, yue1, yue2, ri1, ri2, nian1, nian2, tv_time, tv_ju, tv_xunshou, tv_zhifu, tv_zhishi;
    private RecyclerView recyclerView;
    private PanAdapter mAdapter;
    private List<Object> panInfo;
    private int[] gongweishu = {4, 9, 2, 3, 5, 7, 8, 1, 6};//正常宫位数
    private int[] gongweishu2 = {4, 9, 2, 7, 6, 1, 8, 3, 5};//使用算法排天盘干的时候对应数组的宫位数
    private List<String> basmenData = new ArrayList<>(8);
    private Map<Integer, String> dipanganMap;
    private Map<Integer, String> tianpanMap = new HashMap<>(9);
    private Map<Integer, String> jiuxingMap = new HashMap<>(9);
    private Map<Integer, String> bashenMap = new HashMap<>(9);
    private Map<Integer, String> bamenMap = new HashMap<>(9);
    private String curShiGan, xunshouGan, shigan, xunshouGan1;
    //值使门
    private String zhishi = "";
    //值符星
    private String zhifu = "";

    //是阴盾还是阳盾
    private boolean isYangdun;
    //旬首干位置
    private int pXunshougan;
    private List<String> dipanganData = new ArrayList<>(10);
    private List<String> tianpanganData = new ArrayList<>(9);
    private List<String> bamenData = new ArrayList<>(9);
    private List<String> bashenData = new ArrayList<>(9);
    private ArrayList<String> jiuxingData = new ArrayList<>(9);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paipan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("奇门排盘");
        initView();
        initData();
        sortListData();
        setData();
    }

    private void initView() {
        nian1 = findViewById(R.id.nian1);
        nian2 = findViewById(R.id.nian2);
        ri2 = findViewById(R.id.ri2);
        ri1 = findViewById(R.id.ri1);
        yue2 = findViewById(R.id.yue2);
        yue1 = findViewById(R.id.yue1);
        shi2 = findViewById(R.id.shi2);
        shi1 = findViewById(R.id.shi1);
        tv_time = findViewById(R.id.tv_time);
        tv_ju = findViewById(R.id.tv_ju);
        tv_xunshou = findViewById(R.id.tv_xunshou);
        tv_zhifu = findViewById(R.id.tv_zhifu);
        tv_zhishi = findViewById(R.id.tv_zhishi);
        recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mAdapter = new PanAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        time = getIntent().getLongExtra(CommmonUtil.TIME, 0);
        XuanxueUtil.initGanZhi(DateUtil.getYearByTimeStamp(time), DateUtil.getMonthByTimeStamp(time), DateUtil.getDayByTimeStamp(time));
        String rigz = XuanxueUtil.getGanZhiRi();
        panInfo = CommmonUtil.getPanInfo(time, rigz);
        curShiGan = XuanxueUtil.getDizhiHour(time);
        xunshouGan = CommmonUtil.getXunshouGan(curShiGan);
        shigan = CommmonUtil.getSpecialShigan(curShiGan);
        Log.e(TAG, "时辰: " + XuanxueUtil.getGanZhi() + " " + curShiGan + "时");
        Log.e(TAG, "时干: " + shigan);
        isYangdun = (boolean) panInfo.get(0);
        int jushu = (int) panInfo.get(3);
        String j = isYangdun ? "阳盾" : "阴盾";
        tv_ju.setText("盾局：" + j + jushu + "局");
        dipanganMap = CommmonUtil.getYinYangTianganMap(isYangdun, jushu);
        xunshouGan1 = xunshouGan;
        sortMapDataToList(dipanganMap, DPG);
        for (int i = 0; i < 9; i++) {
            if (dipanganData.get(i).equals(xunshouGan1)) {
                pXunshougan = i;
            }
        }
    }

    private void setData() {
        nian1.setText(XuanxueUtil.getGanZhiNian().substring(0, 1));
        nian2.setText(XuanxueUtil.getGanZhiNian().substring(1, 2));
        yue1.setText(XuanxueUtil.getGanZhiYue().substring(0, 1));
        yue2.setText(XuanxueUtil.getGanZhiYue().substring(1, 2));
        ri1.setText(XuanxueUtil.getGanZhiRi().substring(0, 1));
        ri2.setText(XuanxueUtil.getGanZhiRi().substring(1, 2));
        shi1.setText(curShiGan.substring(0, 1));
        shi2.setText(curShiGan.substring(1, 2));
        tv_time.setText("日期：" + DateUtil.getDateToString(time));
        tv_xunshou.setText("旬首：" + xunshouGan1);
        List<PanEntity> mdatas = new ArrayList<>(9);
        String pXunkong = CommmonUtil.getXunkong(curShiGan);
        int pMaxing = CommmonUtil.getStarMa(curShiGan);
        String kong1 = pXunkong.substring(0, 1);
        String kong2 = TextUtils.isEmpty(pXunkong.substring(1)) ? kong1 : pXunkong.substring(1);

        for (int i = 0; i < 9; i++) {
            PanEntity entity = new PanEntity();
            entity.setDipangan(CommmonUtil.getDefaultGan(dipanganData.get(i)));
            entity.setTianpangan(CommmonUtil.getDefaultGan(tianpanganData.get(i)));
            entity.setGongwei(CommmonUtil.iconGongwei[i]);
            if (CommmonUtil.getDefaultGan(dipanganData.get(i)).equals(xunshouGan)) {
                tv_zhishi.setText("值使：" + CommmonUtil.yuanBamen[i]);
                Log.e(TAG, "值使: " + i + ":" + CommmonUtil.yuanBamen[i]);
            }
            if (CommmonUtil.getDefaultGan(dipanganData.get(i)).equals(xunshouGan1)) {
                tv_zhifu.setText("值符：" + CommmonUtil.yuanJiuXing[i]);
                Log.e(TAG, "值符: " + i + ":" + CommmonUtil.yuanJiuXing[i]);
            }
            entity.setJiuxing2(jiuxingData.get(i));
            if (jiuxingData.get(i).equals("天芮")) {
                entity.setJiuxing1("天禽");
                entity.setTiangan1(CommmonUtil.getDefaultGan(dipanganData.get(4)));
            }
            entity.setBamen(bamenData.get(i));
            entity.setBashen(bashenData.get(i));
            if ((i == Integer.parseInt(kong1) || i == Integer.parseInt(kong2)) && i == pMaxing) {
                entity.setMakong(R.mipmap.empty_horse);
            }
            if (kong1.equals(kong2)) {
                equalCondition(pMaxing, kong1, i, entity);
            } else {
                equalCondition(pMaxing, kong1, i, entity);
                if (i == Integer.parseInt(kong2) && i == pMaxing) {
                    entity.setMakong(R.mipmap.empty_horse);
                }
                if (i == Integer.parseInt(kong2) && i != pMaxing) {
                    entity.setMakong(R.mipmap.icon_empty);
                }
                if (i == pMaxing && i != Integer.parseInt(kong2)) {
                    entity.setMakong(R.mipmap.icon_horse);
                }
            }
            //TODO 2011-3-17-12-20 该时间下，宫位生克和九星生克有bug
            entity.setMenke(XuanxueUtil.getBamenShengke(CommmonUtil.getMonthByRecyclerPosition(i), bamenData.get(i)));
            entity.setXingke(XuanxueUtil.getJiuxingShengke(i, jiuxingData.get(i)));
            entity.setChangsheng(XuanxueUtil.getZhangsheng(tianpanganData.get(i), CommmonUtil.getDZByRecyclerPosition(i)));
            mdatas.add(i, entity);
        }
        mAdapter.addData(mdatas);
    }
}