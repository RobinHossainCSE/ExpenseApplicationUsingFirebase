package com.example.expenseapplicationusingfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expenseapplicationusingfirebase.databinding.FragmentLoginBinding;
import com.example.expenseapplicationusingfirebase.viewmodels.LoginViewModel;


public class LoginFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;
    private boolean isLogin;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater);
        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        binding.loginBtn.setOnClickListener(view -> {

            isLogin = true;
            authenticate();

        });

        binding.registerBtn.setOnClickListener(view -> {

            isLogin=false;
            authenticate();

        });

        loginViewModel.getStateLiveData().observe(getViewLifecycleOwner(), authState -> {
            if(authState == LoginViewModel.AuthState.AUTHENTICATED){
                Navigation.findNavController(container).navigate(R.id.login_to_expense_list_action);

            }

        });

        loginViewModel.getErrMsgLiveData().observe(getViewLifecycleOwner(), errMsg -> {
            binding.errMsgTv.setText(errMsg);

        });

        return binding.getRoot();
    }

    private void authenticate() {
        final String email = binding.emailInputET.getText().toString();
        final String password = binding.emailInputET.getText().toString();

        if(isLogin){
            loginViewModel.login(email, password);
        }else {
            loginViewModel.register(email, password);
        }
    }
}