import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    private ProductRepository repo = new ProductRepository();
    private ProductManager manager = new ProductManager(repo);

    Product one = new Book (1, "Спин техника", 199, "Солженицын");
    Product two = new Book(2, "Физика невозможного", 389, "Митио Каку");
    Product tree = new Book(3, "Ход королевы", 599, "Уолтер Тевис");
    Product four = new Smartphone(4, "Iphone14ProMax", 113900, "Apple");
    Product fife = new Smartphone(5, "Iphone12", 64990, "Apple");
    Product six = new Smartphone(6, "Xiaomi12Pro", 89990, "Xiaomi");
    Product seven = new Product(7, "Universal product", 1);

    @BeforeEach

    public void addProducts() {
        repo.save(one);
        repo.save(two);
        repo.save(tree);
        repo.save(four);
        repo.save(fife);
        repo.save(six);
        repo.save(seven);
    }
    @Test
    public void addProductsAndFindAll() {
        Product[] expected = new Product[]{one, two, tree, four, fife, six, seven};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void findById() {
        Product expected = tree;
        Product actual = repo.findById(3);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void removeById() {
        repo.removeById(2);
        repo.removeById(7);

        Product[] expected = {one, tree, four, fife, six};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void findByUnCorrectId() {
        Product actual = repo.findById(10);
        Assertions.assertNull(actual);
    }
    @Test
    public void shouldDeleteProduct() {
        repo.removeById(four.getId());

        Product[] expected = {one, two, tree, fife, six, seven};
        Product[] actual = repo.getItems();
        Assertions.assertArrayEquals(expected, actual);
    }

}
