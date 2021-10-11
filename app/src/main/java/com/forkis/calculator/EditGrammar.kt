package com.forkis.calculator

import com.forkis.calculator.CheckGrammar.Companion.isLastAction
import com.forkis.calculator.CheckGrammar.Companion.isLastNumber
import com.forkis.calculator.CheckGrammar.Companion.isLastOpenedBracket

class EditGrammar {



    companion object {
        val actions = arrayOf('+', '-', '*', '/', '^')
        val numbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')

        /**
         * Check, that in string only one zero in start of number
         * @param text = stroke will be check
         * @return stroke without more than one zero in start of number
         */
        fun oneZero(text: String): String{
            var oneZer = false
            var act = false
            var result = text
            var i = 0
            while (i < result.length){
                if (oneZer && result[i] == '0') {
                    result = removeCharAt(result, i)
                    continue
                }
                else {
                    oneZer = false
                }
                if(result[i] in actions) act = true
                if(result[i] in numbers && result[i] != '0') act = false
                if (result[i] == '0' && act) {
                    oneZer = true
                    act = false
                }

                i++
            }
            return result
        }

        fun oneAction(text: String): String{
            var result = text
            var i = 0
            var oneAct = false
            while (i < result.length){
                if (oneAct && result[i] in actions){
                    result = removeCharAt(result, i)
                    continue
                }

                oneAct = result[i] in actions
                i++
            }


            return result
        }

        fun checkNumberDots(text: String): Boolean {
            var startNum = 0
            var endNum = 0
            var isDot = false

            for (i in text.indices) {
                if (text[i] in actions) startNum = i + 1
                if (text[i] in numbers || text[i] == '.') endNum = i

            }

            for (i in startNum..endNum) {
                if (text[i] == '.') {
                    isDot = true
                    break
                }
            }
            return isDot
        }

        fun checkPlaceDot(text: String): String{
            var result = text
            if (isLastAction(result) || isLastOpenedBracket(result)){
                result+= "0."
            }

            return result
        }

        fun checkPercent(text: String, action: Char, number: String): String{
            var result = text
            when (action){
                '*', '/' -> result += "$action${clearDoubleFromZeros(number.toDouble()/100)}"
                '^' -> result += "$action(${clearDoubleFromZeros(number.toDouble()/100)}"
                '+', '-' -> {
                    val res = ResultGrammar.clearResult("$result*${clearDoubleFromZeros(number.toDouble()/100)}")
                    result = "$result$action$res"
                }
            }
            return result
        }

        fun getLastAction(text: String): String{
            var test = text
            var result = ""
            while (!isLastAction(test) && test.isNotEmpty()){
                test = removeLast(test)
            }
            if (test.isNotEmpty()){
                result += test.last()
            }
            return result
        }

        fun getLastNumber(text: String): String{
            var test = text
            var result = ""
            while (!isLastNumber(test) && test.isNotEmpty()){
                test = removeLast(test)
            }
            if (test.isNotEmpty()){
                while (isLastNumber(test) && test.isNotEmpty()){
                    result += test.last()
                    test = removeLast(test)
                }
            }
            result = result.reversed()
            return result
        }

        fun clearDoubleFromZeros(number: Double): String{
            var result = number.toString()
            while (result.last() == '0'){
                result = removeLast(result)
            }
            if (result.last() == '.'){
                result = removeLast(result)
            }
            return result
        }

        fun checkActionBracket(text: String): String {
            var result = text
            val isBracket = isLastOpenedBracket(result)
            if (isBracket && result[result.length-1] in actions){
                result = removeCharAt(result, result.length-1)

            }
            return result
        }

        fun checkErr(text: String): String{
            if (text == "Error"){
                return ""
            }
            return text
        }

        fun checkInput(text: String): String{
            var res = checkErr(text)
            res = oneZero(res)
            res = oneAction(res)
            return res
        }

        fun removeCharAt(s: String, pos: Int): String {
            return s.substring(
                0,
                pos
            ) + s.substring(pos + 1)
        }

        fun removeLast(s: String): String{
            return s.substring(0, s.length-1)
        }
    }
}