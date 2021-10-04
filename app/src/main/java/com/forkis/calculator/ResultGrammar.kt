package com.forkis.calculator

class ResultGrammar {
    companion object{

        /**
         * Return result, that have double value where it needed
         * @return cleared from types result in string
         * */
        fun clearResultFromType(text: String): String {
            val dirty = CalculatorParser.calculate(text)
            return if (dirty.toString() == "${dirty.toInt()}.0"){
                "${dirty.toInt()}"
            } else {
                dirty.toString()
            }
        }

        /**
         * Check stroke on infinity
         * @param result stroke, that function checks on infinity
         * @return true if result = infinity
         */
        fun isInfinity(result: String): Boolean{
            return result == "Infinity"
        }

        /**
         * Check stroke on NaN errors
         * @param result stroke, that function checks on NaN errors
         * @return true if result has NaN error
         */
        fun isNaN(result: String): Boolean{
            return result == "NaN"
        }


        /**
         * Clear result from type problems, infinity and NaN
         * @param text stroke, that needed to calculate
         * @return cleared result
         */
        fun clearResult(text: String): String{
            var result = clearResultFromType(text)
            if(isInfinity(result)) result = "\u221E"
            if(isNaN(result)) result = "Error"
            return result
        }
    }
}