package com.forkis.calculator

import com.forkis.calculator.CheckGrammar.Companion.isHaveADot
import com.forkis.calculator.CheckGrammar.Companion.isLastAction
import org.junit.Assert
import org.junit.Test
import java.lang.Exception
import com.forkis.calculator.CheckGrammar.Companion.isLastNumber
import com.forkis.calculator.CheckGrammar.Companion.isLastOpenedBracket

class CheckGrammarTest {

    @Test
    @Throws(Exception::class)
    fun lastNumber(){
        Assert.assertEquals(true, isLastNumber("513+4"))
    }

    @Test
    @Throws(Exception::class)
    fun lastNumberWithDot(){
        Assert.assertEquals(true, isLastNumber("5."))
    }

    @Test
    @Throws(Exception::class)
    fun lastNotNumber(){
        Assert.assertEquals(false, isLastNumber("50/"))
    }

    @Test
    @Throws(Exception::class)
    fun nothingNumber(){
        Assert.assertEquals(false, isLastNumber(""))
    }

    @Test
    @Throws(Exception::class)
    fun lastAction(){
        Assert.assertEquals(true, isLastAction("25+"))
    }

    @Test
    @Throws(Exception::class)
    fun lastActionWithDot(){
        Assert.assertEquals(false, isLastAction("25+."))
    }

    @Test
    @Throws(Exception::class)
    fun lastNotAction(){
        Assert.assertEquals(false, isLastAction("2+2"))
    }

    @Test
    @Throws(Exception::class)
    fun nothingAction(){
        Assert.assertEquals(false, isLastAction(""))
    }

    @Test
    @Throws(Exception::class)
    fun lastDot(){
        Assert.assertEquals(true, isHaveADot("25."))
    }

    @Test
    @Throws(Exception::class)
    fun lastNotDotButDotHere(){
        Assert.assertEquals(false, isHaveADot("25.2+40"))
    }

    @Test
    @Throws(Exception::class)
    fun noDot(){
        Assert.assertEquals(false, isHaveADot("25+40"))
    }

    @Test
    @Throws(Exception::class)
    fun nothingDot(){
        Assert.assertEquals(false, isHaveADot(""))
    }

    @Test
    @Throws(Exception::class)
    fun manyDots(){
        Assert.assertEquals(false, isHaveADot("25..+4"))
    }

    @Test
    @Throws(Exception::class)
    fun trueDot(){
        Assert.assertEquals(true, isHaveADot("25.2+44.4"))
    }

    @Test
    @Throws(Exception::class)
    fun lastBracket(){
        Assert.assertEquals(true, isLastOpenedBracket("513+("))
    }

    @Test
    @Throws(Exception::class)
    fun withoutBracket(){
        Assert.assertEquals(false, isLastOpenedBracket("5."))
    }

    @Test
    @Throws(Exception::class)
    fun lastNotBracket(){
        Assert.assertEquals(false, isLastOpenedBracket("50/(40"))
    }

    @Test
    @Throws(Exception::class)
    fun nothingBracket(){
        Assert.assertEquals(false, isLastOpenedBracket(""))
    }

}