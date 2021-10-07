package com.forkis.calculator

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.animation.ArgbEvaluatorCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView


class MainActivity : AppCompatActivity() {

    private lateinit var resultScrollView: HorizontalScrollView
    private lateinit var editScrollView: HorizontalScrollView

    private lateinit var slideView: View
    private lateinit var bottomSheet: LinearLayout

    private lateinit var resultText: MaterialTextView
    private lateinit var editText: MaterialTextView

    private lateinit var zeroButton: FloatingActionButton
    private lateinit var oneButton: FloatingActionButton
    private lateinit var twoButton: FloatingActionButton
    private lateinit var threeButton: FloatingActionButton
    private lateinit var fourButton: FloatingActionButton
    private lateinit var fiveButton: FloatingActionButton
    private lateinit var sixButton: FloatingActionButton
    private lateinit var sevenButton: FloatingActionButton
    private lateinit var eightButton: FloatingActionButton
    private lateinit var nineButton: FloatingActionButton

    private lateinit var dotButton: FloatingActionButton
    private lateinit var equalButton: FloatingActionButton
    private lateinit var plusButton: FloatingActionButton
    private lateinit var minusButton: FloatingActionButton
    private lateinit var multiplyButton: FloatingActionButton
    private lateinit var divideButton: FloatingActionButton
    private lateinit var clearButton: FloatingActionButton
    private lateinit var sqrtButton: FloatingActionButton
    private lateinit var percentButton: FloatingActionButton
    private lateinit var deleteButton: FloatingActionButton

    private lateinit var lgButton: FloatingActionButton
    private lateinit var degButton: FloatingActionButton
    private lateinit var sqrButton: FloatingActionButton
    private lateinit var factorialButton: FloatingActionButton

    private lateinit var lbButton: FloatingActionButton
    private lateinit var rbButton: FloatingActionButton
    private lateinit var oppositeButton: FloatingActionButton
    private lateinit var piButton: FloatingActionButton

    private lateinit var sinButton: FloatingActionButton
    private lateinit var cosButton: FloatingActionButton
    private lateinit var tgButton: FloatingActionButton
    private lateinit var ctgButton: FloatingActionButton

    private lateinit var arcsinButton: FloatingActionButton
    private lateinit var arccosButton: FloatingActionButton
    private lateinit var arctgButton: FloatingActionButton
    private lateinit var arcctgButton: FloatingActionButton

