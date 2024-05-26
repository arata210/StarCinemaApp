package moe.arata210.starcinema.ui.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import moe.arata210.starcinema.R;
import moe.arata210.starcinema.ui.mypage.LoginActivity;

public class MypageFragment extends Fragment {

    private static final int LOGIN_REQUEST_CODE = 1;
    private MypageViewModel mypageViewModel;
    private TextView usernameText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mypageViewModel = new ViewModelProvider(this).get(MypageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        // 初始化视图组件
        ImageView profileImage = root.findViewById(R.id.image_profile);
        usernameText = root.findViewById(R.id.user_name);

        LinearLayout couponButton = root.findViewById(R.id.coupons);
        LinearLayout ordersButton = root.findViewById(R.id.orders);
        LinearLayout membershipButton = root.findViewById(R.id.membership_card);

        TextView userAgreementText = root.findViewById(R.id.user_agreement);
        TextView privacyPolicyText = root.findViewById(R.id.privacy_policy);
        TextView settingsText = root.findViewById(R.id.settings);

        // 设置用户名
        mypageViewModel.getUsername().observe(getViewLifecycleOwner(), usernameText::setText);

        // 设置点击事件
        profileImage.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivityForResult(intent, LOGIN_REQUEST_CODE);
        });

        couponButton.setOnClickListener(v -> {
            // 跳转到优惠券页面的逻辑
        });

        ordersButton.setOnClickListener(v -> {
            // 跳转到订单页面的逻辑
        });

        membershipButton.setOnClickListener(v -> {
            // 跳转到会员卡页面的逻辑
        });

        userAgreementText.setOnClickListener(v -> {
            // 跳转到用户协议页面的逻辑
        });

        privacyPolicyText.setOnClickListener(v -> {
            // 跳转到隐私政策页面的逻辑
        });

        settingsText.setOnClickListener(v -> {
            // 跳转到设置页面的逻辑
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String username = data.getStringExtra("username");
                if (username != null) {
                    usernameText.setText(username);
                    mypageViewModel.setUsername(username);
                }
            }
        }
    }
}
