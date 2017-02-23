package com.damidev.dd.shared.dialog.progressdialog.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.damidev.dd.R;


public class ProgressDialog extends DialogFragment {

    Bundle savedInstanceState;

    private static final String KEY_CONTENT = "content";

    private String content;

    public static ProgressDialog newInstance(String text) {
        final ProgressDialog dialog = new ProgressDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CONTENT, text);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        setRetainInstance(true);
        super.onCreate(bundle);

        content = getArguments().getString(KEY_CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setCancelable(false);
        final View view = inflater.inflate(R.layout.dialog_progress, container, false);
        ((TextView) view.findViewById(R.id.tvContent)).setText(content);

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        this.savedInstanceState = savedInstanceState;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

}