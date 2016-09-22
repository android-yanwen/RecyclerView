package com.gta.administrator.cniaoshop.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.gta.administrator.cniaoshop.R;
import com.gta.administrator.cniaoshop.adapter.DividerItemDecoration;
import com.gta.administrator.cniaoshop.adapter.MyAdapter;
import com.gta.administrator.cniaoshop.http.BaseCallback;
import com.gta.administrator.cniaoshop.http.OkHttpHelper;
import com.gta.administrator.cniaoshop.http.SpotsCallback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/13.
 */
public class TabHomeFragment extends Fragment {
    private static final String TAG = "TabHomeFragment";

    private Context context;

    private SliderLayout sliderShow;

    private PagerIndicator pagerIndicator;

//    private OkHttpClient httpClient = new OkHttpClient();

    private OkHttpHelper okHttpHelper;

    private RecyclerView recyclerView;

    private MaterialRefreshLayout materialRefreshLayout;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.tab_home_fragment, container, false);

        initView();


        okHttpHelper = OkHttpHelper.getInstance();

        okHttpHelper.get("https://raw.github.com/square/okhttp/master/README.md", new SpotsCallback<String>(getActivity()) {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError() {

            }
        });


        return view;
    }


    private void initView() {

        pagerIndicator = (PagerIndicator) view.findViewById(R.id.custom_indicator);
        sliderShow = (SliderLayout) view.findViewById(R.id.slider);

        TextSliderView textSliderView = new TextSliderView(this.getActivity());
        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
            @Override
            public void onSliderClick(BaseSliderView slider) {
                Toast.makeText(TabHomeFragment.this.getActivity(), "精品推荐", Toast.LENGTH_SHORT).show();
            }
        });
        textSliderView
                .description("精品推荐")
                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t2416/102/20949846/13425/a3027ebc/55e6d1b9Ne6fd6d8f.jpg");
        sliderShow.addSlider(textSliderView);

        TextSliderView textSliderView1 = new TextSliderView(this.getActivity());
        textSliderView1
                .description("时尚男装")
                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1507/64/486775407/55927/d72d78cb/558d2fbaNb3c2f349.jpg");
        sliderShow.addSlider(textSliderView1);

        TextSliderView textSliderView2 = new TextSliderView(this.getActivity());
        textSliderView2
                .description("家电秒杀")
                .image("http://m.360buyimg.com/mobilecms/s300x98_jfs/t1363/77/1381395719/60705/ce91ad5c/55dd271aN49efd216.jpg");
        sliderShow.addSlider(textSliderView2);

//        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderShow.setCustomIndicator(pagerIndicator);
//        sliderShow.setCustomAnimation(new DescriptionAnimation());
        sliderShow.setPresetTransformer(SliderLayout.Transformer.RotateDown);
        sliderShow.setDuration(3000);


        /* init MaterialRefreshLayout */
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.material_refresh);
        // 允许下拉刷新
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束上拉刷新
                        materialRefreshLayout.finishRefresh();
                    }
                }, 3000);
            }

            @Override
            public void onRefreshLoadMore(final MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 结束下拉刷新
                        materialRefreshLayout.finishRefreshLoadMore();
                    }
                }, 3000);
            }
        });

        /*Init RecyclerView*/
        recyclerView = (RecyclerView) view.findViewById(R.id.home_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            datas.add("" + i);
        }
        recyclerView.setAdapter(new MyAdapter(context, datas));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));


    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }
}
