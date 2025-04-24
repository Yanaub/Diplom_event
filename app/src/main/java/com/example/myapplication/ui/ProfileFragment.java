package com.example.myapplication.ui;


import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProfileBinding;
import com.example.myapplication.ui.authentication.LoginActivity;
import com.example.myapplication.ui.clases.User;
import com.example.myapplication.ui.database.UserStorage;
import com.example.myapplication.ui.transform.TransformViewModel;
import com.google.android.material.card.MaterialCardView;


public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    private FragmentProfileBinding binding;
    private ProfileViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        mViewModel.getUserRole().observe(getViewLifecycleOwner(), role -> {


            MaterialCardView cardView = binding.cards1;
            MaterialCardView cardView2 = binding.cards2;

            int cardColor1;
            int cardColor2;
            int textColor1;
            int textColor2;
            Toast.makeText(getContext(), "Role: " + role, Toast.LENGTH_SHORT).show();
            if (role) {
                cardColor1 = getResources().getColor(R.color.blue_dark);
                cardColor2 = getResources().getColor(R.color.white);
                textColor1 = getResources().getColor(R.color.white);
                textColor2 = getResources().getColor(R.color.blue_dark2);

            } else {
                cardColor1 = getResources().getColor(R.color.white);
                cardColor2 = getResources().getColor(R.color.blue_dark);
                textColor1 = getResources().getColor(R.color.blue_dark2);
                textColor2 = getResources().getColor(R.color.white);
            }

            cardView.setCardBackgroundColor(cardColor1);
            cardView2.setCardBackgroundColor(cardColor2);

            // Изменяем цвет текста
            binding.cardsTxOrgP.setTextColor(textColor1);
            binding.cardsTxPomP.setTextColor(textColor2);

            // Изменяем цвет иконок
            binding.cardsImOrgP.setColorFilter(textColor1, PorterDuff.Mode.SRC_IN);
            binding.cardsImPomP.setColorFilter(textColor2, PorterDuff.Mode.SRC_IN);

        });

        binding.cards1.setOnClickListener(v -> {
            mViewModel.setUserRole(true);
            Toast.makeText(getContext(), "Выбран организатор", Toast.LENGTH_SHORT).show();
        });

        binding.cards2.setOnClickListener(v -> {
            mViewModel.setUserRole(false);
            Toast.makeText(getContext(), "Выбран помощник", Toast.LENGTH_SHORT).show();
        });
        binding.cards3.setOnClickListener(v -> {
            getActivity().finish();
            Toast.makeText(getContext(), "Выход", Toast.LENGTH_SHORT).show();
        });

        mViewModel.getUserIdd().observe(getViewLifecycleOwner(), id -> {
            UserStorage userStorage = new UserStorage(getContext());

            User user = userStorage.getUserById(id);
            binding.userName.setText(user.getName());
            binding.userEmail.setText(user.getEmail());
            if (id != null) {
                binding.editButton.setOnClickListener(v -> {
                    Intent intent = new Intent(getContext(), ProfileRedActivity.class);
                    intent.putExtra("USER_ID", id);
                    startActivity(intent);
                });
            } else {

                Toast.makeText(getContext(), "ID пользователя не найден", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
    private void updateText() {
        mViewModel.getUserIdd().observe(getViewLifecycleOwner(), id -> {
            UserStorage userStorage = new UserStorage(getContext());

            User user = userStorage.getUserById(id);
            binding.userName.setText(user.getName());
            binding.userEmail.setText(user.getEmail());});
    }
    private void updateCardColors(boolean role) {
        MaterialCardView cardView = binding.cards1;
        MaterialCardView cardView2 = binding.cards2;

        int cardColor1;
        int cardColor2;
        int textColor1;
        int textColor2;

        if (role) {
            cardColor1 = getResources().getColor(R.color.blue_dark);
            cardColor2 = getResources().getColor(R.color.white);
            textColor1 = getResources().getColor(R.color.white);
            textColor2 = getResources().getColor(R.color.blue_dark2);

        } else {
            cardColor1 = getResources().getColor(R.color.white);
            cardColor2 = getResources().getColor(R.color.blue_dark);
            textColor1 = getResources().getColor(R.color.blue_dark2);
            textColor2 = getResources().getColor(R.color.white);
        }

        cardView.setCardBackgroundColor(cardColor1);
        cardView2.setCardBackgroundColor(cardColor2);

        binding.cardsTxOrgP.setTextColor(textColor1);
        binding.cardsTxPomP.setTextColor(textColor2);

        binding.cardsImOrgP.setColorFilter(textColor1, PorterDuff.Mode.SRC_IN);
        binding.cardsImPomP.setColorFilter(textColor2, PorterDuff.Mode.SRC_IN);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}