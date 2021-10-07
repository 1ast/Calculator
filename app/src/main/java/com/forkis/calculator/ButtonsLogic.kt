package com.forkis.calculator

import android.widget.HorizontalScrollView
import com.forkis.calculator.MainActivity.Companion.toEnd
import com.forkis.calculator.ResultGrammar.Companion.clearResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import java.util.*

/**
 * Class, that initialise and work with button logic
 */
class ButtonsLogic(
    private val editText: MaterialTextView, private val editScrollView: HorizontalScrollView,
    private val resultText: MaterialTextView, private val resultScrollView: HorizontalScrollView) {




    /**
     * Return result with equal symbol
     * @param str string, where need to add a equal symbol
     * @return "=${your stroke}"
     */
    private fun addEqual(str: String = "0"): String {
        return "=$str"
    }

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

            text = EditGrammar.checkInput(text)
            editText.text = text
            val res = addEqual(clearResult(text))
            resultText.text = res
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
                Actions.Dot -> setDotLogic()
                Actions.LeftBracket -> setLeftBracketLogic()
                Actions.RightBracket -> setRightBracketLogic()
                Actions.Degree -> setDegreeLogic()
                Actions.Sqr -> setSqrLogic()
                Actions.Sqrt -> setSqrtLogic()
                else -> {}

            }
            toEnd(resultScrollView)
            toEnd(editScrollView)
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
                    val res = addEqual(clearResult(editText.text.toString()))
                    resultText.text = res
                }
            }

            toEnd(editScrollView)
            toEnd(resultScrollView)
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

            } else {
                editText.text = "0"
                resultText.text = "=0"

            }
        }

        toEnd(editScrollView)
        toEnd(resultScrollView)
    }

    /**
     * Set logic for plus button
     */
    private fun setPlusLogic(){
        var text = "${editText.text}+"
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text

    }

    /**
     * Set logic for minus button
     */
    private fun setMinusLogic(){
        var text = "${editText.text}-"
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for multiply button
     */
    private fun setMultiplyLogic(){
        var text = "${editText.text}*"
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for divide button
     */
    private fun setDivideLogic(){
        var text = "${editText.text}/"
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for left bracket button
     */
    private fun setLeftBracketLogic(){
        var text = "${editText.text}"
        text += if (text.last() in EditGrammar.actions){
            "("
        } else {
            "*("
        }

        editText.text = text
    }

    /**
     * Set logic for right bracket button
     */
    private fun setRightBracketLogic(){
        var text = "${editText.text}"
        if (text.last() in EditGrammar.actions){
            text = text.removeRange(text.length-1, text.length)
            text += ")"
        }
        else {
            text += ")"
        }
        editText.text = text
    }

    /**
     * Set logic for dot button
     */
    private fun setDotLogic(){
        var text = "${editText.text}"
        text = EditGrammar.checkPlaceDot(text)
        if (!EditGrammar.checkNumberDots(text)) {
            text += "."
        }

        editText.text = text
    }

    /**
     * Set logic for degree button
     */
    private fun setDegreeLogic(){
        var text = "${editText.text}^("
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for sqr button
     */
    fun setSqrLogic(){
        var text = "${editText.text}^(2)"
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text
        val res = addEqual(clearResult(text))
        resultText.text = res
        toEnd(editScrollView)
        toEnd(resultScrollView)
    }

    /**
     * Set logic for sqrt button
     */
    fun setSqrtLogic(){
        var text = "${editText.text}^(1/2)"
        text = EditGrammar.oneAction(text)
        text = EditGrammar.checkActionBracket(text)
        editText.text = text
        val res = addEqual(clearResult(text))
        resultText.text = res
        toEnd(editScrollView)
        toEnd(resultScrollView)
    }
}