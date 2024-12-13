package com.northcoders.calculatorapp.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.northcoders.calculatorapp.BR;

public class DisplayText extends BaseObservable {

    private String displayTextString;


    public DisplayText(String displayTextString) {
        this.displayTextString = displayTextString;
    }

    @Bindable
    public String getDisplayTextString() {
        return displayTextString;
    }

    public void setDisplayTextString(String displayTextString) {
        displayTextString = cleanZeros(displayTextString);
        this.displayTextString = displayTextString;
        notifyPropertyChanged(BR.displayTextString);
    }

    @NonNull
    private static String cleanZeros(String displayTextString) {
        // Remove ".0" if on end of string
        if (displayTextString.endsWith(".0")){
            displayTextString = displayTextString.substring(0, displayTextString.indexOf('.'));
        }
        // Remove trailing zero e.g. 05 or 01234 but not 0 or 0.****
        if (displayTextString.startsWith("0")
                && displayTextString.length() > 1
                && displayTextString.charAt(1) != '.'
                && displayTextString.charAt(1) != '+'
                && displayTextString.charAt(1) != '-'
                && displayTextString.charAt(1) != '*'
                && displayTextString.charAt(1) != '/'
        ){
            displayTextString = displayTextString.substring(1);
        }
        return displayTextString;
    }
}