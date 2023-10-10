import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        TreeSet<Book> booksSetByName = new TreeSet<>();

        booksSetByName.add(new Book("Deniz",256,"lale","12-12-2017"));
        booksSetByName.add(new Book("Ahmet",400,"ahmet","12-12-2018"));
        booksSetByName.add(new Book("Osman",800,"ayşe","12-12-2016"));
        booksSetByName.add(new Book("Beril",390,"mehmet","12-12-2019"));
        booksSetByName.add(new Book("Cemal",600,"veysel","12-12-2020"));

        System.out.println("--- Kitapları isimleriyle sıralama ---");

        for(Book book : booksSetByName){
            System.out.println(book);
        }

        System.out.println("-------------------------------------------------------------------------------------");

        System.out.println("--- Kitapları sayfa sayılarıyla sıralama ---");

        TreeSet<Book> booksSetByNumber = new TreeSet<>(new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2){
                return b1.getPageCount()-b2.getPageCount();
            }

        });

        booksSetByNumber.addAll(booksSetByName);

        for(Book book: booksSetByNumber){
            System.out.println(book);
        }





    }
}