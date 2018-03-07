package com.example.mudassirkhan.mercaritest.ui.ErrorPrompt.snackbar;

import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.mudassirkhan.mercaritest.R;
import com.example.mudassirkhan.mercaritest.ui.ErrorPrompt.IPrompt;
import com.example.mudassirkhan.mercaritest.ui.ErrorPrompt.PromptParams;

/**
 * Created by Mudassir Khan on 2/23/2018.
 */

public class SnackbarPrompt implements IPrompt {

    @Override
    public void showNetworkError(final PromptParams params) {
        final View rootView = ((SnackbarParams)params).rootView;
        if(rootView != null) {
            final Snackbar snackbar = Snackbar.make(((SnackbarParams) params).rootView
                    , rootView.getContext().getString(R.string.network_fail_message), ((SnackbarParams) params).duration);

            snackbar.setAction(rootView.getContext().getString(R.string.settings), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                    rootView.getContext().startActivity(new Intent(Settings.ACTION_SETTINGS));
                    // ((SnackbarParams) params).listener.onClick(v);
                }
            });
            snackbar.setActionTextColor(Color.parseColor(rootView.getContext().getString(R.string.snackbar_setting_color)));



            snackbar.show();
        }
    }

    @Override
    public void showError(PromptParams params) {
        show(params);
    }

    @Override
    public void showSuccess(PromptParams params) {
        show(params);
    }

    @Override
    public void showInfo(PromptParams params) {
        show(params);
    }

    private void show(PromptParams params){
        SnackbarParams snackbarPromptParams = ((SnackbarParams)params);

        final Snackbar snackbar = Snackbar.make(((SnackbarParams) params).rootView
                , snackbarPromptParams.message, ((SnackbarParams) params).duration);

        snackbar.show();
    }

}
