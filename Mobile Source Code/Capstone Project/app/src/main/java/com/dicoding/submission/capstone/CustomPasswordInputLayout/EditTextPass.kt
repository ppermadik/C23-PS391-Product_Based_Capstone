package com.dicoding.submission.capstone.CustomPasswordInputLayout

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.dicoding.submission.capstone.R
import com.google.android.material.textfield.TextInputEditText

class EditTextPass : TextInputEditText {

    private var isError: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }



    private fun init() {

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val input = p0.toString()
                when (inputType) {
                    PASSWORD -> {
                        isError = if (input.length < 6) {
                            setError(context.getString(R.string.password_length), null)
                            true
                        } else {
                            false
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                val input = p0.toString()
                when (inputType) {
                    PASSWORD -> {
                        isError = if (input.length < 8) {
                            setError(context.getString(R.string.password_length), null)
                            true
                        } else {
                            false
                        }
                    }
                }
            }
        })
    }

    companion object {
        const val PASSWORD = 0x00000081
    }
}
