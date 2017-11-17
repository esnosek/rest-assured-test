package app;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksDataProvider {

    @DataProvider(name = "books")
    public static Object[] books() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", "Koziolek Matolek");
        book.put("author", "Kornel Makuszynski");
        book.put("year", 1980);
        return new Object[] { book };
    }

    @DataProvider(name = "books2")
    public static Object[] books2() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", "AAAA");
        book.put("author", "xyz");
        book.put("year", 1980);
        return new Object[] { book };
    }

    @DataProvider(name = "books1")
    public static Object[] books1() {
        Map<String, Object> book1 = new HashMap<>();
        book1.put("title", "Krzyzacy");
        book1.put("author", "Sienkiewicz");
        book1.put("year", 1880);
        Map<String, Object> book2 = new HashMap<>();
        book2.put("title", "Quo Vadis");
        book2.put("author", "Sienkiewicz");
        book2.put("year", 1880);
        Map<String, Object> book3 = new HashMap<>();
        book3.put("title", "Dziady");
        book3.put("author", "Mickiewicz");
        book3.put("year", 1880);
        return new Object[] { book1, book2, book3 };
    }

    @DataProvider(name = "titles")
    public static Object[] expectedTitles() {
        List<String> titles = Arrays.asList("Krzyzacy", "Quo Vadis");
        return new Object[] { titles };
    }
}
