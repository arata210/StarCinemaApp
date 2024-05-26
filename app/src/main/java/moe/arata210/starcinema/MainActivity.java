package moe.arata210.starcinema;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private LinearLayout coupons;
    private LinearLayout orders;
    private LinearLayout membershipCard;
    private TextView userAgreement;
    private TextView privacyPolicy;
    private TextView settings;

    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化视图
        coupons = findViewById(R.id.coupons);
        orders = findViewById(R.id.orders);
        membershipCard = findViewById(R.id.membership_card);
        userAgreement = findViewById(R.id.user_agreement);
        privacyPolicy = findViewById(R.id.privacy_policy);
        settings = findViewById(R.id.settings);
        mainLayout = findViewById(R.id.main_layout);

        // 设置点击事件监听器
        coupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理优惠券的点击事件
                Log.d(TAG, "Coupons clicked");
            }
        });

        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理我的订单的点击事件
                Log.d(TAG, "Orders clicked");
            }
        });

        membershipCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理会员卡的点击事件
                Log.d(TAG, "Membership Card clicked");
            }
        });

        userAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理用户协议的点击事件
                Log.d(TAG, "User Agreement clicked");
                // 启动UserAgreementActivity
                startActivity(new Intent(MainActivity.this, UserAgreement.class));
            }
        });

        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理隐私政策的点击事件
                Log.d(TAG, "Privacy Policy clicked");
                // 清空布局
                mainLayout.removeAllViews();
                // 创建 TextView 并设置内容
                TextView textView = new TextView(MainActivity.this);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText("这是我们的隐私政策内容。");
                // 将 TextView 添加到布局中
                mainLayout.addView(textView);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在这里处理设置的点击事件
                Log.d(TAG, "Settings clicked");
            }
        });
    }
}
