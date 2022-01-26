package com.example.expenseapplicationusingfirebase.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.expenseapplicationusingfirebase.models.Expense;
import com.example.expenseapplicationusingfirebase.repos.ExpenseRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class ExpenseViewModel extends ViewModel {
    private final ExpenseRepository repository = new ExpenseRepository();

    public void addExpense(Expense expense){
        repository.insertNewExpense(expense)
                .addOnSuccessListener(unused -> {

        }).addOnFailureListener(e -> {

        });
    }
}
