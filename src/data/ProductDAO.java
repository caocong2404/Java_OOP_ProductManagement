package data;

import java.io.IOException;

public interface ProductDAO {
    public void addProduct();
    public void printProduct();
    public void printProductFromFile();
    public void checkProductExistV2() throws IOException;
    public void searchProductbyPartOfName();
    public void UpdateAndDelete();
}
