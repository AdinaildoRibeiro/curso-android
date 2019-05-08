package br.com.tabviewapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.com.tabviewapp.R;
import br.com.tabviewapp.fragments.EightFragment;
import br.com.tabviewapp.fragments.FiveFragment;
import br.com.tabviewapp.fragments.FourFragment;
import br.com.tabviewapp.fragments.NineFragment;
import br.com.tabviewapp.fragments.OneFragment;
import br.com.tabviewapp.fragments.SevenFragment;
import br.com.tabviewapp.fragments.SixFragment;
import br.com.tabviewapp.fragments.TenFragment;
import br.com.tabviewapp.fragments.ThreeFragment;
import br.com.tabviewapp.fragments.TwoFragment;

public class ScrollableTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "UM");
        adapter.addFrag(new TwoFragment(), "DOIS");
        adapter.addFrag(new ThreeFragment(), "TRES");
        adapter.addFrag(new FourFragment(), "QUATRO");
        adapter.addFrag(new FiveFragment(), "CINCO");
        adapter.addFrag(new SixFragment(), "SEIS");
        adapter.addFrag(new SevenFragment(), "SETE");
        adapter.addFrag(new EightFragment(), "OITO");
        adapter.addFrag(new NineFragment(), "NOVE");
        adapter.addFrag(new TenFragment(), "DEZ");
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

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
