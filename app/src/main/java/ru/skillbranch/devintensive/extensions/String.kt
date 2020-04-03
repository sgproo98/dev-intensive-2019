package ru.skillbranch.devintensive.extensions

import android.widget.Toast


fun String.truncate(count: Int = 16): String {

    return if (this.length <= count) {
        this
    } else {

        if(this.substring(count).isBlank()){

        if (this.substring(0, count)[count - 1] == ' ') this.substring(
            0,
            count - 1
        ) + "..." else this.substring(0, count) + "..."

        } else{
            this.trim()
        }

        

    }

}

fun String.stripHtml(): String{

    return ""
}