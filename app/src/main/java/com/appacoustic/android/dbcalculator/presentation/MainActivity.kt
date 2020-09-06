package com.appacoustic.android.dbcalculator.presentation

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.appacoustic.android.dbcalculator.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.androidx.scope.lifecycleScope as koinScope

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by koinScope.viewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initEditText()
        initNumberButtons()
        initCommaButton()
        initOperationButtons()
    }

    private fun initViewModel() {
        viewModel.sum.observe(this, { sum ->
            tvSumResult.text = sum?.toString() ?: "?"
        })

        viewModel.input.observe(this, { input ->
            viewModel.calculateSum(input)
            if (input.isBlank()) {
                tvSources.text = "?"
            } else {
                tvSources.text = input
            }
        })
    }

    private fun initEditText() {
//        viewModel.handleSourcesChanged(input.toString())
    }

    private fun initNumberButtons() {
        btn1.setOnClickListener(::handleNumberClicked)
        btn2.setOnClickListener(::handleNumberClicked)
        btn3.setOnClickListener(::handleNumberClicked)
        btn4.setOnClickListener(::handleNumberClicked)
        btn5.setOnClickListener(::handleNumberClicked)
        btn6.setOnClickListener(::handleNumberClicked)
        btn7.setOnClickListener(::handleNumberClicked)
        btn8.setOnClickListener(::handleNumberClicked)
        btn9.setOnClickListener(::handleNumberClicked)
        btn0.setOnClickListener(::handleNumberClicked)
    }

    private fun initCommaButton() {
        btnComma.setOnClickListener {
            viewModel.handleCommaClicked()
        }
    }

    private fun initOperationButtons() {
        btnClear.setOnClickListener {
            viewModel.handleClearClicked()
        }
        ibBackspace.setOnClickListener {
            viewModel.handleBackspaceClicked()
        }
        ibMinus.setOnClickListener {
            viewModel.handleMinusClicked()
        }
        ibAdd.setOnClickListener {
            viewModel.handleAddClicked()
        }
    }

    private fun handleNumberClicked(view: View) {
        viewModel.handleNumberClicked((view as Button).text.toString())
    }
}
