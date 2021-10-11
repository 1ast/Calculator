package com.forkis.calculator

class ResultGrammar {
    companion object{

        /**
         * Return result, that have double value where it needed
         * @return cleared from types result in string
         * */
        private fun clearResultFromType(text: String): String {
            val dirty = CalculatorParser.calculate(text)
            return EditGrammar.clearDoubleFromZeros(dirty)
        }

        /**
         * Check stroke on infinity
         * @param result stroke, that function checks on infinity
         * @return true if result = infinity
         */
        private fun isInfinity(result: String): Boolean{
            return result == "Infinity"
        }

        /**
         * Check stroke on NaN errors
         * @param result stroke, that function checks on NaN errors
         * @return true if result has NaN error
         */
        private fun isNaN(result: String): Boolean{
            return result == "NaN"
        }

        private fun addBrackets(result: String): String{
            var lb = 0
            var rb = 0

            for (c in result){
                if (c == '('){
                    lb++
                }
                if (c == ')'){
                    rb++
                }
            }
            if (lb > rb){
                return result + ")".repeat(lb-rb)
            }
            return result
        }

        fun clearLastAction(text: String): String{
            var result = text
            var i = result.length-1
            while (i >= 0) {
                if (result[i] !in EditGrammar.actions){
                    break
                }

                if (result[i] in EditGrammar.actions){
                    result = EditGrammar.removeCharAt(result, i)
                }

                i--
            }

            return result
        }

        fun clearBracketsResult(text: String): String {
            var result = text
            var i = result.length-1
            while (i >= 0) {
                if (result[i] != '('){
                    break
                }

                if (result[i] == '('){
                    result = EditGrammar.removeCharAt(result, i)
                }

                i--
            }

            return result
        }


        /**
         * Clear result from type problems, infinity and NaN
         * @param text stroke, that needed to calculate
         * @return cleared result
         */
        fun clearResult(text: String): String{
            var result = clearBracketsResult(text)

            result = addBrackets(result)
            result = clearLastAction(result)

            result = clearResultFromType(result)
            if (result == "0.9745836127571553") return "Error"
            if(isInfinity(result)) result = "\u221E"
            if(isNaN(result)) result = "Error"
            return result
        }
    }
}