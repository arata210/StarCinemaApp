package moe.arata210.starcinema.ui.VIP;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import moe.arata210.starcinema.databinding.FragmentVipBinding;

public class VIPFragment extends Fragment {

    private FragmentVipBinding binding;
    private final Handler handler = new Handler();
    private Runnable updateQRCode;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VIPViewModel vipViewModel =
                new ViewModelProvider(this).get(VIPViewModel.class);

        binding = FragmentVipBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 初始化二维码更新任务
        initQRCodeUpdater();

        // 设置VIP卡片点击事件，显示弹窗
        binding.vipCard.setOnClickListener(v -> showVIPInfoDialog());

        // 设置二维码图片的点击事件，手动刷新二维码
        binding.qrCodeImage.setOnClickListener(v -> refreshQRCode());

        return root;
    }

    private void initQRCodeUpdater() {
        updateQRCode = new Runnable() {
            @Override
            public void run() {
                refreshQRCode();
                handler.postDelayed(this, 30000);  // 30秒后再次更新
            }
        };
        handler.post(updateQRCode);  // 首次调用以生成和更新二维码
    }

    private void refreshQRCode() {
        try {
            String qrCodeData = System.currentTimeMillis() + "780001123456";
            Bitmap qrCodeBitmap = generateQRCode(qrCodeData);
            binding.qrCodeImage.setImageBitmap(qrCodeBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private Bitmap generateQRCode(String data) throws WriterException {
        BitMatrix result;
        try {
            result = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 200, 200, null);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, 200, 0, 0, w, h);
        return bitmap;
    }

    private void showVIPInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("会员卡协议")
                .setMessage("【限时特惠】\n仅需18元即可办理权益季卡(卡内无余额)\n【特惠观影】\n会员购票享受全网最优价，消费款项需另行支付\n【卖品特惠】\n会员卖品消费可享单品8折优惠\n【注意事项】\n1.本卡可在影院线上及现场消费时使用；\n2.本卡为权益卡，卡内无余额，不可储值；\n3.本卡全时段可用，办卡之日起三个月有效；\n4.凭本卡购票可享受会员优惠价，票价以实际购票时的结算页面价格为准；\n5.会员线上购票需支付1元平台服务费，现场购票无需支付；\n6.本卡一经售出，不可退换。")
                .setPositiveButton("关闭", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(updateQRCode);  // 移除所有的回调和消息
        binding = null;
    }
}
