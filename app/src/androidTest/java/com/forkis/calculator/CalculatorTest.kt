package com.forkis.calculator

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class CalculatorTest {
    lateinit var calculatorParser: CalculatorParser
    @Before
    @Throws(Exception::class)
    fun setUp(){
        calculatorParser = CalculatorParser()
    }

    @Test
    @Throws(Exception::class)
    fun infinity(){
        Assert.assertEquals("\u221E", ResultGrammar.clearResult("40/0"))
    }

    @Test
    @Throws(Exception::class)
    fun nan(){
        Assert.assertEquals("Error", ResultGrammar.clearResult("0/0"))
    }

    @Test
    @Throws(Exception::class)
    fun zero(){
        Assert.assertEquals("0", ResultGrammar.clearResult(""))
    }
}