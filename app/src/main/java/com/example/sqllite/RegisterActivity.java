package com.example.sqllite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sqllite.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    private EditText edt_email, edt_password, edt_cf_password;
    private Button btnSignup;
    private ProgressBar progressBar;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth;
    private AppDatabase db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name-v2").build();
        executorService = Executors.newSingleThreadExecutor();

        edt_email = findViewById(R.id.edt_email_signup);
        edt_password = findViewById(R.id.edt_password_signup);
        edt_cf_password = findViewById(R.id.edt_cf_password_signup);
        btnSignup = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.processBar_signup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });
    }

    private void addUserToDatabase(FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            executorService.execute(() -> {
                User user = new User(
                        firebaseUser.getUid(),
                        firebaseUser.getEmail(),
                        firebaseUser.getDisplayName()
                );
                db.userDao().insertUser(user);
            });
        } else {
            runOnUiThread(() -> Toast.makeText(RegisterActivity.this, "FirebaseUser is null", Toast.LENGTH_SHORT).show());
        }
    }

    private void onClickSignUp() {
        progressBar.setVisibility(View.VISIBLE);
        String email = edt_email.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String cf_password = edt_cf_password.getText().toString().trim();
        if (!password.equals(cf_password)) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(RegisterActivity.this, getString(R.string.cf_pass_error), Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {
                                addUserToFirebase(email);
                                addUserToDatabase(firebaseUser);
                                // Sign in success, update UI with the signed-in user's information
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            }
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void addUserToFirebase(String email) {
        DatabaseReference myRef = database.getReference(getString(R.string.firebase_email_table));
        DatabaseReference newChild = myRef.push();
        newChild.setValue(email);
        myRef.addChildEventListener(addRoleListener(email));
    }

    private ChildEventListener addRoleListener(String name) {
        return new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue(String.class).equals(name)) {
                    String id = snapshot.getKey();
                    // add role
                    DatabaseReference myRef2 = database.getReference(getString(R.string.firebase_role_table));
                    myRef2.child(id).setValue(getString(R.string.role_user));
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
        builder.setTitle(RegisterActivity.this.getString(R.string.app_name))
                .setMessage(RegisterActivity.this.getString(R.string.no_internet_detect))
                .setPositiveButton(RegisterActivity.this.getString(R.string.close_app), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton(RegisterActivity.this.getString(R.string.wait), null)
                .create();
        AlertDialog alert = builder.create();
        alert.show();
    }
}
