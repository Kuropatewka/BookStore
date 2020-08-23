package pl.camp.it.library.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.library.model.Author;
import pl.camp.it.library.model.Book;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IBookService;
import pl.camp.it.library.services.IUserService;

import javax.jws.soap.SOAPBinding;

@Controller
@RequestMapping(value = "/admin/utils") //caly kontroler mapowany pod ta nazwa
public class AdminController {

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET) // to sie nazywa end point
    public String addUser() {
        User user = new User();
        user.setLogin("admin");
        String hashedPassword = DigestUtils.md5Hex("admin");
        user.setPassword(hashedPassword);

        userService.addUser(user);

        return "redirect:/login";
    }

    @RequestMapping(value = "/addBooks", method = RequestMethod.GET)
    public String addBooks() {
        Book book1 = new Book();
        book1.setIsbn("123123124123");
        book1.setTitle(" Sekrety włoskiej kuchnii");
        book1.setCategory(Book.Category.COOKING);

        Author author1 = new Author();
        author1.setName(" Elena");
        author1.setSurname(" Kostiukovitch");

        book1.setAuthor(author1);

        Book book2 = new Book();
        book2.setIsbn("2342342354234234");
        book2.setTitle(" Sekrety polskiej kuchnii");
        book2.setCategory(Book.Category.COOKING);

        Author author2 = new Author();
        author2.setName(" Jeffrey");
        author2.setSurname(" Hamelmann");

        book2.setAuthor(author2);

        Book book3 = new Book();
        book3.setIsbn("12323423434");
        book3.setTitle(" Sekrety chorwackiej kuchnii");
        book3.setCategory(Book.Category.COOKING);

        Author author3 = new Author();
        author3.setName(" Piotr");
        author3.setSurname(" Srna");

        book3.setAuthor(author3);

        Book book4 = new Book();
        book4.setIsbn("3242342342");
        book4.setTitle(" Moje wypieki i desery");
        book4.setCategory(Book.Category.BAKING);

        Author author4 = new Author();
        author4.setName(" Dorota");
        author4.setSurname(" Piotrowska");

        book4.setAuthor(author4);

        Book book5 = new Book();
        book5.setIsbn("23423423423423423");
        book5.setTitle(" Moje wypieki i desery dla dzieci");
        book5.setCategory(Book.Category.BAKING);

        book5.setAuthor(author4);

        this.bookService.addBook(book1);
        this.bookService.addBook(book2);
        this.bookService.addBook(book3);
        this.bookService.addBook(book4);
        this.bookService.addBook(book5);

        return "redirect:/main";
    }
}
