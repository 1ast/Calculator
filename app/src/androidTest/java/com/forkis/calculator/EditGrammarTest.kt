package com.forkis.calculator

import com.forkis.calculator.EditGrammar.Companion.checkPercent
import com.forkis.calculator.EditGrammar.Companion.getLastAction
import com.forkis.calculator.EditGrammar.Companion.getLastNumber
import org.junit.Assert
import org.junit.Test

class EditGrammarTest {

    @Test
    @Throws(Exception::class)
    fun dotTest(){
        Assert.assertEquals(true, EditGrammar.checkNumberDots("20.0."))
    }

    @Test
    @Throws(Exception::class)
    fun removeLast(){
        Assert.assertEquals("25+", EditGrammar.removeLast("25+3"))
    }

    @Test
    @Throws(Exception::class)
    fun subStr(){
        val text = "25+0.5"
        val lastAction = getLastAction(text)
        val lastNumber = getLastNumber(text)
        Assert.assertEquals("25", text.substring(0..(text.length-(lastAction.length+lastNumber.length+1))))
    }

    class PercentTests {
        @Test
        @Throws(Exception::class)
        fun percentTestPlus() {
            Assert.assertEquals("5+1", checkPercent("5", '+', "20"))
        }

        @Test
        @Throws(Exception::class)
        fun percentTestMinus(){
            Assert.assertEquals("5-1", checkPercent("5", '-', "20"))
        }

        @Test
        @Throws(Exception::class)
        fun percentTestMultiply(){
            Assert.assertEquals("5*0.2", checkPercent("5", '*', "20"))
        }

        @Test
        @Throws(Exception::class)
        fun percentTestDivide(){
            Assert.assertEquals("5/0.2", checkPercent("5", '/', "20"))
        }

        @Test
        @Throws(Exception::class)
        fun percentTestDegree(){
            Assert.assertEquals("5^(0.2", checkPercent("5", '^', "20"))
        }
    }

    class GetLastActionTest{
        @Test
        @Throws(Exception::class)
        fun onlyNumber(){
            Assert.assertEquals("", getLastAction("5"))
        }

        @Test
        @Throws(Exception::class)
        fun nothing(){
            Assert.assertEquals("", getLastAction(""))
        }

        @Test
        @Throws(Exception::class)
        fun minusAction(){
            Assert.assertEquals("-", getLastAction("5-1"))
        }

        @Test
        @Throws(Exception::class)
        fun lastIsAction(){
            Assert.assertEquals("+", getLastAction("5-1+"))
        }

        @Test
        @Throws(Exception::class)
        fun degreeAction(){
            Assert.assertEquals("^", getLastAction("5-1^(0.5"))
        }

    }


    class GetLastNumberTest{
        @Test
        @Throws(Exception::class)
        fun onlyNumber(){
            Assert.assertEquals("5", getLastNumber("5"))
        }

        @Test
        @Throws(Exception::class)
        fun nothing(){
            Assert.assertEquals("", getLastNumber(""))
        }

        @Test
        @Throws(Exception::class)
        fun plusNumber(){
            Assert.assertEquals("23.32", getLastNumber("50+23.32"))
        }

        @Test
        @Throws(Exception::class)
        fun lastIsAction(){
            Assert.assertEquals("50", getLastNumber("50+"))
        }

        @Test
        @Throws(Exception::class)
        fun dotNumber(){
            Assert.assertEquals("50.", getLastNumber("50.4+50."))
        }

    }
}