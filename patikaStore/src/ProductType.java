import java.util.ArrayList;

public class ProductType {
    private int ID;
    private String typeName;
    private static ArrayList<ProductType> productTypes = new ArrayList<>();

     static {
        productTypes.add(new Notebook());
        productTypes.add(new Phone());
    }

    public ProductType(String typeName) {
        this.ID = productTypes.size();
        this.typeName = typeName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static ArrayList<ProductType> getProductTypes() {
        return productTypes;
    }

    public static void setProductTypes(ArrayList<ProductType> productTypes) {
        ProductType.productTypes = productTypes;
    }
}
