package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

const val KEY_STATUS = "STATUS"
const val KEY_QUESTION = "QUESTION"

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var messageEt: EditText
    lateinit var textTxt: TextView
    lateinit var sendBtn: ImageView
    lateinit var benderImage: ImageView

    lateinit var benderObj: Bender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageEt = et_message
        textTxt = tv_text
        sendBtn = iv_send
        benderImage = android_iv

        sendBtn.setOnClickListener(this)

        val status = savedInstanceState?.getString(KEY_STATUS) ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString(KEY_QUESTION) ?: Bender.Question.NAME.name

        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        val(r, g, b) = benderObj.status.color

        textTxt.text = benderObj.aksQuestion()
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

    }

    override fun onRestart() {
        super.onRestart()
        Log.d("M_MainActivity", "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.d("M_MainActivity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("M_MainActivity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("M_MainActivity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("M_MainActivity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("M_MainActivity", "onDestroy")
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.iv_send){
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString())
            textTxt.text = phrase

            messageEt.setText("")

            val(r, g, b) = color

            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(KEY_STATUS, benderObj.status.name)
        outState.putString(KEY_QUESTION, benderObj.question.name)

    }

}
