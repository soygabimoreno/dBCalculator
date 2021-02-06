package com.appacoustic.android.dbcalculator.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.appacoustic.android.dbcalculator.databinding.ActivityMainBinding
import com.appacoustic.android.dbcalculator.framework.event.EventObserver
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel(binding)
        initNumberButtons(binding)
        initCommaButton(binding)
        initOperationButtons(binding)
        initInfoButton(binding)
    }

    private fun initViewModel(binding: ActivityMainBinding) {
        viewModel.sum.observe(
            this,
            { sum ->
                binding.tvSumResult.text = sum?.toString() ?: "?"
            })

        viewModel.input.observe(
            this,
            { input ->
                viewModel.calculateSum(input)
                if (input.isBlank()) {
                    binding.tvSources.text = "?"
                } else {
                    binding.tvSources.text = input
                }
            })
        viewModel.navigateToWeb.observe(
            this,
            EventObserver { uriString ->
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(uriString)
                )
                startActivity(browserIntent)
            })
    }

    private fun initNumberButtons(binding: ActivityMainBinding) {
        binding.btn1.setOnClickListener(::handleNumberClicked)
        binding.btn2.setOnClickListener(::handleNumberClicked)
        binding.btn3.setOnClickListener(::handleNumberClicked)
        binding.btn4.setOnClickListener(::handleNumberClicked)
        binding.btn5.setOnClickListener(::handleNumberClicked)
        binding.btn6.setOnClickListener(::handleNumberClicked)
        binding.btn7.setOnClickListener(::handleNumberClicked)
        binding.btn8.setOnClickListener(::handleNumberClicked)
        binding.btn9.setOnClickListener(::handleNumberClicked)
        binding.btn0.setOnClickListener(::handleNumberClicked)
    }

    private fun initCommaButton(binding: ActivityMainBinding) {
        binding.btnComma.setOnClickListener {
            viewModel.handleCommaClicked()
        }
    }

    private fun initOperationButtons(binding: ActivityMainBinding) {
        binding.btnClear.setOnClickListener {
            viewModel.handleClearClicked()
        }
        binding.ibBackspace.setOnClickListener {
            viewModel.handleBackspaceClicked()
        }
        binding.ibMinus.setOnClickListener {
            viewModel.handleMinusClicked()
        }
        binding.ibAdd.setOnClickListener {
            viewModel.handleAddClicked()
        }
    }

    private fun initInfoButton(binding: ActivityMainBinding) {
        binding.ibInfo.setOnClickListener {
            viewModel.handleInfoClicked()
        }
    }

    private fun handleNumberClicked(view: View) {
        viewModel.handleNumberClicked((view as Button).text.toString())
    }
}
