package com.gta.administrator.cniaoshop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.gta.administrator.cniaoshop.bean.Tab;
import com.gta.administrator.cniaoshop.fragment.TabCategoryFragment;
import com.gta.administrator.cniaoshop.fragment.TabHomeFragment;
import com.gta.administrator.cniaoshop.fragment.TabHotFragment;
import com.gta.administrator.cniaoshop.fragment.TabPersonFragment;
import com.gta.administrator.cniaoshop.fragment.TabShopFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;

    private List<Tab> list = new ArrayList<>(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTab();

    }

    private void initTab() {
        Tab tab1 = new Tab(R.drawable.tab_home_selector, "home", "主页", TabHomeFragment.class);
        Tab tab2 = new Tab(R.drawable.tab_hot_selector, "hot", "热卖", TabHotFragment.class);
        Tab tab3 = new Tab(R.drawable.tab_category_selector, "category", "种类", TabCategoryFragment.class);
        Tab tab4 = new Tab(R.drawable.tab_shop_selector, "shop", "购物车", TabShopFragment.class);
        Tab tab5 = new Tab(R.drawable.tab_person_selector, "person", "我", TabPersonFragment.class);

        list.add(tab1);
        list.add(tab2);
        list.add(tab3);
        list.add(tab4);
        list.add(tab5);

        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : list) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tab.getTag());
            tabSpec.setIndicator(buildView(tab));
            mTabHost.addTab(tabSpec, tab.getmFragment(), null);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabHost.setCurrentTabByTag(tab1.getTag());
    }

    private View buildView(Tab tab) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_indicator, null);
        ImageView tabImg = (ImageView) view.findViewById(R.id.tab_img);
        TextView text = (TextView) view.findViewById(R.id.tab_text);
        tabImg.setImageResource(tab.getIcon());
        text.setText(tab.getText());
        return view;
    }

}
