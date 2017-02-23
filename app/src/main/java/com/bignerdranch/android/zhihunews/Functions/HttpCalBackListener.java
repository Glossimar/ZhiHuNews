package com.bignerdranch.android.zhihunews.Functions;

/**
 * Created by LENOVO on 2017/1/16.
 */

public interface HttpCalBackListener {
    void onFnish(String responce);
    void onError(Exception e);
}
