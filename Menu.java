package EasyShopperMart;

import java.util.Scanner;

public class Menu {



    public void start (){
        System.out.println("Welcome to the  Easy shop");

    }

    public void showOwnerMenuPanel(int userChoice) {
        switch (userChoice){
            case 1:
                System.out.println("Add product");
                break;
            case 2:
                System.out.println("Check profit/loss");
                break;
            default:
                break;
        }

    }

    public void showCustomerMenuPanel(int userChoice) {
        switch(userChoice){
            case 1:
                System.out.println("Choose products from the list");
                break;
            case 2:
                System.out.println("Pay");
                break;
            default:
                break;
        }

    }

    public void showSalesRepresentativeMenuPanel(int userChoice) {
        switch (userChoice){
            case 1 :
                System.out.println("Add products");
                break;
            case 2 :
                System.out.println("Check quantity left");
                break;
            default:
                break;
        }

    }

}
