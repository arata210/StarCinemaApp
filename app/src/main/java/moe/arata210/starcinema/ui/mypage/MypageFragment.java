package moe.arata210.starcinema.ui.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import moe.arata210.starcinema.R;

public class MypageFragment extends Fragment {

    private MypageViewModel mypageViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mypageViewModel = new ViewModelProvider(this).get(MypageViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mypage, container, false);

        // 初始化视图组件
        TextView usernameText = root.findViewById(R.id.user_name);
        LinearLayout couponButton = root.findViewById(R.id.coupons);
        LinearLayout ordersButton = root.findViewById(R.id.orders);
        LinearLayout membershipButton = root.findViewById(R.id.membership_card);

        TextView userAgreementText = root.findViewById(R.id.user_agreement);
        TextView privacyPolicyText = root.findViewById(R.id.privacy_policy);
        TextView settingsText = root.findViewById(R.id.settings);

        // 设置用户名
        mypageViewModel.getUsername().observe(getViewLifecycleOwner(), usernameText::setText);

        // 设置点击事件
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
}
