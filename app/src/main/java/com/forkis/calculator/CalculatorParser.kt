package com.forkis.calculator

import android.util.Log
import java.util.*

class CalculatorParser {
    companion object {
        private fun isOperator(c: Char): Boolean{
            if(("+-/*^()").indexOf(c) != -1){
                return true
            }
            return false
        }

        private fun isDelimiter(c: Char): Boolean {
            if(c == ' ')
                return true
            return false
        }

        private fun getPriority(c: Char): Byte{
            return when(c) {
                '(' -> 0
                ')' -> 1
                '+' -> 2
                '-' -> 3
                '*' -> 4
                '/' -> 4
                '^' -> 5
                else -> 6
            }
        }

        private fun getExpression(input: String): String {
            var output = String()
            val operatorStack = Stack<Char>()
            var isOp = -1
            for (i in input.indices) {
                if (isOp > i) {
                    continue
                }

                if (input[i].isDigit()) {
                    isOp = i
                    while (!isOperator(input[isOp])) {
                        output += input[isOp]
                        isOp++

                        if (isOp == input.length) break
                    }
                    output += " "
                }

                if (isOperator(input[i])) {
                    when {
                        input[i] == '(' -> {
                            operatorStack.push(input[i])
                        }
                        input[i] == ')' -> {
                            var s = operatorStack.pop()

                            while (s != '(') {
                                output += "$s "
                                s = operatorStack.pop()
                            }
                        }
                        else -> {
                            if (operatorStack.count() > 0)
                                if (getPriority(input[i]) <= getPriority(operatorStack.peek()))
                                    output += "${operatorStack.pop()} "

                            operatorStack.push(input[i])

                        }
                    }
                }
            }
            while (operatorStack.count() > 0) {
                output += operatorStack.pop()
            }

            return output
        }

        private fun counting(input: String): Double {
            var result = 0.0
            val temp = Stack<Double>()
            var tempI = 0
            for (i in input.indices) {
                if (tempI > i) {
                    continue
                }
                if (input[i].isDigit()) {
                    var a = String()
                    tempI = i
                    while (!isDelimiter(input[tempI]) && !isOperator(input[tempI])) {
                        a += input[tempI]
                        tempI++
                        if (tempI == input.length) break
                    }
                    temp.push(a.toDouble())

                } else if (isOperator(input[i])) {
                    val a = temp.pop()
                    val b = temp.pop()

                    when (input[i]) {
                        '+' -> result = b + a
                        '-' -> result = b - a
                        '*' -> result = b * a
                        '/' -> result = b / a
                        '^' -> result = Math.pow(b, a)
                    }
                    temp.push(result)
                }
            }


            return temp.peek()
        }

        fun calculate(input: String): Double {
            val output = getExpression(input)
            val res = counting(output)
            Log.d("CALCULATOR", res.toString())
            return res
        }
    }
}