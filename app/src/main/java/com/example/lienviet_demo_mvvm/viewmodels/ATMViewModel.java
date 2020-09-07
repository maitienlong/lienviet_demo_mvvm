package com.example.lienviet_demo_mvvm.viewmodels;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.example.lienviet_demo_mvvm.BR;
import com.example.lienviet_demo_mvvm.R;
import com.example.lienviet_demo_mvvm.databinding.ActivityMainBinding;
import com.example.lienviet_demo_mvvm.databinding.TransferBinding;
import com.example.lienviet_demo_mvvm.model.ATM;
import com.example.lienviet_demo_mvvm.model.CheckCallbacks;
import com.example.lienviet_demo_mvvm.views.MainActivity;

import java.util.Locale;

public class ATMViewModel extends ViewModel {
    private String TAG = "ATMViewModel";
    private ATM atm = new ATM();
    private CheckCallbacks checkCallbacks;
    private Context context;
    private TransferBinding binding;


    public ATMViewModel() {
    }
    public ATMViewModel(Context context, TransferBinding transferBinding) {
        this.context = context;
        this.binding = transferBinding;
    }


    // @Bindable
    public String getNumber() {
        return atm.getNumber();
    }

    public void setNumber(String number) {
        atm.setNumber(number);
    }

    // @Bindable
    public String getForm() {
        return atm.getForm();
    }

    public void setForm(String form) {
        atm.setForm(form);
    }



    public void onSend(){
        if(atm.isValid()){
            Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
        }

    }

    public TextWatcher numberATM() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(TextUtils.isEmpty(editable.toString())){
                    binding.btnSearch.setEnabled(false);
                    binding.btnSearch.setBackgroundResource(R.color.buttonDislabel);
                }else {
                    binding.btnSearch.setEnabled(true);
                    binding.btnSearch.setBackgroundResource(R.color.buttonSearch);
                }
                Log.i(TAG, "edt: " + editable.toString().trim());
                setNumber(editable.toString());
                Toast.makeText(context, editable.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }


}
