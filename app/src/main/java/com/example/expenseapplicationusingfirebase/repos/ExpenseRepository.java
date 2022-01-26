package com.example.expenseapplicationusingfirebase.repos;

import com.example.expenseapplicationusingfirebase.models.Expense;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ExpenseRepository {
    private final String COLLECTION_EXPENSE = "Expenses";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();  //this object is main database object


    //this method create an empty document which is return an object of DocumentReference and under the "Expenses"

    public Task<Void> insertNewExpense(Expense expense){
        final DocumentReference ref = db.collection(COLLECTION_EXPENSE).document();

        expense.setId(ref.getId());
        return ref.set(expense);
    }
}
