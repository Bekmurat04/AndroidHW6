    package com.example.revice.ui.form;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.revice.R;
import com.example.revice.databinding.FragmentFormBinding;
import com.example.revice.ui.home.recycler.HomeModel;


public class FormFragment extends Fragment {

    private FragmentFormBinding binding;
    private String s = "Поле не должно быть пустым";
    private NavController navController;

    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);
        binding = FragmentFormBinding.inflate(inflater, container, false);
        getData();
        initListeners();

        return binding.getRoot();
    }

    public void initListeners() {
        binding.saveButton.setOnClickListener(v -> {
            if (binding.nameItem.getText().toString().isEmpty() && binding.numberItem.getText().toString().isEmpty()) {
                binding.nameItem.setError(s);
                binding.numberItem.setError(s);
            } else if (binding.nameItem.getText().toString().isEmpty()) {
                binding.nameItem.setError(s);
            } else if (binding.numberItem.getText().toString().isEmpty()) {
                binding.numberItem.setError(s);
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("name", binding.nameItem.getText().toString());
                bundle.putString("number", binding.numberItem.getText().toString());
                bundle.putInt("id", id);
                getParentFragmentManager().setFragmentResult("key", bundle);
                close();
            }
        });
    }

    public void getData() {
        getParentFragmentManager().setFragmentResultListener("2", getViewLifecycleOwner(),
                new FragmentResultListener() {

                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        if (requestKey.equals("2")&& result != null){
                            binding.nameItem.setText(result.getString("name2"));
                            binding.numberItem.setText(result.getString("number2"));
                            id = result.getInt("id");


                        }
                    }
                });
    }

private void close(){
navController.navigateUp();
}

}