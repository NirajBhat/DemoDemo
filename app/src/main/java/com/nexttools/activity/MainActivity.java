package com.nexttools.activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.nexttools.R;
import com.nexttools.adapter.PagerAdapter;
import com.nexttools.adapter.SubjectsDataAdapter;
import com.nexttools.constants.NlpConstants;
import com.nexttools.model.Cards;
import com.nexttools.model.Guide;
import com.nexttools.model.NextToolMainObject;
import com.nexttools.model.Tabs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    TabLayout mTabLayout;
    ViewPager mViewPager;
    private ActionBar mActionBar;
    ArrayList<String> firstIterate = new ArrayList<>();
    ArrayList<Tabs> tabsArrayList = new ArrayList<>();
    NextToolMainObject nextToolMainObject = new NextToolMainObject();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing the tablayout
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.Maths)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.Chemistry)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.Social)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.English)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getResources().getString(R.string.Primary)));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        loadJSONfromAssets();

        //Initializing viewPager
        mViewPager = (ViewPager) findViewById(R.id.Viewpager);

        //Creating our pager adapter
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());

        //Adding adapter to pager
        mViewPager.setAdapter(adapter);
        //mTabLayout.setupWithViewPager(mViewPager);
        //Adding onTabSelectedListener to swipe views
        mTabLayout.setOnTabSelectedListener(this);

       // SubjectsDataAdapter adapter1= new SubjectsDataAdapter(firstIterate,this);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
              //   mActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        // on tabsArrayList selected
        // show respected fragment view
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public String loadJSONfromAssets() {
        String json = null;
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("jsonData.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray_Tabs = jsonObj.getJSONArray("tabs");
            for (int i = 0; i < jsonArray_Tabs.length(); i++) {
                ArrayList<Cards> cardsArrayList = new ArrayList<>();
                Tabs tabData = new Tabs();

                JSONObject jsonObject = jsonArray_Tabs.getJSONObject(i);

                String strTitle1 = jsonObject.getString("title");
                String strInfo1 = jsonObject.getString("info");
                String strIcon = jsonObject.getString("icon");
                tabData.setTitle(strTitle1);
                tabData.setIcon(strIcon);
                tabData.setInfo(strInfo1);



                JSONArray jsonArray_Cards = jsonObject.getJSONArray("cards");
                for (int j = 0; j < jsonArray_Cards.length(); j++) {
                    ArrayList<Guide> guideArrayList = new ArrayList<>();
                    Cards cardData = new Cards();

                    JSONObject jsonObject1 = jsonArray_Cards.getJSONObject(j);
                    String strTitle2 = jsonObject1.getString("title");
                    String strThumb1 = jsonObject1.getString("thumb");
                    String strInfo2 = jsonObject1.getString("info");
                    String strURL = jsonObject1.getString("url");
                    cardData.setTitle(strTitle2);
                    cardData.setThumb(strThumb1);
                    cardData.setInfo(strInfo2);
                    cardData.setUrl(strURL);


                    JSONArray jsonArray_Guide = jsonObject1.getJSONArray("guide");
                    for (int k = 0; k < jsonArray_Guide.length(); k++) {


                        Guide guideData = new Guide();
                        JSONObject jsonObject2 = jsonArray_Guide.getJSONObject(k);
                        String strThumb2 = jsonObject2.getString("thumb");
                        String strTitle3 = jsonObject2.getString("title");
                        String strColor = jsonObject2.getString("color");
                        String strDescription = jsonObject2.getString("description");
                        guideData.setThumb(strThumb2);
                        guideData.setTitle(strTitle3);
                        guideData.setColour(strColor);
                        guideData.setDescription(strDescription);
                        guideArrayList.add(guideData);

                    }
                    cardData.setGuideInfo(guideArrayList);
                    cardsArrayList.add(cardData);
                }
                tabData.setCardInfo(cardsArrayList);
                tabsArrayList.add(tabData);
            }

            NlpConstants.tabsArrayList = tabsArrayList;


        } catch (JSONException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
