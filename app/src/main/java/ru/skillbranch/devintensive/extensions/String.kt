package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {

    return if (this.length <= count) {
        this
    } else {

        var ifNotSpace = false
        this.substring(count).forEach {
            if(it != ' ') ifNotSpace = true
        }

        if(ifNotSpace){

        if (this.substring(0, count)[count - 1] == ' ') this.substring(
            0,
            count - 1
        ) + "..." else this.substring(0, count) + "..."

        } else{
            this.trim()
        }

    }



}