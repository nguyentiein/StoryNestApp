package com.example.sqllite;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.style.Circle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private LinearLayout layoutSignup;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignIn;
    private LinearLayout layoutForgotPassword;
    private ProgressBar progressBar;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        layoutSignup = findViewById(R.id.layout_signup);
        edtEmail = findViewById(R.id.edt_email_signin);
        edtPassword = findViewById(R.id.edt_password_signin);
        btnSignIn = findViewById(R.id.btn_signin);
        layoutForgotPassword = findViewById(R.id.layout_forgot_password);
        progressBar = findViewById(R.id.processBar_signin);
        progressBar.setIndeterminateDrawable(new Circle());

        layoutSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyBoard();
                onClickSignIn();
            }
        });

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(internetReceiver, filter);
    }
    private void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    private void onClickSignIn() {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        // Kiểm tra email và mật khẩu có hợp lệ không
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, getString(R.string.enter_credentials), Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            getHomeActivity(email);
                        } else {
                            String errorMessage = task.getException() != null ? task.getException().getMessage() : getString(R.string.sign_in_fail);
                            Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



    private void getHomeActivity(String email) {
        DatabaseReference myRef = database.getReference(getString(R.string.firebase_email_table));
        myRef.addChildEventListener(getRoleFromName(email));
    }

    private ChildEventListener getRoleFromId(String id){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(id)){
                    String role = snapshot.getValue(String.class);
                    if (role.equals(getString(R.string.role_admin))){
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, AdminPage.class);
                        startActivity(intent);
                        finishAffinity();
                    }else {
                        progressBar.setVisibility(View.GONE);
                        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    private ChildEventListener getRoleFromName(String name){
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue(String.class).equals(name)){
                    String id = snapshot.getKey();
                    DatabaseReference myRef = database.getReference(getString(R.string.firebase_role_table));
                    myRef.addChildEventListener(getRoleFromId(id));
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    private void displayAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(LoginActivity.this.getString(R.string.app_name))
                .setMessage(LoginActivity.this.getString(R.string.no_internet_detect))
                .setPositiveButton(LoginActivity.this.getString(R.string.close_app), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton(LoginActivity.this.getString(R.string.wait), null)
                .create();
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null){
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network = manager.getActiveNetwork();
            if (network == null){
                return false;
            }
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
            return capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        } else {
            NetworkInfo info = manager.getActiveNetworkInfo();
            return info != null && info.isConnected();
        }
    }

    private final BroadcastReceiver internetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Context applicationContext = context.getApplicationContext();
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
                if (!isNetworkAvailable(context)){
                    displayAlert();
                }
            }
        }
    };
}
