package com.example.ryandu.materialdesigndemo;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tlUsername;
    private EditText etUsername;
    private TextInputLayout tlPassword;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        tlUsername = (TextInputLayout) findViewById(R.id.tl_username);
        tlPassword = (TextInputLayout) findViewById(R.id.tl_password);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btLogin = (Button) findViewById(R.id.bt_login);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private boolean validateUserName(String username){
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.length() > 6;
    }

    private void login(){
        String username = tlUsername.getEditText().getText().toString();
        String password = tlPassword.getEditText().getText().toString();
        if (!validateUserName(username)) {
            tlUsername.setErrorEnabled(true);
            tlUsername.setError("请输入正确的邮箱地址");
        }else if (!validatePassword(password)){
            tlPassword.setErrorEnabled(true);
            tlPassword.setError("密码字数过少");
        }else {
            tlUsername.setErrorEnabled(false);
            tlPassword.setErrorEnabled(false);
            Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
        }
    }
}
