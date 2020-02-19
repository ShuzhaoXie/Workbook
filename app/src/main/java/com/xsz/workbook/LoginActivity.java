package com.xsz.workbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xsz.workbook.data.UserDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameEdit, passwordEdit;
    private Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        usernameEdit = findViewById(R.id.username);
        passwordEdit = findViewById(R.id.password);
        signInButton = findViewById(R.id.signIn);
        signUpButton = findViewById(R.id.signUp);
        signInButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn:
                confirmInformation();
                break;
            case R.id.signUp:
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private boolean confirmInformation() {
        String username = usernameEdit.getText().toString();
        if (username.length() == 0) {
            Toast.makeText(LoginActivity.this, "用户名不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        String password = passwordEdit.getText().toString();
        if (password.length() == 0) {
            Toast.makeText(LoginActivity.this, "密码不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        UserDatabase userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user").build();
        String testpwd = userDatabase.userDao().findUserPassword(username);
        if (testpwd.equals(password)) {
            MainActivity.actionStart(LoginActivity.this, username);
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
