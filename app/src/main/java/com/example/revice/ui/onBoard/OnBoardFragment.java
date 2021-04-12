package com.example.revice.ui.onBoard;

import android.app.Application;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.revice.App;
import com.example.revice.MainActivity;
import com.example.revice.R;
import com.example.revice.databinding.ActivityMainBinding;
import com.example.revice.databinding.FragmentOnBoardBinding;
import com.example.revice.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;


public class OnBoardFragment extends Fragment {

    private FragmentOnBoardBinding binding;
    private OnBoardAdapter onBoardAdapter;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        binding = FragmentOnBoardBinding.inflate(inflater, container, false);
        setUpOnBoardItem();
        binding.ViewPagerOnBoard.setAdapter(onBoardAdapter);
        binding.dotsIndicator.setViewPager2(binding.ViewPagerOnBoard);

        binding.ViewPagerOnBoard.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    binding.buttonOnBoard.setText("Finish");
                    binding.buttonOnBoard.setOnClickListener(v -> {
                        navController.navigateUp();
                    });
                } else {
                    binding.buttonOnBoard.setText("Next");
                }
            }
        });

        binding.buttonOnBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.ViewPagerOnBoard.getCurrentItem() + 1 < onBoardAdapter.getItemCount()) {
                    binding.ViewPagerOnBoard.setCurrentItem(binding.ViewPagerOnBoard.getCurrentItem() + 1);
                } else {
                    binding.ViewPagerOnBoard.setCurrentItem(binding.ViewPagerOnBoard.getCurrentItem());
                }
            }
        });
        App.share.saveBoardShown(true);

        return binding.getRoot();
    }

    private void setUpOnBoardItem() {
        List<OnBoardItem> list = new ArrayList<>();

        OnBoardItem firstPage = new OnBoardItem();
        firstPage.setTitle("wdveveve");
        firstPage.setDescription("sfvkllkvnlc");
        firstPage.setImage(R.drawable.onboard);

        OnBoardItem secondPage = new OnBoardItem();
        secondPage.setTitle("sdvsv");
        secondPage.setDescription("svwvc");
        secondPage.setImage(R.drawable.second);

        OnBoardItem thirdPage = new OnBoardItem();
        thirdPage.setTitle("vsvwvwec");
        thirdPage.setDescription("sdvwcw");
        thirdPage.setImage(R.drawable.third);

        list.add(firstPage);
        list.add(secondPage);
        list.add(thirdPage);

        onBoardAdapter = new OnBoardAdapter(list);
    }
}


