package com.rakantao.pcg.lacostazamboanga.PCGStations.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.rakantao.pcg.lacostazamboanga.LoginActivity;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.PCGAdminHome;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.ProfilesActivity;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Activities.TrackActivity;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments.ChatAdminFragment;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments.NewsFeedAdminFragment;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments.NotifAdminFragment;
import com.rakantao.pcg.lacostazamboanga.PCGAdmin.Fragments.ParentTabTimeMonitorFragment;
import com.rakantao.pcg.lacostazamboanga.PublicUser.Fragments.FragmentWeather;
import com.rakantao.pcg.lacostazamboanga.R;

import java.util.ArrayList;
import java.util.List;

public class PcgStationAdminHome extends AppCompatActivity {

    BoomMenuButton boomMenuButton;
    FirebaseAuth firebaseAuth;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcg_station_admin_home);


        firebaseAuth = FirebaseAuth.getInstance();

        boomMenuButton = findViewById(R.id.boomStationAdmin);
        boomMenuButton.setPiecePlaceEnum(PiecePlaceEnum.DOT_4_1);
        boomMenuButton.setButtonPlaceEnum(ButtonPlaceEnum.SC_4_1);
        initBoomMenu();

        viewPager = findViewById(R.id.viewpagerStationAdmin);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabsStationAdmin);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(1).setIcon(R.drawable.timleft);
        tabLayout.getTabAt(2).setIcon(R.drawable.clouds);
        tabLayout.getTabAt(3).setIcon(R.drawable.chat1);
        tabLayout.getTabAt(4).setIcon(R.drawable.notification);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ParentTabTimeMonitorFragment(), "");
        adapter.addFragment(new FragmentWeather(), "");
        adapter.addFragment(new ChatAdminFragment(), "");
        adapter.addFragment(new NotifAdminFragment(), "");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void initBoomMenu() {
        this.boomMenuButton.setButtonEnum(ButtonEnum.TextOutsideCircle);

        TextOutsideCircleButton.Builder newBuilder2 = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {

            }
        });
        TextOutsideCircleButton.Builder newBuilder3 = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {

            }
        });
        TextOutsideCircleButton.Builder newBuilder4 = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {

            }
        });
        TextOutsideCircleButton.Builder newBuilder5 = new TextOutsideCircleButton.Builder().listener(new OnBMClickListener() {
            @Override
            public void onBoomButtonClick(int index) {
                firebaseAuth.signOut();
                startActivity(new Intent(PcgStationAdminHome.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        newBuilder2.normalImageRes(R.drawable.set).normalText("Settings").imagePadding(new Rect(25, 25, 25, 25)).textSize(14);
        newBuilder3.normalImageRes(R.drawable.question).normalText("About us").imagePadding(new Rect(25, 25, 25, 25)).textSize(14);
        newBuilder4.normalImageRes(R.drawable.bug).normalText("Report Problem").imagePadding(new Rect(25, 25, 25, 25)).textSize(14);
        newBuilder5.normalImageRes(R.drawable.powerbutton).normalText("Log Out").imagePadding(new Rect(25, 25, 25, 25)).textSize(14);

        this.boomMenuButton.addBuilder(newBuilder2);
        this.boomMenuButton.addBuilder(newBuilder3);
        this.boomMenuButton.addBuilder(newBuilder4);
        this.boomMenuButton.addBuilder(newBuilder5);

    }

    boolean twice;
    @Override
    public void onBackPressed() {
        if (twice == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        Toast.makeText(this, "Please press BACK again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
        }, 3000);
        twice = true;
    }
}
