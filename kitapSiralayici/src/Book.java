public class Book implements Comparable<Book>{
    private String name ;
    private int pageCount;
    private String authorName;
    private String publicationDate;


    public Book(String name, int pageCount, String outhorName, String publicationDate) {
        this.name = name;
        this.pageCount = pageCount;
        this.authorName = outhorName;
        this.publicationDate = publicationDate;
    }

    public String getName() {
        return name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    @Override
    public int compareTo(Book o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Book{" +
                "'Title:'" + name + '\'' +
                ", Page Count:" + pageCount +
                ", Author:'" + authorName + '\'' +
                ", Publication Date:'" + publicationDate + '\'' +
                '}';
    }
}
