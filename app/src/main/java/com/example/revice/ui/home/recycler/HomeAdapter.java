package com.example.revice.ui.home.recycler;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.renderscript.ScriptGroup;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revice.App;
import com.example.revice.databinding.FragmentHomeBinding;
import com.example.revice.databinding.ItemListhomeBinding;
import com.example.revice.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private ItemListhomeBinding binding;
    private List<HomeModel> list = new ArrayList<>();
    private Listen listen;

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(ItemListhomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent
                , false));

    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public void addList(HomeModel homeModel) {
//        list.add(homeModel);
//        notifyDataSetChanged();
//    }
    public void addList(List<HomeModel> homeModelList){
        list = homeModelList;
        notifyDataSetChanged();
    }

    public HomeAdapter(Listen listen) {
        this.listen = listen;
    }

    public HomeModel getModelToId(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }











    class HomeViewHolder extends RecyclerView.ViewHolder {


        public HomeViewHolder(@NonNull ItemListhomeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;

        }

        public void onBind(HomeModel homeModel) {
            binding.nameItem.setText(homeModel.getTitle());
            binding.numberItem.setText(homeModel.getDescription());

        binding.getRoot().setOnClickListener(v ->
            listen.setDataForForm(homeModel, getAdapterPosition()));


            binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(binding.getRoot().getContext());
                    alert.setMessage("Are you sure");
                    alert.setCancelable(true);

                    alert.setPositiveButton(
                            "yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    App.fillDataBase.fillDao().delete(list.get(getAdapterPosition()));
                                    notifyItemChanged(getAdapterPosition());
                                }});

                    alert.setNegativeButton(
                            "no",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }});
                    alert.show();
                    return true;
                };
            });
        }
    }
}
