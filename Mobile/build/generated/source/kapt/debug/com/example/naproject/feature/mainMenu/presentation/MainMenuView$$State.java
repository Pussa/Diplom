package com.example.naproject.feature.mainMenu.presentation;

import com.example.naproject.Computer;
import com.example.naproject.Display;
import java.lang.Override;
import java.util.List;
import moxy.viewstate.MvpViewState;
import moxy.viewstate.ViewCommand;
import moxy.viewstate.strategy.AddToEndSingleStrategy;

public class MainMenuView$$State extends MvpViewState<MainMenuView> implements MainMenuView {
	@Override
	public void showDisplays(List<Display> arg0) {
		ShowDisplaysCommand showDisplaysCommand = new ShowDisplaysCommand(arg0);
		this.viewCommands.beforeApply(showDisplaysCommand);

		if (hasNotView()) {
			return;
		}

		for (MainMenuView view : this.views) {
			view.showDisplays(arg0);
		}

		this.viewCommands.afterApply(showDisplaysCommand);
	}

	@Override
	public void showLoading(boolean arg0) {
		ShowLoadingCommand showLoadingCommand = new ShowLoadingCommand(arg0);
		this.viewCommands.beforeApply(showLoadingCommand);

		if (hasNotView()) {
			return;
		}

		for (MainMenuView view : this.views) {
			view.showLoading(arg0);
		}

		this.viewCommands.afterApply(showLoadingCommand);
	}

	@Override
	public void showComputers(List<Computer> arg0) {
		ShowComputersCommand showComputersCommand = new ShowComputersCommand(arg0);
		this.viewCommands.beforeApply(showComputersCommand);

		if (hasNotView()) {
			return;
		}

		for (MainMenuView view : this.views) {
			view.showComputers(arg0);
		}

		this.viewCommands.afterApply(showComputersCommand);
	}

	public class ShowDisplaysCommand extends ViewCommand<MainMenuView> {
		public final List<Display> arg0;

		ShowDisplaysCommand(List<Display> arg0) {
			super("showDisplays", AddToEndSingleStrategy.class);

			this.arg0 = arg0;
		}

		@Override
		public void apply(MainMenuView mvpView) {
			mvpView.showDisplays(arg0);
		}
	}

	public class ShowLoadingCommand extends ViewCommand<MainMenuView> {
		public final boolean arg0;

		ShowLoadingCommand(boolean arg0) {
			super("showLoading", AddToEndSingleStrategy.class);

			this.arg0 = arg0;
		}

		@Override
		public void apply(MainMenuView mvpView) {
			mvpView.showLoading(arg0);
		}
	}

	public class ShowComputersCommand extends ViewCommand<MainMenuView> {
		public final List<Computer> arg0;

		ShowComputersCommand(List<Computer> arg0) {
			super("showComputers", AddToEndSingleStrategy.class);

			this.arg0 = arg0;
		}

		@Override
		public void apply(MainMenuView mvpView) {
			mvpView.showComputers(arg0);
		}
	}
}
