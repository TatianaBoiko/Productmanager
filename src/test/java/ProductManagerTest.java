import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductManagerTest {
    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);
    Product one = new Book(1, "Прыжок", 199, "Сергей Лукьяненко");
    Product two = new Book(2, "Физика невозможного", 389, "Митио Каку");
    Product tree = new Book(3, "Ход королевы", 599, "Уолтер Тевис");
    Product four = new Smartphone(4, "Iphone14ProMax", 113900, "Apple");
    Product fife = new Smartphone(5, "Iphone12", 64990, "Apple");
    Product six = new Smartphone(6, "Xiaomi12Pro", 89990, "Xiaomi");
    Product seven = new Product(7, "Universal product", 1);

    @BeforeEach
    public void addProducts() {
        manager.add(one);
        manager.add(two);
        manager.add(tree);
        manager.add(four);
        manager.add(fife);
        manager.add(six);
        manager.add(seven);
    }

    @Test
    public void addProductsAndGetIts() {
        Product[] expected = new Product[]{one, two, tree, four, fife, six, seven};
        Product[] actual = repo.getItems();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchBySmartphoneFewName() {
        Product[] results = manager.searchBy("Iphone");

        Product[] expected = new Product[]{four, fife};
        Assertions.assertArrayEquals(expected, results);
    }

    @Test
    public void SearchByNoExistingSmartphoneName() {
        Product[] results = manager.searchBy("Samsung");

        Product[] expected = new Product[0];
        Assertions.assertArrayEquals(expected, results);
    }

    @Test
    public void SearchByExistingBookName() {
        Product[] results = manager.searchBy("Ход королевы");

        Product[] expected = new Product[]{tree};
        Assertions.assertArrayEquals(expected, results);
    }

    @Test
    public void SearchByNoExistingBookName() {
        Product[] results = manager.searchBy("Ведьмак");

        Product[] expected = new Product[0];
        Assertions.assertArrayEquals(expected, results);
    }

    @Test
    public void SearchByTextName() {
        Product[] results = manager.searchBy("Universal");

        Product[] expected = new Product[]{seven};
        Assertions.assertArrayEquals(expected, results);
    }

}

