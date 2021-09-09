package com.example.movieapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.movieapp.adapter.BannerAdapter;
import com.example.movieapp.adapter.RVMainAdapter;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.model.AllCategory;
import com.example.movieapp.model.BannerModel;
import com.example.movieapp.network.MovieAPI;
import com.example.movieapp.network.RetrofitBuilder;
import com.example.movieapp.viewmodel.MovieViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<BannerModel> homeBannerList, tvShowsBannerList, movieBannerList, kidsBannerList;
    private BannerAdapter homeBannerAdapter, tvShowsBannerAdapter, movieBannerAdapter, kidsBannerAdapter;
    private RVMainAdapter rvMainAdapter;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.mainToolbar.appBar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Movie Streaming App");
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        setBannerInfo();
        setupBannerWithTabLayout();
        autoSlider();
        initHomeMovies();
    }

    private void setupBannerWithTabLayout() {
        //default
        binding.mainViewPager.setAdapter(homeBannerAdapter);
        binding.mainTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        binding.mainViewPager.setAdapter(homeBannerAdapter);
                        return;

                    case 1:
                        binding.mainViewPager.setAdapter(tvShowsBannerAdapter);
                        return;

                    case 2:
                        binding.mainViewPager.setAdapter(movieBannerAdapter);
                        return;

                    case 3:
                        binding.mainViewPager.setAdapter(kidsBannerAdapter);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setBannerInfo() {
        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerModel(1, "City one", "https://images.unsplash.com/photo-1541267226650-673e4bc869c7?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx9&ixlib=rb-1.2.1&auto=format&fit=crop&w=1780&q=80", ""));
        homeBannerList.add(new BannerModel(2, "City two", "https://images.unsplash.com/photo-1493976040374-85c8e12f0c0e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1350&q=80", ""));
        homeBannerList.add(new BannerModel(3, "City three", "https://images.unsplash.com/photo-1574236170901-59c2f5c99c75?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1491&q=80", ""));
        homeBannerList.add(new BannerModel(4, "City four", "https://images.unsplash.com/photo-1587974136444-493b758688b8?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80", ""));
        homeBannerAdapter = new BannerAdapter(this, homeBannerList);

        tvShowsBannerList = new ArrayList<>();
        tvShowsBannerList.add(new BannerModel(1, "View one", "https://i.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68", ""));
        tvShowsBannerList.add(new BannerModel(2, "View two", "https://i.picsum.photos/id/1000/5626/3635.jpg?hmac=qWh065Fr_M8Oa3sNsdDL8ngWXv2Jb-EE49ZIn6c0P-g", ""));
        tvShowsBannerList.add(new BannerModel(3, "View three", "https://i.picsum.photos/id/1002/4312/2868.jpg?hmac=5LlLE-NY9oMnmIQp7ms6IfdvSUQOzP_O3DPMWmyNxwo", ""));
        tvShowsBannerList.add(new BannerModel(4, "View four", "https://i.picsum.photos/id/1015/6000/4000.jpg?hmac=aHjb0fRa1t14DTIEBcoC12c5rAXOSwnVlaA5ujxPQ0I", ""));
        tvShowsBannerAdapter = new BannerAdapter(this, tvShowsBannerList);

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerModel(1, "Movie one", "https://i.picsum.photos/id/1025/4951/3301.jpg?hmac=_aGh5AtoOChip_iaMo8ZvvytfEojcgqbCH7dzaz-H8Y", ""));
        movieBannerList.add(new BannerModel(2, "Movie two", "https://i.picsum.photos/id/1024/1920/1280.jpg?hmac=-PIpG7j_fRwN8Qtfnsc3M8-kC3yb0XYOBfVzlPSuVII", ""));
        movieBannerList.add(new BannerModel(3, "Movie three", "https://i.picsum.photos/id/1020/4288/2848.jpg?hmac=Jo3ofatg0fee3HGOliAIIkcg4KGXC8UOTO1dm5qIIPc", ""));
        movieBannerList.add(new BannerModel(4, "Movie four", "https://i.picsum.photos/id/1003/1181/1772.jpg?hmac=oN9fHMXiqe9Zq2RM6XT-RVZkojgPnECWwyEF1RvvTZk", ""));
        movieBannerAdapter = new BannerAdapter(this, movieBannerList);

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerModel(1, "Funny one", "https://i.picsum.photos/id/1005/5760/3840.jpg?hmac=2acSJCOwz9q_dKtDZdSB-OIK1HUcwBeXco_RMMTUgfY", ""));
        kidsBannerList.add(new BannerModel(2, "Funny two", "https://i.picsum.photos/id/1010/5184/3456.jpg?hmac=7SE0MNAloXpJXDxio2nvoshUx9roGIJ_5pZej6qdxXs", ""));
        kidsBannerList.add(new BannerModel(3, "Funny three", "https://i.picsum.photos/id/1012/3973/2639.jpg?hmac=s2eybz51lnKy2ZHkE2wsgc6S81fVD1W2NKYOSh8bzDc", ""));
        kidsBannerList.add(new BannerModel(4, "Funny four", "https://i.picsum.photos/id/1014/6016/4000.jpg?hmac=yMXsznFliL_Y2E2M-qZEsOZE1micNu8TwgNlHj7kzs8", ""));
        kidsBannerAdapter = new BannerAdapter(this, kidsBannerList);
    }

    private void autoSlider() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(() -> {
                    if (binding.mainViewPager.getCurrentItem() < 3)
                        binding.mainViewPager.setCurrentItem(binding.mainViewPager.getCurrentItem() + 1);
                    else
                        binding.mainViewPager.setCurrentItem(0);
                });
            }
        }, 4000, 6000);
        binding.mainTlIndicator.setupWithViewPager(binding.mainViewPager, true);
    }

    private void initHomeMovies() {
        rvMainAdapter = new RVMainAdapter(this);
        movieViewModel.getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    binding.mainPb.setVisibility(View.GONE);
                    rvMainAdapter.setList(result.getCategories().get(0).getVideos());
                }, error -> {
                    binding.mainPb.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                });

        binding.mainRv.setLayoutManager(new GridLayoutManager(this, 2));
        binding.mainRv.setHasFixedSize(true);
        binding.mainRv.setAdapter(rvMainAdapter);
    }
}