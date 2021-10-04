package com.forkis.calculator

import android.util.Log

class EditGrammar {
    companion object {
        fun oneZero(text: String): String{
            var oneZer = false
            var result = text
            var i = 0
            while (i < result.length){


                if (oneZer && text[i] == '0') {
                    result = result.removeRange(i-1,i)
                    continue
                }

                oneZer = result[i] == '0'
                i++
            }
            return result
        }

        fun checkInput(text: String): String{
            var res = oneZero(text)
            return res
        }
    }
}