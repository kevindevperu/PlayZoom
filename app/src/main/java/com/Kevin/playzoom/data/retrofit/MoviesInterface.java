package com.Kevin.playzoom.data.retrofit;


import com.Kevin.playzoom.data.response.ShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MoviesInterface {
    @GET("ba61b658-360e-45c4-a3c6-e9f23be66157")
    Call<ShowResponse> getShows();
}
