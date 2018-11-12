package com.example.android.sunshine.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by vagner on 08/02/2016.
 */
public class LocationEditTextPreference extends EditTextPreference{
    static final private int DEFAULT_MINIMUM_LOCATION_LENGHT = 2;
    private int mMinLenght;
    public LocationEditTextPreference(Context context,AttributeSet attributeSet){
        super(context,attributeSet);
        TypedArray array = context.getTheme().obtainStyledAttributes(
                attributeSet,
                R.styleable.LocationEditTextPreference,
                0,0);
        try {
            mMinLenght = array.getInteger(R.styleable.LocationEditTextPreference_minLength,DEFAULT_MINIMUM_LOCATION_LENGHT);
        } finally {
            array.recycle();
        }
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);

        EditText editText = getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Dialog d = getDialog();
                if (d instanceof AlertDialog){
                    AlertDialog dialog = (AlertDialog)d;
                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    // check if the EditText is empty
                    if (editable.length() < mMinLenght){
                        // Disable Ok Button
                        positiveButton.setEnabled(false);
                    } else {
                        // re-enable the button
                        positiveButton.setEnabled(true);
                    }
                }

            }
        });

    }

}
