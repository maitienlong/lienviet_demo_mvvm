package com.example.lienviet_demo_mvvm.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.databinding.DataBindingUtil;

import com.example.lienviet_demo_mvvm.R;
import com.example.lienviet_demo_mvvm.databinding.TransferBinding;
import com.example.lienviet_demo_mvvm.viewmodels.ATMViewModel;

import java.util.Locale;

public class CustomViews extends RelativeLayout {
    private Context context;

    public CustomViews(Context context) {
        super(context);
        this.context = context;
        loadLocate();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.transfer, this, true);
        TransferBinding transferBinding = DataBindingUtil.setContentView((Activity) context, R.layout.transfer);
        transferBinding.setAmtTranfer(new ATMViewModel((Activity) context, transferBinding));
        transferBinding.btnlang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLanguages();
            }
        });


    }

    public CustomViews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void changeLanguages() {
        multipeLanguages();
    }

    private void multipeLanguages() {
        final String[] listItems = {"Việt Nam", "English", "日本"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Chọn ngôn ngữ");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocate("vi");
                    ((Activity) context).recreate();

                } else if (i == 1) {

                    setLocate("en");
                    ((Activity) context).recreate();
                } else if (i == 2) {
                    setLocate("ja");
                    ((Activity) context).recreate();
                }
                dialogInterface.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setLocate(String lang) {
        Log.i("locale", lang);
        Locale locale = new Locale(lang);
        Log.i("Locate", locale.toString());
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = context.getSharedPreferences("Setting", context.MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }

    public void loadLocate() {
        SharedPreferences preferences = context.getSharedPreferences("Setting", context.MODE_PRIVATE);
        String language = preferences.getString("My_lang", "");
        setLocate(language);
    }


}
