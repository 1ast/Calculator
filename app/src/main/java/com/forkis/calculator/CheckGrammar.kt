package com.forkis.calculator

class CheckGrammar {
    companion object{
        val actions = arrayOf('+', '-', '*', '/', '^')
        val numbers = arrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
        const val DOT = '.'
        const val OPEN_BRC = '('
        const val CLOSE_BRC = ')'


        fun isLastAction(text: String): Boolean{
            if (text.isNotEmpty()) {
                return text.last() in actions
            }
            return false
        }

        fun isLastNumber(text: String): Boolean{
            if (text.isNotEmpty()) {
                return text.last() in numbers || text.last() == DOT
            }
            return false
        }

        fun isHaveADot(text: String): Boolean{
            var firstIndex = 0
            for (i in text.indices){
                if (text[i] in actions)
                    firstIndex = i
            }

            while (firstIndex < text.length){
                if (text[firstIndex] == DOT)
                    return true
                firstIndex++
            }
            return false
        }

        fun isLastOpenedBracket(text: String): Boolean{
            if (text.isNotEmpty()){
                return text.last() == OPEN_BRC
            }
            return false
        }
    }
}