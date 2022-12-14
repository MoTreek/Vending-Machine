package com.techelevator;

import com.techelevator.view.Menu;

import javax.swing.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_SALES_REPORT = "SALES REPORT";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SALES_REPORT };

	private static final String SECOND_MENU_OPTION_FEED_MONEY = "Insert Money";
	private static final String SECOND_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SECOND_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SECOND_MENU_OPTIONS = {SECOND_MENU_OPTION_FEED_MONEY, SECOND_MENU_OPTION_SELECT_PRODUCT, SECOND_MENU_OPTION_FINISH_TRANSACTION};

	private boolean checker = true;

	private Menu menu;

	// Initialize VendingMachine
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {
		VendingMachine vendingMachine = new VendingMachine();

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);



			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingMachine.getInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				checker = true;
				while (checker) {
					String secondChoice = (String) menu.getChoiceFromOptions(SECOND_MENU_OPTIONS);

					if (secondChoice.equals(SECOND_MENU_OPTION_FEED_MONEY)) {
						// VendingMachine calls MoneyBox add X money method
						vendingMachine.feedMoney(menu);

					} else if (secondChoice.equals(SECOND_MENU_OPTION_SELECT_PRODUCT)) {
						vendingMachine.getInventory();
						vendingMachine.selectProduct(menu);

					} else if (secondChoice.equals(SECOND_MENU_OPTION_FINISH_TRANSACTION)) {
						checker = vendingMachine.finishTransaction();

					}
					
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.exit(0);
		}
			//

			// Make a new menu for Purchase


		else if (choice.equals(MAIN_MENU_SALES_REPORT))	{
			vendingMachine.salesReport();
			}// Do what the choice says
		}
	}



	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
