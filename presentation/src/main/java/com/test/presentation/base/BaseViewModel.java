package com.test.presentation.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.test.domain.entity.DomainModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<R extends BaseRouter, T extends DomainModel> extends ViewModel {

    private CompositeDisposable compositeDisposable;

    public ObservableBoolean progressBar = new ObservableBoolean(false);

    protected R router;

    protected abstract void runInject();

    public BaseViewModel() {
        runInject();
    }

    public void addRouter(R router) {
        this.router = router;
    }

    public void removeRouter() {
        router = null;
    }

    public void showProgress() {
        progressBar.set(true);
    }

    public void dismissProgress() {
        progressBar.set(false);
    }

    public abstract void setItem(T item);

    public void showProgressWithDelay() {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public CompositeDisposable getCompositeDisposable() {
        if(compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }

        return compositeDisposable;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(compositeDisposable != null
                && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
