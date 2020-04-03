package ru.skillbranch.devintensive.models

import android.text.TextUtils
import androidx.core.text.isDigitsOnly as isDigitsOnly1

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun aksQuestion() = question.question

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val (phrase, result) = question.checkValid(answer)
        return if(result){
            if (question.answer.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            } else {
                val result = if (status == Status.CRITICAL) {
                    question = Question.NAME
                    ". Давай все по новой"
                } else ""
                status = status.nextStatus()
                "Это неправильный ответ$result\n${question.question}" to status.color
            }
        } else{
            "$phrase\n${question.question}" to status.color
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answer: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion() = PROFESSION
            override fun checkValid(answer: String): Pair<String, Boolean> {
                return if (answer[0].isLowerCase()) "Имя должно начинаться с заглавной буквы" to false else "" to true
            }
        },
        PROFESSION("Назови мою профессию", listOf("сгибальщик", "bender")) {
            override fun nextQuestion() = MATERIAL
            override fun checkValid(answer: String): Pair<String, Boolean> {
                return if (answer[0].isUpperCase()) "Профессия должна начинаться со строчной буквы" to false else "" to true
            }
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion() = BDAY
            override fun checkValid(answer: String): Pair<String, Boolean> {
                return if (answer.any { it.isDigit() }) "Материал не должен содержать цифр" to false else "" to true
            }
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion() = SERIAL
            override fun checkValid(answer: String): Pair<String, Boolean> {
                return if(!TextUtils.isDigitsOnly(answer)) "Год моего рождения должен содержать только цифры" to false else "" to true
            }
        },
        SERIAL("Назови мой серийный номер", listOf("2716057")) {
            override fun nextQuestion() = IDLE
            override fun checkValid(answer: String): Pair<String, Boolean> {
                return if (answer.length > 7) "Серийный номер содержит только цифры, и их 7" to false else "" to true
            }
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion() = IDLE
            override fun checkValid(answer: String): Pair<String, Boolean> {
                return "" to true
            }
        };

        abstract fun nextQuestion(): Question
        abstract fun checkValid(answer: String): Pair<String, Boolean>


    }
}

