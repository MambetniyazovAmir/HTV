package com.app.basemodule.extensions

import java.security.MessageDigest
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.regex.Pattern

/**
 * there are " "(space) and "-" also included
 */
fun String.isAllLettersLatin(): Boolean = this.matches(Regex("[a-zA-Z-]+"))

fun String.isAllDigits(): Boolean = this.matches(Regex("[0-9]+"))

fun String.isAllLetters(): Boolean = this.toCharArray().all { it.isLetter() }

/* fast decision, could made better, but had no time */
fun String.isDate(pattern: String): Boolean {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    dateFormat.isLenient = false
    try {
        dateFormat.parse(this.trim())
    } catch (pe: ParseException) {
        return false
    }

    return true
}

fun String.noPlus() = replace("+", "")

fun String.getInnerPosition(targetText: String): Pair<Int, Int> {
    require(contains(targetText))
    val start = this.indexOf(targetText)
    return Pair(start, start + targetText.length)
}

fun String.find(pattern: String): String? {
    val intsOnly = Pattern.compile(pattern)
    val makeMatch = intsOnly.matcher(this)
    makeMatch.find()
    return try {
        makeMatch.group()
    } catch (ex: IllegalStateException) {
        null
    }
}

fun String?.doIfNotBlank(action: (String) -> Unit) {
    if (this?.isNotBlank() == true) action(this)
}

fun String.grouping(countEvery: Int = 4, symbol: Char = ' '): String {
    var result = ""
    for (i in 1..Math.ceil(this.length.toDouble() / countEvery).toInt()) {
        result += this.substring(
            (i - 1) * countEvery,
            if (i * countEvery > this.length) this.length else i * countEvery
        )
        if (Math.ceil(this.length.toDouble() / countEvery).toInt() != i) {
            result += symbol
        }
    }
    return result
}

fun String.removeIfMatch(prefix: String, suffix: String): String {
    var cursor = 0
    var result = this
    while (result.indexOf(prefix, cursor, false) != -1){
        val start = result.indexOf(prefix, cursor, false)
        val end = result.indexOf(suffix, start, false) + suffix.length
        result = result.removeRange(start, end)
        cursor = start
    }
    return result
}

fun ArrayList<String>.mergeInOneString(symbol: Char = '\n'): String {
    var result: String = ""
    this.forEach {
        result += it
    }
    return result
}

fun String.sha512(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

fun String.sha256(): String {
    val bytes = this.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)
    return digest.fold("") { str, it -> str + "%02x".format(it) }
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}