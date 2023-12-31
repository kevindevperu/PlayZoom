package com.Kevin.playzoom.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Kevin.playzoom.data.response.ShowResponse;
import com.Kevin.playzoom.data.retrofit.RetrofitHelper;
import com.Kevin.playzoom.databinding.FragmentHomeBinding;
import com.Kevin.playzoom.model.Movie;
import com.Kevin.playzoom.model.Series;
import com.Kevin.playzoom.model.Shows;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(HomeViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RVResumeAdapter adapter  = new RVResumeAdapter(getData());
        binding.rvMoviesResume.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvMoviesResume.setLayoutManager(layoutManager);
        RetrofitHelper.getServices().getShows().enqueue(new Callback<ShowResponse>() {
            @Override
            public void onResponse(Call<ShowResponse> call, Response<ShowResponse> response) {
                if (response.isSuccessful()){
                    assert response.body() != null;
                    showMovies(response.body().getShowsList());
                }
            }

            @Override
            public void onFailure(Call<ShowResponse> call, Throwable t) {

            }
        });
        homeViewModel.listLiveData.observe(requireActivity(), showList ->{
            //Mostrar en recycler
            for (int i = 0 ; i< showList.size(); i++){
                Log.d("Peliculas", showList.get(i).getName());//Solo que te mueste el nombre en la BD
            }
        });
        homeViewModel.getShows();
    }

    private void showMovies(List<Shows> showsList) {
        RVShowAdapter adapter = new RVShowAdapter(showsList, show -> {
            homeViewModel.addShow(show);
        });
        binding.rvShows.setAdapter(adapter);
    }

    private List<Shows> getData(){
        List<Shows> shows = new ArrayList<>();
        shows.add(new Series("Euphoria", "https://mcdaniel.hu/wp-content/uploads/2022/02/Euphoria-HBO-1176x445.png", 2));
        shows.add(new Series("Star Wars: Rebelds", "https://musingsonthem49.files.wordpress.com/2015/09/sw-rebels_tsol_poster.jpg", 3));
        shows.add(new Movie("Avengers", "https://img.ecartelera.com/img/35400/35493_nuevo-banner-con-los-vengadores.jpg", "Infinite Saga"));
        shows.add(new Movie("Barbie", "https://static.euronews.com/articles/stories/07/51/45/54/1440x810_cmsv2_d794baf9-5913-5e00-ab52-461ab3b4b56f-7514554.jpg", ""));
        shows.add(new Series("Black Mirror", "https://www.americatv.com.pe/cinescape/wp-content/uploads/2017/05/189068.jpg", 5));
        shows.add(new Series("The witcher", "https://redanianintelligence.com/wp-content/uploads/2023/06/poster-wide.jpg", 3));
        shows.add(new Movie("Oppenheimer", "https://i.ytimg.com/vi/MnE8L44r9VM/maxresdefault.jpg", ""));
        return shows;
    }
}