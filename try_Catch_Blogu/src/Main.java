import java.util.Scanner;

public class Main {
    public static int indexiDondur(int[] arr, int index)  {
        if (index < 0 || index >= arr.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return arr[index];
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Scanner scan = new Scanner(System.in);
        System.out.print("Bir index numarası giriniz : ");
        int index = scan.nextInt();

        try {
            System.out.println("Seçilen indexteki sayı : " + indexiDondur(arr, index));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Hata: Geçersiz index, lütfen doğru bir index girin.");
        }


    }
}