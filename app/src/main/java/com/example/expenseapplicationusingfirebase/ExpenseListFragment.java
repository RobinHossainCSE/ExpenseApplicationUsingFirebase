package com.example.expenseapplicationusingfirebase;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.expenseapplicationusingfirebase.databinding.FragmentExpenseListBinding;
import com.example.expenseapplicationusingfirebase.models.Expense;
import com.example.expenseapplicationusingfirebase.viewmodels.ExpenseViewModel;
import com.example.expenseapplicationusingfirebase.viewmodels.LoginViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;


public class ExpenseListFragment extends Fragment {
    private FragmentExpenseListBinding binding;
    private LoginViewModel loginViewModel;
    private ExpenseViewModel expenseViewModel;


    public ExpenseListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExpenseListBinding.inflate(inflater);

        loginViewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        expenseViewModel = new ViewModelProvider(requireActivity()).get(ExpenseViewModel.class);

        loginViewModel.getStateLiveData().observe(getViewLifecycleOwner(),authState -> {
            if(authState == LoginViewModel.AuthState.UNAUTHENTICATED){
                Navigation.findNavController(container).navigate(R.id.login_action);
            }
        });

        final BottomSheetBehavior<CardView> behavior = BottomSheetBehavior.from(binding.bottomSheetCardView);

        binding.floatingActionButton.setOnClickListener(view -> {
           if(behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {  /*check condition if bottom sheet is collapse then expand*/

               behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

           }
        });

        binding.saveBtn.setOnClickListener(view -> {

            final String title = binding.titleInputET.getText().toString();
            final double amount = Double.parseDouble(binding.amountInputET.getText().toString());

            final Expense expense = new Expense(
                    null, title, amount, System.currentTimeMillis(), null
            );
            expenseViewModel.addExpense(expense);

            binding.titleInputET.setText(""); //this two lines for empty EditText while click save button
            binding.amountInputET.setText("");

            if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {  /*check condition if bottom sheet is expand then collapse*/

                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

            }

        });
        return binding.getRoot();
    }
}