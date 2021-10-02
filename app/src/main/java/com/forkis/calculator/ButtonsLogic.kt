package com.forkis.calculator

import android.widget.HorizontalScrollView
import com.forkis.calculator.MainActivity.Companion.toEnd
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import java.util.*

/**
 * Class, that initialise and work with button logic
 */
class ButtonsLogic(val editText: MaterialTextView, val editScrollView: HorizontalScrollView,
                   val resultText: MaterialTextView, val resultScrollView: HorizontalScrollView) {

    var actionButtonUsed = false
    var secondaryActionsUsed = false

    var mainActions = listOf(Actions.Plus, Actions.Minus, Actions.Multiply, Actions.Divide)
    var secondaryActions = listOf(Actions.Sqrt, Actions.Percent, Actions.Dot, Actions.Lg, Actions.Sqr,
        Actions.Degree, Actions.Factorial, Actions.LeftBracket, Actions.RightBracket, Actions.Opposite,
        Actions.Pi, Actions.Sin, Actions.Cos, Actions.Tg, Actions.Ctg, Actions.Arcsin, Actions.Arccos,
        Actions.Arctg, Actions.Arcctg)

    /**
     * Create logic for buttons with numbers
     * @param button a button that add logic
     * @param number number of button
     */
    fun setNumberLogic(button: FloatingActionButton, number: Int) {
        button.setOnClickListener {
            var text = editText.text.toString()
            if (text == "0")
                text = number.toString()
            else
                text += number.toString()
            editText.text = text
            val res = CalculatorParser.calculate(editText.text.toString())
            text = if((res).toString() == "${res.toInt()}.0"){
                "=${res.toInt()}"
            } else{
                "=${res}"
            }
            resultText.text = text
            toEnd(editScrollView)
            toEnd(resultScrollView)
        }
    }


    /**
     * Create logic for clear button
     * @param button a button that add logic
     */
    fun setClearLogic(button: FloatingActionButton){
        button.setOnClickListener {
            editText.text = "0"
            resultText.text = "=0"
            toEnd(editScrollView)
            toEnd(resultScrollView)
            secondaryActionsUsed = false
            actionButtonUsed = false
        }
    }

    /**
     * Create logic for buttons with numbers
     * @param button a button that add action logic
     * @param action type of action
     */
    fun setActionLogic(button: FloatingActionButton, action: Actions){
        button.setOnClickListener {
            when(action){
                Actions.Plus -> setPlusLogic()
                Actions.Minus -> setMinusLogic()
                Actions.Multiply -> setMultiplyLogic()
                Actions.Divide -> setDivideLogic()
                else -> {}
            }
        }
    }

    /**
     * Create logic for clear button
     * @param button a button that add delete symbol logic
     */
    fun setDeleteLogic(button: FloatingActionButton) {
        button.setOnClickListener {

            if (editText.text.length == 1){
                editText.text = "0"
                resultText.text = "=0"
                return@setOnClickListener
            }
            if (editText.text.isNotEmpty()) {
                editText.text =
                    editText.text.subSequence(0, editText.text.length - 1)
                if (("+-*/^").indexOf(editText.text[editText.text.length-1]) == -1) {
                    val res = CalculatorParser.calculate(editText.text.toString())
                    val text = if ((res).toString() == "${res.toInt()}.0") {
                        "=${res.toInt()}"
                    } else {
                        "=${res}"
                    }
                    resultText.text = text
                }
            }
        }

    }

    /**
     * Create logic for equal button
     * @param button a button that add equal symbol logic
     */
    fun setEqualLogic(button: FloatingActionButton){
        button.setOnClickListener {
            if(resultText.text.contains('=')) {
                val text = resultText.text.subSequence(1, resultText.text.length)
                resultText.text = editText.text
                editText.text = text
                toEnd(resultScrollView)
                toEnd(editScrollView)
            } else {
                editText.text = "0"
                resultText.text = "=0"
            }
        }
    }

    /**
     *
     */
    fun setPlusLogic(){
        val text = "${editText.text.toString()}+"
        editText.text = text
        
    }

    /**
     *
     */
    fun setMinusLogic(){
        val text = "${editText.text.toString()}-"
        editText.text = text
    }

    /**
     *
     */
    fun setMultiplyLogic(){
        val text = "${editText.text.toString()}*"
        editText.text = text
    }

    /**
     *
     */
    fun setDivideLogic(){
        val text = "${editText.text.toString()}/"
        editText.text = text
    }


}