package com.example.naproject.feature.mainMenu.ui

import android.os.Bundle
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

class DisplaysFragment: MvpAppCompatFragment(R.layout.fragment_main_menu), MainMenuView {


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
        buttonGoAnotherCategory.setText("Компьютеры")
        buttonGoAnotherCategory.setOnClickListener({
            requireFragmentManager().beginTransaction()
                .replace(R.id.container, ComputersFragment())
                .addToBackStack("SeF")
                .commit()
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