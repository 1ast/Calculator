package com.forkis.calculator

class EditGrammar {



    companion object {
        val actions = arrayOf('+', '-', '*', '/', '^')
        val numbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.')
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
            if (text.last() in actions){
                result+= "0."
            }

            return result
        }

        fun checkActionBracket(text: String): String {
            var result = text
            var isBracket = false
            var i = 0
            while (i < result.length){
                if(isBracket && result[i] == '^'){
                    result = removeCharAt(result, i)
                    result = removeCharAt(result, i+1)
                    isBracket = false
                    continue
                } else if (isBracket && result[i] in actions){
                    result = removeCharAt(result, i)
                    isBracket = false
                    continue
                }


                isBracket = result[i] == '('
                i++
            }
            return result
        }

        fun checkInput(text: String): String{
            var res = oneZero(text)
            res = oneAction(res)
            return res
        }

        fun removeCharAt(s: String, pos: Int): String {
            return s.substring(
                0,
                pos
            ) + s.substring(pos + 1)
        }
    }
}