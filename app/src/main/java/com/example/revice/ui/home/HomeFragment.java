package com.example.revice.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.revice.App;
import com.example.revice.R;
import com.example.revice.databinding.FragmentHomeBinding;
import com.example.revice.ui.home.recycler.HomeAdapter;
import com.example.revice.ui.home.recycler.HomeModel;
import com.example.revice.ui.home.recycler.Listen;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Listen {

    private NavController navController;
    private FragmentHomeBinding binding;
    private HomeAdapter homeAdapter;
    private List<HomeModel> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new HomeAdapter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        App.fillDataBase.fillDao().getAll().observe(getViewLifecycleOwner(), new Observer<List<HomeModel>>() {
            @Override
            public void onChanged(List<HomeModel> homeModelList) {
                homeAdapter.addList(homeModelList);
            }
        });
        binding.rv.setAdapter(homeAdapter);
        click();
        getData();
        return binding.getRoot();
    }

    public void getData() {
        getParentFragmentManager().setFragmentResultListener("key",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String name = result.getString("name");
                        String number = result.getString("number");
                        int id = result.getInt("id");

                        HomeModel model = homeAdapter.getModelToId(id);
                        if (model != null) {
                            model.setTitle(name);
                            model.setDescription(number);
                            App.fillDataBase.fillDao().update(model);
                        } else {
                            App.fillDataBase.fillDao().insert(new HomeModel(name, number));
                        }
                    }
                });


    }

    public void setDataForForm(HomeModel homeModel, int position) {
        Bundle bundle = new Bundle();

        bundle.putString("name2", homeModel.getTitle());
        bundle.putString("number2", homeModel.getDescription());
        bundle.putInt("id", homeModel.getId());
        getParentFragmentManager().setFragmentResult("2", bundle);
        navController.navigate(R.id.action_navigation_home_to_formFragment);
    }

    public void click() {
        binding.fabAdd.setOnClickListener(v -> {
            navController.navigate(R.id.action_navigation_home_to_formFragment);
        });

    }
}
