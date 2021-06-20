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
import com.example.naproject.domain.GetComputerUseCase
import com.example.naproject.feature.mainMenu.presentation.ComputersPresentation
import com.example.naproject.feature.mainMenu.presentation.MainMenuView
import kotlinx.android.synthetic.main.fragment_main_menu.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ComputersFragment : MvpAppCompatFragment(R.layout.fragment_main_menu), MainMenuView {


    private val presenter: ComputersPresentation by moxyPresenter {
        ComputersPresentation(GetComputerUseCase(dipliomApi))
    }
    private var computersAdapter: ComputerAdapter? = null


    override fun onDestroyView() {
        super.onDestroyView()
        computersAdapter = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(rvDisplays) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ComputerAdapter().also {
                computersAdapter = it
            }
        }

        buttonGoAnotherCategory.setText("Мониторы")
        buttonGoAnotherCategory.setOnClickListener({
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, DisplaysFragment())
                .addToBackStack("SeF")
                .commit()
        })
        search_name_bar.hint = "Название"
        search_name_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               presenter.filterComputersByName(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_1_bar.hint = "Процессор"
        search_1_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterComputersByCPU(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_2_bar.hint = "Оперативная память"
        search_2_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterComputersByRAM(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_3_bar.hint = "Видеокарта"
        search_3_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterComputersByGPU(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        search_4_bar.hint = "Емкость накопителя"
        search_4_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.filterComputersByStorage(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun showDisplays(rockets: List<Display>) {
        TODO("Not yet implemented")
    }

    override fun showLoading(isShow: Boolean) {
        RocketsProgress.isVisible = isShow
    }

    override fun showComputers(rockets: List<Computer>) {
        computersAdapter?.submitList(rockets)
    }
}