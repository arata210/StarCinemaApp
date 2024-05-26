package moe.arata210.starcinema.ui.mypage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import moe.arata210.starcinema.R;
import moe.arata210.starcinema.database.UserDao;

public class LoginActivity extends AppCompatActivity {

    private EditText phoneEditText;
    private EditText codeEditText;
    private Button loginButton;
    private UserDao userDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneEditText = findViewById(R.id.editTextPhone);
        codeEditText = findViewById(R.id.editTextCode);
        loginButton = findViewById(R.id.buttonLogin);
        userDao = new UserDao(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = phoneEditText.getText().toString().trim();
                String code = codeEditText.getText().toString().trim();

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
                    Toast.makeText(LoginActivity.this, "请输入手机号和验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (userDao.checkUser(phone, code)) {
                    String username = userDao.getUsername(phone);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("username", username);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                } else {
                    long userId = userDao.addUser(phone, code); // 使用手机号后3位作为用户名，验证码作为默认密码
                    if (userId != -1) {
                        Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        String username = userDao.getUsername(phone);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("username", username);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
