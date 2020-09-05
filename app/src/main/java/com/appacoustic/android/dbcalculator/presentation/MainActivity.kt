package com.appacoustic.android.dbcalculator.presentation

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.appacoustic.android.dbcalculator.R
import com.appacoustic.android.dbcalculator.framework.setOnTextChangedListener
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
        initFab()
    }

    private fun initViewModel() {
        viewModel.sum.observe(this, { sum ->
            tvSumResult.text = sum?.toString() ?: "?"
        })
    }

    private fun initEditText() {
        Handler(Looper.getMainLooper()).postDelayed({
            etSources.requestFocus()
            val imr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imr.showSoftInput(etSources, InputMethodManager.SHOW_IMPLICIT)
        }, 500)

        etSources.setOnTextChangedListener { input ->
            viewModel.handleSourcesChanged(input)
        }
    }

    private fun initFab() {
        fabAdd.setOnClickListener {
            Toast.makeText(this, "FOO", Toast.LENGTH_SHORT).show()
        }
    }
}
