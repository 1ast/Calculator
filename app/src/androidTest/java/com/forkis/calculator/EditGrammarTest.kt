package com.forkis.calculator

import org.junit.Assert
import org.junit.Test

class EditGrammarTest {

    @Test
    @Throws(Exception::class)
    fun dotTest(){
        Assert.assertEquals(true, EditGrammar.checkNumberDots("20.0."))
    }

}