
package data;

import java.io.Serializable;


public class Product implements Serializable {
    
    private String ProductID;
    private String ProductName;
    private double UnitPrice;
    private int Quantity;
    private String Status;

    public Product() {
    }

    public Product(String ProductID, String ProductName, double UnitPrice, int Quantity, String Status) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.Status = Status;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return String.format("|%-15s|%-15s|%-11.1f|%-9d|%-13s\n", ProductID, ProductName, UnitPrice, Quantity, Status);
    }
    
}
