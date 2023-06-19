package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import util.MyToys;
import util.UpdateValid;

public class ProductList extends ArrayList<Product> implements ProductDAO {

    private static Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "Product.dat";

    @Override
    public void addProduct() {
        try {
            String ProductID, ProductName;
            double UnitPrice;
            int Quantity;
            String Status;

            do {
                //Product ID 5 character va khong dc trunhg
                ProductID = MyToys.getStringbyFormat("Input Product ID: ", "The Product ID must be at five character and no spaces", "^[S][\\S]{5}$");
                ProductID = ProductID.toUpperCase();
                if (checkDupplicateByID(ProductID) || checkDupplicatedByIDFromFile(ProductID, FILE_NAME)) {
                    System.err.println(ProductID + " is exist");
                }
            } while (checkDupplicateByID(ProductID) || checkDupplicatedByIDFromFile(ProductID, FILE_NAME));

            do {
                ProductName = MyToys.getStringbyFormat("Input Product Name: ", "The Product Name must be at least five character and no spaces", "[\\S]{5,}$");
                ProductName = MyToys.toUpperFirstLetter(ProductName);
                if (checkDupplicateByName(ProductName) || checkDupplicatedByNameFromFile(ProductName, FILE_NAME)) {
                    System.err.println(ProductName + " is exist");
                }
            } while (checkDupplicateByName(ProductName) || checkDupplicatedByNameFromFile(ProductName, FILE_NAME));

            ProductName = MyToys.toUpperFirstLetter(ProductName);
            UnitPrice = MyToys.getDouble("Input UnitPrice: ", "UnitPrice must in range " + 0 + " to " + 10000 + " !!!", 0, 10000);
            Quantity = MyToys.getAnInteger("Input Quantity: ", "Quantity must in range " + 0 + " to " + 1000 + " !!!", 0, 1000);

            //Input status
            System.out.println("    Status:");
            System.out.println("    0. Not Available");
            System.out.println("    1. Available");

            int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 or 0\n", 0, 1);
            if (choice == 0) {
                Status = "Not Available";
            } else {
                Status = "Available";
            }

            this.add(new Product(ProductID, ProductName, UnitPrice, Quantity, Status));
            System.err.println("Create successfully");

            if (goBackMenu()) {
                addProduct();
            } else {
                System.err.println("Exit");
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void printProduct() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        System.out.println("Here the list:");
        System.out.println("|Product ID     |Product Name   |Unit Price |Quantity |Status");
        for (Product x : this) {
            System.out.print(x);
        }

        if (goBackMenu()) {
            printProduct();
        } else {
            System.err.println("Exit");
        }
    }

    public void sortList() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        System.out.println("------======(Sort Type)======------");
        System.out.println("    1   -   Sort by Product ID.");
        System.out.println("    2   -   Sort by Product Name.");
        System.out.println("    3   -   Sort by Product Price.");
        System.out.println("    4   -   Sort by Product Quantity.");
        System.out.println("    5   -   Sort by Product Status.");
        System.out.println("    6   -   Quit to menu.");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1...6\n", 1, 6);
        if (choice == 1) {
            Collections.sort(this, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getProductID().compareToIgnoreCase(o2.getProductID());
                }
            });
        } else if (choice == 2) {
            Collections.sort(this, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getProductName().compareToIgnoreCase(o2.getProductName());

                }
            });
        } else if (choice == 3) {
            Collections.sort(this, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return (int) (o1.getUnitPrice() - o2.getUnitPrice());

                }
            });
        } else if (choice == 4) {
            Collections.sort(this, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o2.getQuantity() - o1.getQuantity();
                }
            });
        } else if (choice == 5) {
            Collections.sort(this, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o1.getStatus().compareTo(o2.getStatus());
                }
            });
        } else {
            System.err.println("Exit");
            return;
        }

        System.err.println("Sort complete!!");
    }

    @Override
    public void printProductFromFile() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        Collections.sort(this, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int result = o2.getQuantity() - o1.getQuantity();
                if (result == 0) {
                    return (int) (o1.getUnitPrice() - o2.getUnitPrice());
                }
                return result;
            }
        });

        System.out.println("Here the list:");
        System.out.println("|Product ID     |Product Name   |Unit Price |Quantity |Status");
        for (Product x : this) {
            System.out.print(x);
        }

    }

    //check trong list
    public void checkProductExistV1() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        boolean check = true;
        String checkProductName;
        checkProductName = MyToys.getString("Input Product Name: ", "The Product Name must be not empty!!");
        if (checkDupplicateByName(checkProductName)) {
            System.err.println("Exist Product " + checkProductName);
        } else {
            System.err.println("No Product Found!");
            check = false;
        }

        if (goBackMenu()) {
            checkProductExistV1();
        } else {
            System.out.println("Exit");
        }
    }
