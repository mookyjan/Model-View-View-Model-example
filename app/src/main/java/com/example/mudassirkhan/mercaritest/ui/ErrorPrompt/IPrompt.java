package com.example.mudassirkhan.mercaritest.ui.ErrorPrompt;

/**
 * Created by Mudassir Khan on 2/23/2018.
 */

public interface IPrompt {
    void showNetworkError(PromptParams params);

    void showError(PromptParams params);

    void showSuccess(PromptParams params);

    void showInfo(PromptParams params);
}
