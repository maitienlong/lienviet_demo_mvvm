package com.example.lienviet_demo_mvvm.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lienviet_demo_mvvm.R;
import com.example.lienviet_demo_mvvm.databinding.ActivityMainBinding;
import com.example.lienviet_demo_mvvm.model.CheckCallbacks;
import com.example.lienviet_demo_mvvm.viewmodels.ATMViewModel;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  loadLocate();
        initDataBinding();
    }

    public void initDataBinding() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        CustomViews customViews = new CustomViews(this);

//        binding.btnChangeLang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "THIS", Toast.LENGTH_SHORT).show();
//                changeLanguages();
//            }
//        });
    }


    public void changeLanguages() {
        multipeLanguages();
    }

    private void multipeLanguages() {
        final String[] listItems = {"Việt Nam", "English", "日本"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn ngôn ngữ");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    setLocate("vi");
                    recreate();
                } else if (i == 1) {

                    setLocate("en");
                    recreate();

                } else if (i == 2) {
                    setLocate("ja");
                    recreate();

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
        this.getResources().updateConfiguration(configuration, this.getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = this.getSharedPreferences("Setting", this.MODE_PRIVATE).edit();
        editor.putString("My_lang", lang);
        editor.apply();
    }

    public void loadLocate() {
        SharedPreferences preferences = this.getSharedPreferences("Setting", this.MODE_PRIVATE);
        String language = preferences.getString("My_lang", "");
        setLocate(language);
    }

}