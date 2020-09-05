package pl.camp.it.library.services.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.library.AppConfigurationTest;
import pl.camp.it.library.dao.IAuthorDAO;
import pl.camp.it.library.dao.IBookDAO;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.model.Book;
import pl.camp.it.library.services.IBasketService;
import pl.camp.it.library.session.SessionObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfigurationTest.class)
public class BasketServiceImplTest {

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IAuthorDAO authorDAO;

    @Autowired
    IBasketService basketService;

    @Autowired
    SessionObject sessionObject;

    @Before
    public void setUpMocks() {

        Book book = new Book();
        book.setId(5);
        book.setCategory(Book.Category.BAKING);
        book.setAuthor(null);
        book.setTitle("testy jednostkowe");
        book.setIsbn("adasdasdasd");

        Mockito.when(this.bookDAO.getBookById(5)).thenReturn()
    }
    @Test
    public void addingUniqueBookToBasketTest() {
        int bookId = 5;
        int expectedResult = 1;

        this.basketService.addBookToBasket(5);

        int result = this.sessionObject.getBasket().size();

        Assert.assertEquals(expectedResult, result);
    }
}
