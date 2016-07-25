package me.bayupaoh.firebaselogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText txtMail;
    EditText txtPassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        decralationWidget();
        setmMauth();
    }

    private void setmMauth() {
        mAuth = FirebaseAuth.getInstance();
    }

    private void decralationWidget() {
        txtMail = (EditText) findViewById(R.id.email);
        txtPassword = (EditText)findViewById(R.id.password);
    }

    public void klikBtnReset(View v){
        Intent intent = new Intent(getApplicationContext(),ForgotPasswordActivity.class);
        startActivity(intent);
    }

    public void klikBtnLogin(View v){
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

    public void klikBtnRegister(View v){
        String email =  txtMail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Daftar Sukses",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Daftar Gagal",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
