package com.forkis.calculator

import android.widget.HorizontalScrollView
import com.forkis.calculator.CheckGrammar.Companion.isLastAction
import com.forkis.calculator.CheckGrammar.Companion.isLastOpenedBracket
import com.forkis.calculator.EditGrammar.Companion.checkActionBracket
import com.forkis.calculator.EditGrammar.Companion.checkErr
import com.forkis.calculator.EditGrammar.Companion.checkPercent
import com.forkis.calculator.EditGrammar.Companion.oneAction
import com.forkis.calculator.MainActivity.Companion.toEnd
import com.forkis.calculator.ResultGrammar.Companion.clearResult
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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

    private fun end(){
        toEnd(editScrollView)
        toEnd(resultScrollView)
    }

    /**
     * Create logic for buttons with numbers
     * @param button a button that add logic
     * @param number number of button
     */
    fun setNumberLogic(button: FloatingActionButton, number: Int) {
        button.setOnClickListener {
            var text = editText.text.toString()
            text = checkErr(text)
            if (text == "0")
                text = number.toString()
            else
                text += number.toString()
            var res = ""
            runBlocking {
                val job = launch {
                    text = EditGrammar.checkInput(text)
                    res = addEqual(clearResult(text))

                }
                job.join()
                editText.text = text
                resultText.text = res
                end()
            }
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
            end()
        }
    }

    /**
     * Create logic for buttons with numbers
     * @param button a button that add action logic
     * @param action type of action
     */
    fun setActionLogic(button: FloatingActionButton, action: Actions){
        button.setOnClickListener {
            editText.text = checkErr(editText.text.toString())
            if (editText.text == ""){
                editText.text = "0"
            }
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
                Actions.Percent -> setPercentLogic()
                else -> {}

            }

            end()
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

            end()
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

        end()
    }

    /**
     * Set logic for plus button
     */
    private fun setPlusLogic(){
        var text = "${editText.text}+"
        text = oneAction(text)
        text = checkActionBracket(text)
        editText.text = text

    }

    /**
     * Set logic for minus button
     */
    private fun setMinusLogic(){
        var text = "${editText.text}-"
        text = oneAction(text)
        text = checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for multiply button
     */
    private fun setMultiplyLogic(){
        var text = "${editText.text}*"
        text = oneAction(text)
        text = checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for divide button
     */
    private fun setDivideLogic(){
        var text = "${editText.text}/"
        text = oneAction(text)
        text = checkActionBracket(text)
        editText.text = text
    }

    /**
     * Set logic for left bracket button
     */
    private fun setLeftBracketLogic(){
        var text = "${editText.text}"
        if (text == "0"){
            editText.text = "("
            return
        }
        text += if (isLastAction(text) || isLastOpenedBracket(text)){
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
        var text = "${editText.text}"
        text = oneAction(text)
        if (!isLastOpenedBracket(text)){
            text += "^("
        }
        editText.text = text
    }

    /**
     * Set logic for sqr button
     */
    fun setSqrLogic(){
        var text = "${editText.text}"
        text = oneAction(text)
        if (!isLastOpenedBracket(text)){
            text += "^(2)"
            editText.text = text
            val res = addEqual(clearResult(text))
            resultText.text = res
            end()
        }
    }

    /**
     * Set logic for sqrt button
     */
    fun setSqrtLogic(){
        var text = "${editText.text}"
        text = oneAction(text)
        if (!isLastOpenedBracket(text)){
            text += "^(1/2)"
            editText.text = text
            val res = addEqual(clearResult(text))
            resultText.text = res
            end()
        }
    }

    fun setPercentLogic(){
        var text = "${editText.text}"
        if (!isLastAction(text)) {
            val lastAction = EditGrammar.getLastAction(text)
            val lastNumber = EditGrammar.getLastNumber(text)
            val textWithoutNumbers = text.substring(0..(text.length-(lastAction.length+lastNumber.length+1)))
            if (lastAction.isNotEmpty() && lastNumber.isNotEmpty() && textWithoutNumbers.isNotEmpty()){
            text = checkPercent(textWithoutNumbers, lastAction.toCharArray()[0], lastNumber)
            editText.text = text
            val res = addEqual(clearResult(text))
            resultText.text = res
            end()
            }
        }
    }
}