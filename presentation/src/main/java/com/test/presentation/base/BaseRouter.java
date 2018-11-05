package com.test.presentation.base;

import android.test.com.testproject.R;
import android.util.Log;
import android.widget.Toast;

import com.test.domain.entity.Error;

public class BaseRouter<A extends BaseActivity> {

    protected A activity;

    public BaseRouter(A activity) {
        this.activity = activity;
    }

    public void goBack() {
        activity.onBackPressed();
    }


    public void showError(Throwable throwable) {

        if(throwable instanceof Error) {

            Error error = (Error)throwable;
            switch (error.getType()) {
                case INTERNET_IS_NOT_AVAILABLE:{
                    showTostError(R.string.error_internet_not_available);
                    break;
                }
                case SERVER_IS_NOT_AVAILABLE: {
                    showTostError(R.string.error_server_not_available);
                    break;
                }
                case SERVER_ERROR: {
                    showTostError(R.string.error_server);
                    break;
                }
                default: {
                    //снова непредвиденная ошибка
                    //делаем соответствующий отчет
                    Log.e(activity.getClass().getSimpleName(),
                            "забыл указать тип ошибки " + throwable.toString());
                    showTostError(R.string.error);
                }
            }
        } else {
            //отправляет отчет программистам
            Log.e(activity.getClass().getSimpleName(),
                    "ужасная проблема " + throwable.toString());
            showTostError(R.string.error);
        }
    }

    private void showTostError(int messageErrorId) {
        //показываете пользователю нейтральное сообщение об ошибке
        Toast.makeText(activity, messageErrorId, Toast.LENGTH_SHORT)
                .show();
    }

    public A getActivity() {
        return activity;
    }
}
