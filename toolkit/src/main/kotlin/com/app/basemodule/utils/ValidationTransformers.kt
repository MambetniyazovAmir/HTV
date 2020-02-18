package com.app.basemodule.utils

import android.util.Patterns
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

const val PASS_LENGTH = 8
const val NAME_VALIDATION_LENGTH = 1
const val PHONE_VALIDATION_LENGTH = 6

fun emailValidTransformer() = ObservableTransformer<CharSequence, Boolean> { observable ->
    observable
            .filter { it.isNotEmpty() }
            .map { it.isNotEmpty() && isEmailValid(it) }
            .distinctUntilChanged()
}

fun emailValidOrEmptyTransformer() = ObservableTransformer<CharSequence, Boolean> { observable ->
    observable
            .map { isEmailValid(it) || it.isEmpty() }
            .distinctUntilChanged()
}

fun isEmailValid(it: CharSequence?) = Patterns.EMAIL_ADDRESS.matcher(it).matches()

fun passLengthValidTransformer() = ObservableTransformer<CharSequence, Boolean> { observable ->
    observable
            .filter { it.isNotEmpty() }
            .map { it.length >= PASS_LENGTH }.distinctUntilChanged()
}

fun nameValidTransformer() = ObservableTransformer<CharSequence, Boolean> {
    it.filter { it.isNotEmpty() }
            .map { it.length >= NAME_VALIDATION_LENGTH }
            .distinctUntilChanged()
}

fun phoneValidTransformer() = ObservableTransformer<CharSequence, Boolean> {
    it.filter { it.isNotEmpty() }
            .map { it.length >= PHONE_VALIDATION_LENGTH }
            .distinctUntilChanged()
}

fun baseValidationTransformer(predicate: (CharSequence) -> Boolean) = ObservableTransformer<CharSequence, Boolean> {
    it.map { cs -> predicate(cs) }.distinctUntilChanged()
}

fun Observable<CharSequence>.validate(predicate: (CharSequence) -> Boolean) = compose(baseValidationTransformer { predicate(it) })
fun Observable<CharSequence>.validate(transformer: ObservableTransformer<CharSequence, Boolean>) = compose(transformer)

fun nameValidTransformerEmpty() = ObservableTransformer<CharSequence, Boolean> {
    it.map { it.length >= NAME_VALIDATION_LENGTH || it.isEmpty() }
            .distinctUntilChanged()
}

fun createMaskForNumber(number: String): String {
    val length = number.length
    return when (length) {
        4 -> number + "-("
        8 -> number + ")"
        13, 16 -> {
            val preLastIndex = length - 1
            fun substring(start: Int, end: Int) = number.substring(start, end)
            if (number[preLastIndex] == '-') substring(0, preLastIndex)
            else "${substring(0, preLastIndex)}-${substring(preLastIndex, length)}"
        }
        else -> number
    }
}
