package xebia.ismail.e_learning.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import xebia.ismail.e_learning.MainActivity;
import xebia.ismail.e_learning.Classes.User;
import xebia.ismail.e_learning.R;

public class SignupActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword, inputNombre, inputTelefono, inputDni, inputDistrito;
    private Spinner spinner1;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    DatabaseReference databaseReference;
    public String nombre, password, distrito, email;
    public double telefono, dni;
    static String var;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();


        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputNombre = (EditText) findViewById(R.id.nombre);
        inputTelefono = (EditText) findViewById(R.id.telefono);
        inputDni = (EditText) findViewById(R.id.dni);
        //inputDistrito = (EditText) findViewById(R.id.distrito);


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                var = spinner1.getItemAtPosition(position).toString();
//                Toast.makeText(spinner1.getContext(),
//                        "OnItemSelectedListener : " + var,
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //addListenerOnSpinnerItemSelection();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
//
//        btnResetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SignupActivity.this, ResetPasswordActivity.class));
//            }
//        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = inputEmail.getText().toString().trim();
                password = inputPassword.getText().toString().trim();
                nombre = inputNombre.getText().toString().trim();
                telefono = Double.parseDouble(inputTelefono.getText().toString().trim());
                distrito = var;
                dni = Double.parseDouble(inputDni.getText().toString().trim());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Introduzca su dirección de correo electrónico!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Introducir la contraseña!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Contraseña demasiado corta, ingrese un mínimo de 6 caracteres!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                Toast.makeText(SignupActivity.this, "Usuario creado!" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignupActivity.this, "No se pudo crear el usuario...." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });


                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                User user = new User(nombre, distrito, telefono, dni, email, password, String.valueOf(date));
                Log.d("HORAAA", String.valueOf(ServerValue.TIMESTAMP));
                updateDatabase(user);

            }
        });
    }

    private void addListenerOnSpinnerItemSelection() {
        //spinner1 = (Spinner) findViewById(R.id.spinner1);
        //spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }


    public void updateDatabase(User user) {

        databaseReference.child("users").push().setValue(user);
        inputNombre.setText(null);
        inputTelefono.setText(null);
        inputDni.setText(null);
        //inputDistrito.setText(null);
        inputEmail.setText(null);
        inputPassword.setText(null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}