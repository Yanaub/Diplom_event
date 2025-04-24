package com.example.myapplication.ui.transform;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentTransformBinding;
import com.example.myapplication.databinding.ItemTransformBinding;
import com.example.myapplication.ui.eventfun.EventHelpActivity;
import com.example.myapplication.ui.eventfun.EventOrgActivity;

import java.util.Arrays;
import java.util.List;


public class TransformFragment extends Fragment {

    public static TransformFragment newInstance() {
        return new TransformFragment();
    }
    private FragmentTransformBinding binding;

    private TransformViewModel tViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tViewModel =new ViewModelProvider(requireActivity()).get(TransformViewModel.class);

        binding = FragmentTransformBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        RecyclerView recyclerView = binding.recyclerviewTransform;





           ListAdapter<String, TransformViewHolder> adapter = new TransformAdapter(item -> {


                    Intent intent = new Intent(getContext(), EventOrgActivity.class);
                    Toast.makeText(getContext(), item.toString(), Toast.LENGTH_SHORT).show();

                    startActivity(intent);




         });

            recyclerView.setAdapter(adapter);
            tViewModel.getTexts().observe(getViewLifecycleOwner(), adapter::submitList);







        // Set up buttons
        binding.button1.setOnClickListener(v -> {
            // Handle button 1 click
        });

        binding.button2.setOnClickListener(v -> {
            // Handle button 2 click
        });

        binding.button3.setOnClickListener(v -> {
            // Handle button 3 click
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



    private static class TransformAdapter extends ListAdapter<String, TransformViewHolder> {


        private OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick(String item);
        }

        private final List<Integer> drawables = Arrays.asList(
                R.drawable.avatar_1,
                R.drawable.avatar_2,
                R.drawable.avatar_3,
                R.drawable.avatar_4,
                R.drawable.avatar_5,
                R.drawable.avatar_6,
                R.drawable.avatar_7,
                R.drawable.avatar_8,
                R.drawable.avatar_9,
                R.drawable.avatar_10,
                R.drawable.avatar_11,
                R.drawable.avatar_12,
                R.drawable.avatar_13,
                R.drawable.avatar_14,
                R.drawable.avatar_15,
                R.drawable.avatar_16);


        protected TransformAdapter(OnItemClickListener listener) {
            super(new DiffUtil.ItemCallback<String>() {
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            });
            this.listener = listener;
        }

        @NonNull
        @Override
        public TransformViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemTransformBinding binding = ItemTransformBinding.inflate(LayoutInflater.from(parent.getContext()));
            return new TransformViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull TransformViewHolder holder, int position) {
            String item = getItem(position);
            holder.textView.setText(item);
            holder.imageView.setImageDrawable(
                    ResourcesCompat.getDrawable(holder.imageView.getResources(),
                            drawables.get(position),
                            null));
            holder.itemView.setOnClickListener(v -> listener.onItemClick(item)); // Обработка нажатия
        }
    }

    private static class TransformViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textView;

        public TransformViewHolder(ItemTransformBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageViewItemTransform;
            textView = binding.textViewItemTransform;
        }
    }
    }