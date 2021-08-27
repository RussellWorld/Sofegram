package com.russellworld.sofegram.utilits

import android.text.Editable
import android.text.TextWatcher

/* Модификация класса TextWatcher */

class AppTextWatcher(val onSuccess: (CharSequence?) -> Unit): TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        onSuccess(s)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

    override fun afterTextChanged(s: Editable?) { }
}