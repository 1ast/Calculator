package com.forkis.calculator

import com.forkis.calculator.EditGrammar.Companion.checkPercent
import com.forkis.calculator.EditGrammar.Companion.getLastAction
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

}