//------------------------------------------------------------------------------
    //check trong file

    @Override
    public void checkProductExistV2() throws IOException {

        ArrayList<Product> productList = new ArrayList<>();
        productList = readFromFilel(FILE_NAME);

        boolean check = true;
        String checkProductName;
        checkProductName = MyToys.getString("Input Product Name: ", "The Product Name must be not empty!!");

        if (checkDupplicateByName(checkProductName, productList)) {
            System.err.println("Exist Product " + checkProductName);
        } else {
            System.err.println("No Product Found!");
            check = false;
        }

        if (goBackMenu()) {
            checkProductExistV2();
        } else {
            System.err.println("Exit");
        }
    }

    private boolean checkDupplicateByName(String s, ArrayList<Product> p) {
        for (Product x : p) {
            if (x.getProductName().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
//--------------------------------------------------------------------------------

    public boolean checkDupplicateByID(String s) {
        for (Product x : this) {
            if (x.getProductID().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDupplicateByName(String s) {
        for (Product x : this) {
            if (x.getProductName().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void searchProductbyPartOfName() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        String searchName;
        searchName = MyToys.getString("Input Product Name to search: ", "Empty!!");
        int pos = searchProductbyPartOfName(searchName);
        if (pos >= 0) {
            System.out.println("FOUND!!");
            for (Product x : this) {
                if ((x.getProductName().toUpperCase()).contains(searchName.toUpperCase())) {
                    System.out.print(x);
                }
            }
        } else {
            System.err.println("NOT FOUND!!");
        }

        if (goBackMenu()) {
            searchProductbyPartOfName();
        } else {
            System.err.println("Exit");
        }
    }

    public int searchProductbyPartOfName(String searchName) {
        for (int i = 0; i < this.size(); i++) {
            if ((this.get(i).getProductName().toUpperCase()).contains(searchName.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    public int searchProductbyID(String searchID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equalsIgnoreCase(searchID)) {
                return i;
            }
        }
        return -1;
    }

    public void updateProduct() {
        String updateID;

        String updateName;
        double updateUnitPrice;
        int updateQuantity;
        String updateStatus;

        updateID = MyToys.getStringbyFormat("Input Product ID you want to update: ", "The Product ID must be at least five character and no spaces", "[\\S]{5,}$");
        int pos = searchProductbyID(updateID);

        if (pos >= 0) {

            
            do {
                updateName = UpdateValid.updateString("Old Product name: " + this.get(pos).getProductName() + " | New name: ", "The Product Name must be at least five character and no spaces", "[\\S]{5,}$");
                if (checkDupplicateByName(updateName)) {
                    System.err.println(updateName + " is exist");
                }
            } while (checkDupplicateByName(updateName));
            if (UpdateValid.checkStringUpdate(updateName)) {
                updateName = MyToys.toUpperFirstLetter(updateName);
                this.get(pos).setProductName(updateName);
            }

            updateUnitPrice = UpdateValid.updateDouble("Old Unit Price: " + this.get(pos).getUnitPrice() + " | New Unit Price: ", "UnitPrice must in range " + 0 + " to " + 10000 + " !!!", 0, 10000);
            if (UpdateValid.checkDoubleUpdate(updateUnitPrice)) {
                this.get(pos).setUnitPrice(updateUnitPrice);
            }

            updateQuantity = UpdateValid.updateInteger("Old Quanity: " + this.get(pos).getQuantity() + " | New Quanity: ", "Quantity must in range " + 0 + " to " + 1000 + " !!!", 0, 1000);
            if (UpdateValid.checkIntegerUpdate(updateQuantity)) {
                this.get(pos).setQuantity(updateQuantity);
            }

            System.out.println("    Status:");
            System.out.println("    0. Not Available");
            System.out.println("    1. Available");
            System.out.println("    2. No update");

            int choice = MyToys.getAnInteger("Your choose: ", "Please choose 0 to 2\n", 0, 2);
            if (choice == 0) {
                updateStatus = "Not Available";
            } else if (choice == 1) {
                updateStatus = "Available";
            } else {
                updateStatus = this.get(pos).getStatus();
            }

            this.get(pos).setStatus(updateStatus);
            System.err.println("Update Successfully!!");
        } else {
            System.err.println("Product ID not found!!!");
            return;
        }
    }

    public void deleteProduct() {
        String deleteID;

        deleteID = MyToys.getStringbyFormat("Input Product ID you want to delete: ", "The Product ID must be at least five character and no spaces", "[\\S]{5,}$");
        int pos = searchProductbyID(deleteID);

        if (pos >= 0) {
            this.remove(pos);
            System.err.println("Delete Success!!");
        } else {
            System.err.println("Delete Fail!!");
            return;
        }
    }

    @Override
    public void UpdateAndDelete() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        System.out.println("    Update Product:");
        System.out.println("    1. Update Product information");
        System.out.println("    2. Delete Product information");
        System.out.println("    3. Quit");

        int choice = MyToys.getAnInteger("Your choose: ", "Please choose 1 to 3\n", 1, 3);
        if (choice == 1) {
            updateProduct();
        } else if (choice == 2) {
            deleteProduct();
        } else {
            System.err.println("Quit menu");
            return;
        }
    }

    public boolean goBackMenu() {
        String choice = MyToys.getChoose("Do you want to continue(Y/N): ", "Y", "N");
        if (choice.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }

    private ArrayList<Product> readFromFilel(String fileName) throws IOException {
        ArrayList<Product> productList = new ArrayList<>();

        Product p;
        File f = null;
        FileInputStream in = null;
        ObjectInputStream objInputStream = null;

        try {
            f = new File(fileName);
            if (!f.exists()) {
                System.out.println("File not exist");
                return null;
            }
            in = new FileInputStream(fileName);
            objInputStream = new ObjectInputStream(in);

            while ((p = (Product) (objInputStream.readObject())) != null) {
                productList.add(p);
            }

        } catch (Exception e) {
        } finally {
            if (in != null) {
                in.close();
            }

            if (objInputStream != null) {
                objInputStream.close();
            }
        }
        return productList;
    }

    public void readFromFile(String fileName) throws IOException {

        Product p;
        File f = null;
        FileInputStream in = null;
        ObjectInputStream objInputStream = null;

        try {
            f = new File(fileName);
            if (!f.exists()) {
                System.out.println("File not exist");
                return;
            }
            in = new FileInputStream(fileName);
            objInputStream = new ObjectInputStream(in);

            while ((p = (Product) (objInputStream.readObject())) != null) {
                if (!checkDupplicateByID(p.getProductID()))
                this.add(p);
            }
            System.out.println("Loading successful");

        } catch (Exception e) {
        } finally {
            if (in != null) {
                in.close();
            }

            if (objInputStream != null) {
                objInputStream.close();
            }
        }
        
    }

    public void saveFromFile(String fileName) throws IOException {
        
        FileOutputStream out = null;
        ObjectOutputStream objOutputStream = null;
        try {
            out = new FileOutputStream(fileName);
            objOutputStream = new ObjectOutputStream(out);

            for (Product x : this) {
                if (!checkDupplicatedByIDFromFile(x.getProductID(), fileName))
                objOutputStream.writeObject(x);
            }
        } catch (Exception e) {
        } finally {
            if (out != null) {
                out.close();
            }

            if (objOutputStream != null) {
                objOutputStream.close();
            }
        }

        System.out.println("Save successfull");

    }

    private boolean checkDupplicatedByIDFromFile(String checkID, String fileName) throws IOException {

        List<Product> listProduct = readFromFilel(fileName);
        for (Product x : listProduct) {
            if (x.getProductID().equalsIgnoreCase(checkID)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDupplicatedByNameFromFile(String checkName, String fileName) throws IOException {

        List<Product> listProduct = readFromFilel(fileName);

        for (Product x : listProduct) {
            if (x.getProductName().equalsIgnoreCase(checkName)) {
                return true;
            }
        }
        return false;
    }

    public int countAvaibleProduct() {
        int count = 0;
        for (Product x : this) {
            if (x.getStatus().equalsIgnoreCase("Available")) {
                count++;
            }
        }
        return count;
    }

    public void printAvailableProduct() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }
        System.out.println("There is/are " + countAvaibleProduct() + " 'Available' Product in list: ");
        System.out.println("|Product ID     |Product Name   |Unit Price |Quantity |Status");
        for (Product x : this) {
            if (x.getStatus().equalsIgnoreCase("Available")) {
                System.out.print(x);
            }
        }
    }

    public void printUnAvailableProduct() {
        if (this.isEmpty()) {
            System.err.println("Have no any Product");
            return;
        }

        System.out.println("There is/are " + (this.size() - countAvaibleProduct()) + " 'Not Available' Product in list: ");
        System.out.println("|Product ID     |Product Name   |Unit Price |Quantity |Status");
        for (Product x : this) {
            if (!x.getStatus().equalsIgnoreCase("Available")) {
                System.out.print(x);
            }
        }
    }
    
    
    private int searchByRangePrice(double a, double b) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getUnitPrice() >= a && this.get(i).getUnitPrice() <= b)
                return i;
        }
        return -1;
    }
    
    public void searchByRangePrice(){
        System.out.println("SEARCH PRICE IN RANGE");
        double a = MyToys.getDouble("From: ", "Must be positive number", 0, Double.MAX_VALUE);
        double b = -1;
        do {
            b = MyToys.getDouble("To: ", "Must be >= " + a);
        } while (b <= a);
        
        int pos = searchByRangePrice(a, b);
        if (pos >= 0) {
            System.out.println("Here the list:");
            System.out.println("|Product ID     |Product Name   |Unit Price |Quantity |Status");
            for (Product x : this) {
                if (x.getUnitPrice() >= a && x.getUnitPrice() <= b)
                        System.out.println(x);
            }
        } else 
            System.out.println("Not found any Product Price in range " + a + "..." + b);
    }
    
    
    private int searchByRangeQuantity(int a, int b) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getQuantity() >= a && this.get(i).getQuantity() <= b)
                return i;
        }
        return -1;
    }
    
    public void searchByRangeQuantity(){
        System.out.println("SEARCH QUANTITY IN RANGE");
        int a = MyToys.getAnInteger("From: ", "Must be positive number", 0, Integer.MAX_VALUE);
        int b = -1;
        do {
            b = MyToys.getAnInterger("To: ", "Must be >= " + a);
        } while (b <= a);
        
        int pos = searchByRangeQuantity(a, b);
        if (pos >= 0) {
            System.out.println("Here the list:");
            System.out.println("|Product ID     |Product Name   |Unit Price |Quantity |Status");
            for (Product x : this) {
                if (x.getQuantity() >= a && x.getQuantity() <= b)
                        System.out.println(x);
            }
        } else 
            System.out.println("Not found any Product Quantity in range " + a + "..." + b);
    }
    
    
//    private static String getString(String inputMsg, String errorMsg) {
//        String id;
//        Scanner sc  = new Scanner(System.in);
//        
//        while (true) {
//            System.out.print(inputMsg);
//            id = sc.nextLine().trim();
//            if (id.length() == 0 || id.isEmpty()) {
//                System.out.println(errorMsg);
//            } else {
//                return id;
//            }
//        }
//    }
//    
//    public void searchSalary(int salary) throws IOException {
//        //input 
//        String path = getString("Enter fullpath of the text file: ", "Must no empty");
//        Product p;
//        File f= null;
//        FileInputStream in = null;
//        ObjectInputStream objInputStream = null;
//
//        try {
//            f = new File(path);
//            if (!f.exists()) {
//                System.out.println("File not exist");
//                return;
//            }
//            in = new FileInputStream(path);
//            objInputStream = new ObjectInputStream(in);
//
//            while ((p = (Product) (objInputStream.readObject())) != null) {
//                if (p.getSalary() == salary)
//                {
//                    System.out.println("Found!!");
//                    System.out.println("Have " + salary);
//                } 
//            }
//            System.out.println("Loading successful");
//
//        } catch (Exception e) {
//        } finally {
//            if (in != null) {
//                in.close();
//            }
//
//            if (objInputStream != null) {
//                objInputStream.close();
//            }
//        }
        
    

}
