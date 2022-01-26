package com.example.expenseapplicationusingfirebase.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends ViewModel {

    public enum AuthState{             /*enumerated type like constant*/
        AUTHENTICATED, UNAUTHENTICATED
    }

    private MutableLiveData<AuthState> stateLiveData;
    private MutableLiveData<String> errMsgLiveData;

    private FirebaseAuth auth;
    private FirebaseUser user;

    public LoginViewModel() {          /*constructor*/
        stateLiveData = new MutableLiveData<>();
        errMsgLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser(); //1st time user will be null because there is no user registered
        if(user == null){
            stateLiveData.postValue(AuthState.UNAUTHENTICATED);
        }else {
            stateLiveData.postValue(AuthState.AUTHENTICATED );
        }
    }

    public LiveData<AuthState> getStateLiveData() {
        return stateLiveData;
    }

    public LiveData<String> getErrMsgLiveData() {
        return errMsgLiveData;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void login(String email, String password){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {

            user = authResult.getUser();
           /* so, now change the AuthState*/
            stateLiveData.postValue(AuthState.AUTHENTICATED);

        }).addOnFailureListener(e -> {
            /*any reason, if login fail then through an error message
            post on errMsgLiveData which is observed from LoginFragment */
            errMsgLiveData.postValue(e.getLocalizedMessage());

        });

    }

    public void register(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(authResult -> {

            user = authResult.getUser();
            /* so, now change the AuthState*/
            stateLiveData.postValue(AuthState.AUTHENTICATED);

        }).addOnFailureListener(e -> {
            /*any reason, if login fail then through an error message
            post on errMsgLiveData which is observed from LoginFragment */
            errMsgLiveData.postValue(e.getLocalizedMessage());

        });

    }

}
