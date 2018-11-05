package com.test.presentation.utils;

import android.view.View;

public class BindingAdapter {

    @android.databinding.BindingAdapter("visibility")
    public static void setVisibility(View view, boolean visibility) {

        if(visibility) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
