package com.Kevin.playzoom.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Kevin.playzoom.R;
import com.Kevin.playzoom.databinding.ItemShowBinding;
import com.Kevin.playzoom.model.Shows;
import com.bumptech.glide.Glide;

import java.util.List;

public class RVShowAdapter extends RecyclerView.Adapter<RVShowAdapter.ShowViewHolder> {

    private List<Shows> shows;
    private FavoriteClick favoriteClick;

    public RVShowAdapter(List<Shows> shows, FavoriteClick favoriteClick){
        this.shows = shows;
        this.favoriteClick = favoriteClick;
    }


    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemShowBinding binding = ItemShowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ShowViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        holder.bind(shows.get(position));
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

    public  class ShowViewHolder extends RecyclerView.ViewHolder{

        private  ItemShowBinding binding;

        public ShowViewHolder(@NonNull ItemShowBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Shows show) {
            binding.txtName.setText(show.getName());
            binding.btnFavorite.setOnClickListener(view -> {
                favoriteClick.onclick(show);
            });
            Glide.with(itemView.getContext()).load(show.getImgUrl()).into(binding.imgShow);
        }
    }

    public interface FavoriteClick{
        public void onclick(Shows show);
    }
}
