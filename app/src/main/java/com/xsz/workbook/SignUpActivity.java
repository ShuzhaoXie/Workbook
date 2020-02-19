package com.xsz.workbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xsz.workbook.data.User;
import com.xsz.workbook.data.UserDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText email, username, pass1, pass2;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.userEmail);
        username = findViewById(R.id.username_sign_up);
        pass1 = findViewById(R.id.password_sign_up_1);
        pass2 = findViewById(R.id.password_sign_up_2);
        button = findViewById(R.id.confirm_sign_up);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInfo();
            }
        });
    }

    private boolean checkInfo() {
        String eml = email.getText().toString();
        if (!BasicMethods.emailFormat(eml)) {
            Toast.makeText(SignUpActivity.this, "请填写正确邮箱", Toast.LENGTH_SHORT).show();
            return false;
        }
        String usrnm = username.getText().toString();
        if (usrnm.length()==0) {
            Toast.makeText(SignUpActivity.this, "用户名不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        String p1 = pass1.getText().toString();
        if (p1.length()==0) {
            Toast.makeText(SignUpActivity.this, "密码不得为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        String p2 = pass1.getText().toString();
        if (!p1.equals(p2)) {
            Toast.makeText(SignUpActivity.this, "请保证两次填写的密码一致", Toast.LENGTH_SHORT).show();
            return false;
        }
        UserDatabase userdb = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user").build();
        User user = new User();
        int uid = userdb.userDao().totalNumberOfUsers();
        user.setUid(uid+1);
        user.setUserEmail(eml);
        user.setUserName(usrnm);
        user.setUserPassword(p1);
        userdb.userDao().insertAll(user);
        return true;
    }
}