    lateinit var buttonsLogic: ButtonsLogic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialise()
        initialisingButtons()
    }

    /**
     * Initialising all views.
     *
     * This method initialise views of main activity and give start values for it.
     * For bottom sheet it add start method, that initialise shadow.
     *
     * */
    private fun initialise(){
        resultScrollView = findViewById(R.id.resultScroll)
        editScrollView = findViewById(R.id.editScroll)

        resultText = findViewById(R.id.resultText)
        editText = findViewById(R.id.editText)

        zeroButton = findViewById(R.id.zeroButton)
        oneButton = findViewById(R.id.oneButton)
        twoButton = findViewById(R.id.twoButton)
        threeButton = findViewById(R.id.threeButton)
        fourButton = findViewById(R.id.fourButton)
        fiveButton = findViewById(R.id.fiveButton)
        sixButton = findViewById(R.id.sixButton)
        sevenButton = findViewById(R.id.sevenButton)
        eightButton = findViewById(R.id.eightButton)
        nineButton = findViewById(R.id.nineButton)

        dotButton = findViewById(R.id.dotButton)
        equalButton = findViewById(R.id.equalButton)
        plusButton = findViewById(R.id.plusButton)
        minusButton = findViewById(R.id.minusButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        divideButton = findViewById(R.id.divideButton)
        sqrtButton = findViewById(R.id.sqrtButton)
        clearButton = findViewById(R.id.clearButton)
        percentButton = findViewById(R.id.percentButton)
        deleteButton = findViewById(R.id.deleteButton)

        lgButton = findViewById(R.id.lgButton)
        degButton = findViewById(R.id.degButton)
        sqrButton = findViewById(R.id.sqrButton)
        factorialButton = findViewById(R.id.factorialButton)

        lbButton = findViewById(R.id.leftBracketButton)
        rbButton = findViewById(R.id.rightBracketButton)
        oppositeButton = findViewById(R.id.oppositeButton)
        piButton = findViewById(R.id.piButton)

        sinButton = findViewById(R.id.sinButton)
        cosButton = findViewById(R.id.cosButton)
        tgButton = findViewById(R.id.tgButton)
        ctgButton = findViewById(R.id.ctgButton)

        arcsinButton = findViewById(R.id.arcsinButton)
        arccosButton = findViewById(R.id.arccosButton)
        arctgButton = findViewById(R.id.arctgButton)
        arcctgButton = findViewById(R.id.arcctgButton)


        slideView = findViewById(R.id.shadowView)
        bottomSheet = findViewById(R.id.bottomSheet)

        buttonsLogic = ButtonsLogic(editText, editScrollView, resultText, resultScrollView)

        setupBottomSheet()
        toEnd(editScrollView)
    }



    /**
     * Initialise bottom sheet and shadow for it.
     *
     *     Also usable for set shadows color and logic of bottom sheet.
     * */
    private fun setupBottomSheet(){
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    BottomSheetBehavior.STATE_COLLAPSED -> slideView.setBackgroundColor(startColor)
                    BottomSheetBehavior.STATE_SETTLING -> {}
                    BottomSheetBehavior.STATE_DRAGGING -> {}
                    BottomSheetBehavior.STATE_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {}
                    BottomSheetBehavior.STATE_HIDDEN -> {}
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val color = ArgbEvaluatorCompat.getInstance().evaluate(
                    slideOffset,
                    startColor,
                    endColor
                )
                slideView.setBackgroundColor(color)
            }

        }
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        bottomSheetBehavior.saveFlags = BottomSheetBehavior.SAVE_ALL
    }


    /**
     * Initialise buttons logic.
     * */
    fun initialisingButtons(){
        buttonsLogic.setNumberLogic(oneButton, 1)
        buttonsLogic.setNumberLogic(twoButton, 2)
        buttonsLogic.setNumberLogic(threeButton, 3)
        buttonsLogic.setNumberLogic(fourButton, 4)
        buttonsLogic.setNumberLogic(fiveButton, 5)
        buttonsLogic.setNumberLogic(sixButton, 6)
        buttonsLogic.setNumberLogic(sevenButton, 7)
        buttonsLogic.setNumberLogic(eightButton, 8)
        buttonsLogic.setNumberLogic(nineButton, 9)
        buttonsLogic.setNumberLogic(zeroButton, 0)
        buttonsLogic.setDeleteLogic(deleteButton)
        buttonsLogic.setClearLogic(clearButton)

        buttonsLogic.setActionLogic(plusButton, Actions.Plus)
        buttonsLogic.setActionLogic(minusButton, Actions.Minus)
        buttonsLogic.setActionLogic(multiplyButton, Actions.Multiply)
        buttonsLogic.setActionLogic(divideButton, Actions.Divide)

        buttonsLogic.setActionLogic(dotButton, Actions.Dot)
        buttonsLogic.setActionLogic(sqrtButton, Actions.Sqrt)
        buttonsLogic.setActionLogic(percentButton, Actions.Percent)

        buttonsLogic.setEqualLogic(equalButton)

        buttonsLogic.setActionLogic(lgButton, Actions.Lg)
        buttonsLogic.setActionLogic(degButton, Actions.Degree)
        buttonsLogic.setActionLogic(sqrButton, Actions.Sqr)
        buttonsLogic.setActionLogic(factorialButton, Actions.Factorial)

        buttonsLogic.setActionLogic(lbButton, Actions.LeftBracket)
        buttonsLogic.setActionLogic(rbButton, Actions.RightBracket)
        buttonsLogic.setActionLogic(oppositeButton, Actions.Opposite)
        buttonsLogic.setActionLogic(piButton, Actions.Pi)

        buttonsLogic.setActionLogic(sinButton, Actions.Sin)
        buttonsLogic.setActionLogic(cosButton, Actions.Cos)
        buttonsLogic.setActionLogic(tgButton, Actions.Tg)
        buttonsLogic.setActionLogic(ctgButton, Actions.Ctg)

        buttonsLogic.setActionLogic(arcsinButton, Actions.Arcsin)
        buttonsLogic.setActionLogic(arccosButton, Actions.Arccos)
        buttonsLogic.setActionLogic(arctgButton, Actions.Arctg)
        buttonsLogic.setActionLogic(arcctgButton, Actions.Arcctg)
    }

    companion object{
        val startColor = Color.parseColor("#00DDDDDD")
        val endColor = Color.parseColor("#80DDDDDD")


        /**
         * Scroll horizontal scroll views to end.
         *
         *     Work only with horizontal scroll views,
         *     but if i not be lazy, i create abstract method, that scroll also scroll view.
         *
         * @param scrollView scroll view, that need to be scrolled
         */
        fun toEnd(scrollView: HorizontalScrollView){
            scrollView.postDelayed({scrollView.scrollBy(scrollView.right, 0)}, 10)
        }
    }

}

