package com.coder.relax.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by TUS on 2018/4/9.
 */

public class InputUtils {

    public static void hidekeybord(View view){
        InputMethodManager inputMethodManager= (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager!=null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

}
