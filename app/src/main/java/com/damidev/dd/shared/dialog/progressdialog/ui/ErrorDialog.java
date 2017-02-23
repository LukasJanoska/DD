package com.damidev.dd.shared.dialog.progressdialog.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;


public class ErrorDialog extends DialogFragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";

    private String title;
    private String content;

    public static ErrorDialog newInstance(String text) {
        return newInstance(null, text);
    }

    public static ErrorDialog newInstance(String title, String text) {
        final ErrorDialog dialog = new ErrorDialog();
        Bundle bundle = new Bundle();

        if(TextUtils.isEmpty(title)) {
            bundle.putString(KEY_TITLE, title);
        }

        bundle.putString(KEY_CONTENT, text);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        setRetainInstance(true);
        super.onCreate(bundle);

        title = getArguments().getString(KEY_TITLE, null);
        content = getArguments().getString(KEY_CONTENT);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext())
                .content(content)
                .positiveText("OK");

        if(title != null) {
            builder.title(title);
        }

        return builder.build();
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

}