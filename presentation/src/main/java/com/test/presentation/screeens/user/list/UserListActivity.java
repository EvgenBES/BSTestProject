package com.test.presentation.screeens.user.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.test.com.testproject.R;
import android.test.com.testproject.databinding.ActivityUserListBinding;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;

import com.test.presentation.base.BaseMvvmActivity;

public class UserListActivity extends BaseMvvmActivity<UserListViewModel,
        ActivityUserListBinding, UserListRouter> {

    private AutoCompleteTextView email;
    private EditText password;
    private CheckBox checkActive;


    @Override
    protected UserListViewModel provideViewModel() {
        return ViewModelProviders.of(this).get(UserListViewModel.class);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_user_list;
    }

    @Override
    protected UserListRouter provideRouter() {
        return new UserListRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        checkActive = findViewById(R.id.checkActive);


        //CheckBox
        checkActive.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (!isChecked) {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                password.setSelection(password.length());
            } else {
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                password.setSelection(password.length());
            }
        });


        //Email
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                autoCompleteEmail(editable.toString());
            }
        });

    }

    //AutoCompleteText Email
    private void autoCompleteEmail(String s) {
        email.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, provideViewModel().getListDomain(s)));
    }

    public void setErrorEmail(String errorEmail) {
        email.setError(errorEmail);
    }

    public void setErrorPassword(String errorPassword) {
        password.setError(errorPassword);
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
