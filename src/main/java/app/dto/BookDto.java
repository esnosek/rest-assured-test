package app.dto;

public class BookDto {

    private String title;
    private String author;
    private int year;

    public BookDto(){
    }

    public BookDto(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}