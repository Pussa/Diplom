package com.example.naproject.feature.mainMenu.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.naproject.Computer
import com.example.naproject.Display
import com.example.naproject.R
import com.example.naproject.di.dipliomApi
import com.example.naproject.domain.GetDisplaysUseCase
import com.example.naproject.feature.mainMenu.presentation.DisplaysPresentation
import com.example.naproject.feature.mainMenu.presentation.MainMenuView
import kotlinx.android.synthetic.main.fragment_main_menu.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DisplaysFragment : MvpAppCompatFragment(R.layout.fragment_main_menu), MainMenuView {


    private val presenter: DisplaysPresentation by moxyPresenter {
        DisplaysPresentation(GetDisplaysUseCase(dipliomApi))
    }
    private var displaysAdapter: DisplaysAdapter? = null

    override fun onDestroyView() {
        super.onDestroyView()
        displaysAdapter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvDisplays) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = DisplaysAdapter().also {
                displaysAdapter = it
            }
        }
        buttonGoAnotherCategory.text = "Компьютеры"
        buttonGoAnotherCategory.setOnClickListener({
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, ComputersFragment())
                .addToBackStack("SeF")
                .commit()
        })
        search_name_bar.hint = "Название"
        search_name_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterDisplaysByName(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_1_bar.hint = "Диагональ"
        search_1_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterDisplaysBySize(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_2_bar.hint = "Разрешение"
        search_2_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterDisplaysByResolution(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_3_bar.hint = "Тип матрицы"
        search_3_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterDisplaysByMatrixType(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_4_bar.hint = "Частота"
        search_4_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterDisplaysByFrequency(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

    }

    override fun showDisplays(rockets: List<Display>) {
        displaysAdapter?.submitList(rockets)
    }

    override fun showLoading(isShow: Boolean) {
        RocketsProgress.isVisible = isShow
    }

    override fun showComputers(rockets: List<Computer>) {
        TODO("Not yet implemented")
    }
}