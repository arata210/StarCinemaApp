package moe.arata210.starcinema.ui.VIP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import moe.arata210.starcinema.databinding.FragmentVipBinding;

public class VIPFragment extends Fragment {

    private FragmentVipBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VIPViewModel VIPViewModel =
                new ViewModelProvider(this).get(VIPViewModel.class);

        binding = FragmentVipBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVip;
        VIPViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}