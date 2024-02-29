package com.example.wordle

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val WordRandomizer = FourLetterWordList()
        val allLetters = listOf(
            arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            arrayOf("DEL", "Z", "X", "C", "V", "B", "N", "M", "ENTER")
        )

        val textViewLayout = findViewById<TableLayout>(R.id.textViewLayout)
        val keyboardLayout = findViewById<TableLayout>(R.id.keyboardLayout)

//       create text view rows
        for (id in 0 until 3){
            val textRow = createTextViewRow(id)
            textViewLayout.addView(textRow)
        }


        // create keyboard rows
        allLetters.forEach { rowLetters ->
            val keyboardRow = createKeyboardRow(rowLetters)
            keyboardLayout.addView(keyboardRow)
        }
    }

    private fun createTextViewRow(id: Int): TableRow{
        val rowHeight = resources.getDimensionPixelSize(R.dimen.textviewRow_height)
        val textViewHeight = resources.getDimensionPixelSize(R.dimen.textviewRow_height)
        val textViewMargin = resources.getDimensionPixelSize(R.dimen.textview_margin)

        // create row of text
        val row = TableRow(this).apply {
            layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,rowHeight)
            setId(id)
        }
        // for each row, create textView to display letter typed by user
        repeat(4){
            val textView = TextView(this).apply {
                layoutParams = TableRow.LayoutParams(textViewHeight,textViewHeight).apply {
                    topMargin = textViewMargin
                    bottomMargin = textViewMargin
                    marginEnd = textViewMargin
                    marginStart = textViewMargin
                }
                background = ContextCompat.getDrawable(context, R.drawable.border)
                gravity = Gravity.CENTER
            }
            // add the textView to the row
            row.addView(textView)
        }
        return row
    }

    private fun createKeyboardRow(letters:Array<String>): TableRow{
        val keyboardRow = TableRow(this).apply {
            layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,TableLayout.LayoutParams.WRAP_CONTENT)
        }
        letters.forEach { letter ->
            val button = createKeyboardButton(letter)
            keyboardRow.addView(button)
        }
        return keyboardRow
    }

    private fun createKeyboardButton(letter:String): Button{
        val keyboardButton = Button(this).apply {
            text = letter
            textSize = 19f
            isAllCaps = true
            background = ContextCompat.getDrawable(context, R.drawable.keyboard_button)
            setTypeface(null,Typeface.BOLD)
            val margin = resources.getDimensionPixelSize(R.dimen.keyboard_button_margin)
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT).apply {
                if (letter.length == 3){
                    weight = 1.7f
                }else if (letter.length > 3){
                    weight = 2.4f
                }else{
                    weight = 1f
                }
                marginStart = margin
                marginEnd = margin
                topMargin = margin
                bottomMargin = margin
            }
        }
        keyboardButton.setOnClickListener{
            Log.d("KeyboardButton", "Button clicked: ${keyboardButton.text}")
        }
        return keyboardButton
    }


    private fun onKeyboardButtonClick(letter: String) {
        // Handle the button click event
        // Update TextViews or perform other actions
    }
}