import java.util.*;

public class Brand {
    private int ID;
    private String brandName;
    private static ArrayList<Brand> brands = new ArrayList<>();
    public Brand(int ID, String brandName, ArrayList<Brand> brandList) {
        this.ID = ID;
        this.brandName = brandName;
        this.brands = brandList;
    }

    public Brand(String brandName){
        this.ID=brands.size()+1;
        this.brandName=brandName;
    }

    static {
        brands.add(new Brand("Samsung"));
        brands.add(new Brand("Lenovo"));
        brands.add(new Brand("Apple"));
        brands.add(new Brand("Huawei"));
        brands.add(new Brand("Casper"));
        brands.add(new Brand("Asus"));
        brands.add(new Brand("HP"));
        brands.add(new Brand("Xiaomi"));
        brands.add(new Brand("Monster"));
    }


    public int getID() {
        return ID;
    }

    public String getBrandName() {
        return brandName;
    }
    public static void printBrands() {
        Collections.sort(brands,Comparator.comparing(Brand::getBrandName));
        for (Brand b : brands) {
            System.out.println(b.getBrandName()+" "+"Sistemdeki ID karşılığı : "+b.getID());
        }
    }


}
