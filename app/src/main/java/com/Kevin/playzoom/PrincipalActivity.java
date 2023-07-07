package com.Kevin.playzoom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.Kevin.playzoom.databinding.ActivityPrincipalBinding;
import com.google.android.material.snackbar.Snackbar;

public class PrincipalActivity extends AppCompatActivity {
    private ActivityPrincipalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabAddMovie.setOnClickListener(view -> {
            Snackbar.make(binding.getRoot(), "Add a movie", Snackbar.LENGTH_SHORT).show();
        });
        setSupportActionBar(binding.tbPlayzoom);
    }
}