package com.example.revice.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revice.databinding.LayoutOnboardBinding;

import java.util.ArrayList;
import java.util.List;

public class OnBoardAdapter extends RecyclerView.Adapter<OnBoardAdapter.OnBoardViewHolder> {

    private List<OnBoardItem> list;

    public OnBoardAdapter(List<OnBoardItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public OnBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardViewHolder(LayoutOnboardBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardViewHolder holder, int position) {
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class OnBoardViewHolder extends RecyclerView.ViewHolder {

        private LayoutOnboardBinding binding;

        public OnBoardViewHolder(@NonNull LayoutOnboardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(OnBoardItem onBoardItem) {
            binding.titleOnBoard.setText(onBoardItem.getTitle());
            binding.descriptionOnBoard.setText(onBoardItem.getDescription());
            binding.imageOnBoard.setImageResource(onBoardItem.getImage());

        }
    }

}
