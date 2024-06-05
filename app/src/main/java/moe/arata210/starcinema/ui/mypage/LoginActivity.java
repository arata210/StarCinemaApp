package moe.arata210.starcinema.ui.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import moe.arata210.starcinema.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private EditText verificationCodeEditText;
    private Button loginButton;
    private Button getCodeButton; // 新添加的按钮
    private OkHttpClient client;
    private static final String TAG = "LoginActivity";
    private static final int COUNTDOWN_TIME = 10000; // 倒计时时间为10秒
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumberEditText = findViewById(R.id.editTextPhone);
        verificationCodeEditText = findViewById(R.id.editTextCode);
        loginButton = findViewById(R.id.buttonLogin);
        getCodeButton = findViewById(R.id.buttonGetCode); // 初始化新按钮
        client = new OkHttpClient();

        getCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                if (phoneNumber.isEmpty() || phoneNumber.length() != 11) {
                    Toast.makeText(LoginActivity.this, "请输入11位手机号", Toast.LENGTH_SHORT).show();
                } else {
                    getCodeButton.setEnabled(false); // 点击后禁用按钮
                    startCountdown(); // 启动倒计时
                    getPasscode(phoneNumber);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                String verificationCode = verificationCodeEditText.getText().toString();
                performLogin(phoneNumber, verificationCode);
            }
        });
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(COUNTDOWN_TIME, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // 在这里更新按钮文本为倒计时时间
                getCodeButton.setText(String.valueOf(millisUntilFinished / 1000) + "秒后重新获取");
            }

            @Override
            public void onFinish() {
                // 倒计时结束后启用按钮，并设置文本为"获取验证码"
                getCodeButton.setEnabled(true);
                getCodeButton.setText("获取验证码");
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 在Activity销毁时取消倒计时，释放资源
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void getPasscode(String phone) {
        String url = "http://192.168.1.4:5050/api/my/passcode?phone=" + phone;

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Request failed: " + e.getMessage(), e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "请求失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String code = response.body().string();
                    // 将验证码填入验证码编辑框中
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            verificationCodeEditText.setText(code);
                        }
                    });
                } else {
                    Log.e(TAG, "Unexpected response code: " + response.code());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "请求失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void performLogin(String phoneNumber, String verificationCode) {
        String url = "http://192.168.1.4:5050/api/my/check?phone=" + phoneNumber + "&code=" + verificationCode;

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Request failed: " + e.getMessage(), e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "请求失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.equals("1")) {
                            String username = "用户" + phoneNumber.substring(phoneNumber.length() - 4);
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("username", username);
                            setResult(Activity.RESULT_OK, resultIntent);
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "登录失败，请检查验证码", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

