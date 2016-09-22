package com.gta.administrator.cniaoshop.http;

import android.content.Context;

import dmax.dialog.SpotsDialog;

/**
 * Created by Administrator on 2016/9/21.
 */
public abstract class SpotsCallback<T> extends BaseCallback<T> {

    private SpotsDialog spotsDialog;

    public SpotsCallback(Context context) {
        spotsDialog = new SpotsDialog(context, "玩命加载中...");
    }

    @Override
    public void onRequestBefore() {
        spotsDialog.show();
    }

    @Override
    public void onFailure() {
        spotsDialog.dismiss();
    }

}
