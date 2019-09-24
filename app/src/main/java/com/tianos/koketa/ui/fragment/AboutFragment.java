package com.tianos.koketa.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.tianos.koketa.R;
import com.tianos.koketa.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutFragment extends DialogFragment {

    private static final String TAG = AboutFragment.class.getName();

    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @BindView(R.id.tv_version_name)
    TextView tvVersionName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle(R.string.dummy);
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        return v;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        tvVersionCode.setText("Version code: " + Util.versionCode(getActivity()));
        tvVersionName.setText("Version name: " + Util.versionName(getActivity()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.close)
    public void btnClose() {
        dismiss();
    }
}
