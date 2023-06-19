package ProductManage;

import data.ProductList;
import java.io.IOException;
import ui.menu;
import util.MyToys;

public class ProductManagement {

    private static final String FILE_NAME = "Product.dat";

    public static void main(String[] args) throws IOException {
        menu menu = new menu("Product Management");
        menu.addOption("    1.     Print");
        menu.addOption("    2.     Sort a list Product(new)");
        menu.addOption("    3.     Create a Product");
        menu.addOption("    4.     Check exits Product");
        menu.addOption("    5.     Search Product infomation by Name");
        menu.addOption("    6.     Update Product");
        menu.addOption("    7.     Save Product to File");
        menu.addOption("    8.     Load and Print list Products from the File");
        menu.addOption("    9.     Print Available Product(new)");
        menu.addOption("    10.    Print Not Available Product(new)");
        menu.addOption("    11.    Search in range by Price(new)");
        menu.addOption("    12.    Search in range by Quantity(new)");
        menu.addOption("    Other. Exit");

        ProductList productList = new ProductList();
        int choice = 0;
        do {
            menu.printMenu();
            choice = MyToys.getAnInterger("    Input your choice: ", "Your choice must be a number!!!");

            switch (choice) {

                case 1:
                    productList.printProduct();
                    break;
                case 2:
                    productList.sortList();
                    break;
                case 3:
                    productList.addProduct();
                    break;
                case 4:
                    productList.checkProductExistV2();
                    break;
                case 5:
                    productList.searchProductbyPartOfName();
                    break;
                case 6:
                    productList.UpdateAndDelete();
                    break;
                case 7:
                    productList.saveFromFile(FILE_NAME);
                    break;
                case 8:
                    productList.readFromFile(FILE_NAME);
                    productList.printProductFromFile();
                    break;
                case 9:
                    productList.printAvailableProduct();
                    break;
                case 10:
                    productList.printUnAvailableProduct();
                    break;
                case 11:
                    productList.searchByRangePrice();
                    break;
                case 12:
                    productList.searchByRangeQuantity();
                    break;
                default:
                    System.out.println("Goodbye!!");

            }
        } while (choice >= 1 && choice <= 12);

    }
}
