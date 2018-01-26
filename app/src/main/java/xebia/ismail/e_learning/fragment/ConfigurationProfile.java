package xebia.ismail.e_learning.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import xebia.ismail.e_learning.Login.LoginActivity;
import xebia.ismail.e_learning.R;

/**
 * Created by Admin on 3/13/2017.
 */
public class ConfigurationProfile extends Fragment {

    private Button btnChangeEmail, btnChangePassword, btnSendResetEmail, btnRemoveUser,
            changeEmail, changePassword, sendEmail, remove, signOut;

    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        Toolbar toolbar = getView().findViewById(R.id.toolbar);
//        toolbar.setTitle(getString(R.string.app_name));
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                    getActivity().finish();
                }
            }
        };

        //btnChangeEmail = view.findViewById(R.id.change_email_button);
        //btnChangePassword = view. findViewById(R.id.change_password_button);
       // btnSendResetEmail = getView().findViewById(R.id.sending_pass_reset_button);
       // btnRemoveUser = getView().findViewById(R.id.remove_user_button);
        changeEmail = view.findViewById(R.id.changeEmail);
        //changePassword = view.findViewById(R.id.changePass);
        sendEmail = view.findViewById(R.id.send);
       // remove = getView().findViewById(R.id.remove);
        signOut = view.findViewById(R.id.sign_out);

        //oldEmail = view.findViewById(R.id.old_email);
        //newEmail = view.findViewById(R.id.new_email);
        //password = view.findViewById(R.id.password);
        //newPassword = view.findViewById(R.id.newPassword);

        //oldEmail.setVisibility(View.GONE);
        //newEmail.setVisibility(View.GONE);
        //password.setVisibility(View.GONE);
        //newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        //changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);
        //remove.setVisibility(View.GONE);

        progressBar = view. findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }




//        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                oldEmail.setVisibility(View.GONE);
//                newEmail.setVisibility(View.VISIBLE);
//                password.setVisibility(View.GONE);
//                newPassword.setVisibility(View.GONE);
//                changeEmail.setVisibility(View.VISIBLE);
//                changePassword.setVisibility(View.GONE);
//                sendEmail.setVisibility(View.GONE);
//                remove.setVisibility(View.GONE);
//            }
//        });

//        changeEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                if (user != null && !newEmail.getText().toString().trim().equals("")) {
//                    user.updateEmail(newEmail.getText().toString().trim())
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getActivity(), "Cuenta de correo actualizada. Por favor ingresa con tu nueva cuenta!", Toast.LENGTH_LONG).show();
//                                        signOut();
//                                        progressBar.setVisibility(View.GONE);
//                                    } else {
//                                        Toast.makeText(getActivity(), "Actualización de correo fallida!", Toast.LENGTH_LONG).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//                                }
//                            });
//                } else if (newEmail.getText().toString().trim().equals("")) {
//                    newEmail.setError("Enter email");
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//        });

//        btnChangePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                oldEmail.setVisibility(View.GONE);
//                newEmail.setVisibility(View.GONE);
//                password.setVisibility(View.GONE);
//                newPassword.setVisibility(View.VISIBLE);
//                changeEmail.setVisibility(View.GONE);
//                changePassword.setVisibility(View.VISIBLE);
//                sendEmail.setVisibility(View.GONE);
//                remove.setVisibility(View.GONE);
//            }
//        });

//        changePassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                if (user != null && !newPassword.getText().toString().trim().equals("")) {
//                    if (newPassword.getText().toString().trim().length() < 6) {
//                        newPassword.setError("Tu contraseña es debil, ingresa almenos 6 caracteres!");
//                        progressBar.setVisibility(View.GONE);
//                    } else {
//                        user.updatePassword(newPassword.getText().toString().trim())
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        if (task.isSuccessful()) {
//                                            Toast.makeText(getActivity(), "Contraseña actualizada. Por favor ingresa con tu contraseña nueva!", Toast.LENGTH_SHORT).show();
//                                            signOut();
//                                            progressBar.setVisibility(View.GONE);
//                                        } else {
//                                            Toast.makeText(getActivity(), "Actualización de contraseña fallida!", Toast.LENGTH_SHORT).show();
//                                            progressBar.setVisibility(View.GONE);
//                                        }
//                                    }
//                                });
//                    }
//                } else if (newPassword.getText().toString().trim().equals("")) {
//                    newPassword.setError("Enter password");
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//        });

//        btnSendResetEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                oldEmail.setVisibility(View.VISIBLE);
//                newEmail.setVisibility(View.GONE);
//                password.setVisibility(View.GONE);
//                newPassword.setVisibility(View.GONE);
//                changeEmail.setVisibility(View.GONE);
//                changePassword.setVisibility(View.GONE);
//                sendEmail.setVisibility(View.VISIBLE);
//                remove.setVisibility(View.GONE);
//            }
//        });

//        sendEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                if (!oldEmail.getText().toString().trim().equals("")) {
//                    auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getActivity(), "El Correo de reseteo de contraseña se envio, revisa tu correo!", Toast.LENGTH_SHORT).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    } else {
//                                        Toast.makeText(getActivity(), "El Correo de reseteo de contraseña no pudo ser enviado", Toast.LENGTH_SHORT).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//                                }
//                            });
//                } else {
//                    oldEmail.setError("Ingresa tu Email");
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//        });

//        btnRemoveUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
//                if (user != null) {
//                    user.delete()
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(getActivity(), "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(getActivity(), SignupActivity.class));
//                                        getActivity().finish();
//                                        progressBar.setVisibility(View.GONE);
//                                    } else {
//                                        Toast.makeText(getActivity(), "Failed to delete your account!", Toast.LENGTH_SHORT).show();
//                                        progressBar.setVisibility(View.GONE);
//                                    }
//                                }
//                            });
//                }
//            }
//        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        return view;
    }

    //sign out method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}

