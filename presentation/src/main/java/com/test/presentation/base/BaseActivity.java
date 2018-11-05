package com.test.presentation.base;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable;

    public CompositeDisposable getCompositeDisposable() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(compositeDisposable != null
                && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
