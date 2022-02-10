package com.pune.earnwealth.page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pune.earnwealth.MainActivity;
import com.pune.earnwealth.R;

import static com.pune.earnwealth.helper.Utils.validateEmail;

public class LoginPage extends AppCompatActivity {
    
    EditText edtEmail , edtPassword ;
    View btnLogin ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        findId();
        click();
    }

    private void click()
    {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateEmail(edtEmail.getText().toString().trim()))
                {
                    Toast.makeText(LoginPage.this, "Please Enter Any Valid Email Address.", Toast.LENGTH_SHORT).show();
                }
                else if(edtPassword.getText().toString().trim().length()<6)
                {
                    Toast.makeText(LoginPage.this, "Please Enter Any 6 Length Of Password.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(LoginPage.this, DashboardPage.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void findId()
    {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        edtPassword.setTransformationMethod(new PasswordTransformationMethod());

    }
}