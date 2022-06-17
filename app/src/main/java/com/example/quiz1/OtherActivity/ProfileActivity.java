package com.example.quiz1.OtherActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quiz1.HomeActivity;
import com.example.quiz1.MainActivity;
import com.example.quiz1.R;
import com.example.quiz1.data.UserData;
import com.example.quiz1.models.User;

import java.util.Vector;

public class ProfileActivity extends AppCompatActivity {

    EditText edtNewUsername;
    TextView tvUsernameProfile, tvEmailProfile, tvPhoneProfile;
    Button btnEdit, btnDelete, btnLogout, btnSave;
    UserData userData;
    Vector<User> vectUser = UserData.getVectUser();
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("Profile");

        intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");

        edtNewUsername = findViewById(R.id.edtUsername);
        tvUsernameProfile = findViewById(R.id.txtContentUsername);
        tvEmailProfile = findViewById(R.id.txtContentEmail);
        tvPhoneProfile = findViewById(R.id.txtContentPhone);

        tvUsernameProfile.setText(username);
        tvEmailProfile.setText(email);
        tvPhoneProfile.setText(phone);

        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnLogout = findViewById(R.id.btnLogOut);
        btnSave = findViewById(R.id.btnSave);

        btnEdit.setOnClickListener(v -> {
            btnEdit.setVisibility(View.GONE);
            btnSave.setVisibility(View.VISIBLE);

            edtNewUsername.setVisibility(View.VISIBLE);
            edtNewUsername.setText(username);
            tvUsernameProfile.setVisibility(View.GONE);
        });

        btnSave.setOnClickListener(v -> {

            String newUsername = edtNewUsername.getText().toString();

            if (newUsername.length() < 3 || newUsername.length() > 20) {
                Toast.makeText(this, "Min 3 characters", Toast.LENGTH_LONG).show();
            } else if(newUsername.length() > 20) {
                Toast.makeText(this, "Max 20 characters", Toast.LENGTH_LONG).show();
            } else {
                btnEdit.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.GONE);
                edtNewUsername.setVisibility(View.GONE);
                tvUsernameProfile.setText(newUsername);

                userData.getLoggedIn().setUsername(newUsername);
                tvUsernameProfile.setVisibility(View.VISIBLE);
                tvUsernameProfile.setText(newUsername);

                // Code dibawah akan digunakan untuk menginput data hasil edit profile ke database
                // =============================================================================

                userData.changeUsername(this, newUsername);
                Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

                // ==============================================================================
            }

        });


        btnDelete.setOnClickListener(v -> {
            for (User allUser: vectUser) {
                if (allUser.getUsername().equals(UserData.getLoggedIn().getUsername())) {
                    Toast.makeText(getApplicationContext(), "User " + UserData.getLoggedIn().getUsername() + " is Deleted!", Toast.LENGTH_LONG).show();
                    userData.deleteAccount(this);

                    Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);
                    break;
                }
            }
        });

        btnLogout.setOnClickListener(v -> {
            Log.wtf("before logout", UserData.getLoggedIn().getUsername());
            Toast.makeText(getApplicationContext(), "Logout From " + UserData.getLoggedIn().getUsername() + " is Successful!", Toast.LENGTH_LONG).show();
            UserData.setLoggedIn(null);
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home :
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                Log.wtf("test", "Masuk Home");
                break;
            case R.id.profile :
                Log.wtf("test", "Masuk Profile");
                break;
        }

        return super.onOptionsItemSelected(item);
    }



    private Object getActivity() {
        return 3;
    }
}