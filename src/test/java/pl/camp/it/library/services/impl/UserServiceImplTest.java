package pl.camp.it.library.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.camp.it.library.AppConfiguration;
import pl.camp.it.library.AppConfigurationTest;
import pl.camp.it.library.dao.IAuthorDAO;
import pl.camp.it.library.dao.IBookDAO;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class) // kiedy obiekt nam przeszkadza, np DAO bo korzysta z bazy to imitujemy obiekt
@ContextConfiguration(classes = {AppConfigurationTest.class}) // pudeleczko
public class UserServiceImplTest {

    @Autowired
    IUserService userService;

    @MockBean  // zamiast w klasie konfiguracyjnej to tworze to w klasie testowej, wtedy korzysta z referencji
    IUserDAO userDAO;

    @MockBean // nie mozemy statycznie zamockowac beana, musimy to zrobic dynamicznie
    IBookDAO bookDAO;

    @MockBean
    IAuthorDAO authorDAO;

    @Before
    public void setUpMocks() {

        User user = new User();
        user.setId(5);
        user.setLogin("mateusz");
        user.setPassword("adadsadsas");

        Mockito.when(this.userDAO.getUserByLogin("mateusz")).thenReturn(user); // jeśli ktos na tym udawanym obiekcie zawola taka metode to zwroc mu w tym przypadku
        // usera podanego wyzej, oon jest udawany w naszej bazie

        user = new User();
        user.setId(5);
        user.setLogin("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");

       /* Mockito.when(this.userDAO.getUserByLogin(anyString())).thenReturn(new User());*/

        Mockito.when(this.userDAO.getUserByLogin("admin")).thenReturn(user); // jesli podam to samo tylko to on to nadpisze

        Mockito.when(this.userDAO.getUserByLogin("badLogin")).thenReturn(null); // symuluje zachowanie prawdziwego zdarzenia
    }

    @Test
    public void wrongPasswordAuthenticationTest() {
        /*Mockito.when(this.userDAO.getUserByLogin(anyString())).thenReturn(generateUser("matuesz", "adasdadasd", 5));*/

        User user = new User(); // to jest w formularzu ze stronki co jest wpisywane

        user.setLogin("mateusz");
        user.setPassword("asdasdasddas");

        boolean result = userService.authenticate(user);

        Assert.assertFalse(result);
    }

    @Test
    public void correctAuthenticationTest() {
        /*Mockito.when(this.userDAO.getUserByLogin(anyString())).thenReturn(generateUserAndHashPassword("admin", "admin", 5));*/

        User user = new User();

        user.setLogin("admin");
        user.setPassword("admin");

        boolean result = userService.authenticate(user);

        Assert.assertTrue(result);
    }

    @Test
    public void wrongLoginAuthenticationTest() {
        User user = new User();
        user.setLogin("badLogin");
        user.setPassword("admin");

        boolean result = userService.authenticate(user);

        Assert.assertFalse(result);
    }

    @Test
    public void wrongRepeatedPasswordDuringRegistrationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz");
        String repeatedPassword = "janusz2";

        boolean result = userService.registerUser(user, repeatedPassword);

        Assert.assertFalse(result);
    }

    @Test
    public void correctRepeatedPasswordDuringRegistrationTest() {
        User user = new User();
        user.setLogin("janusz");
        user.setPassword("janusz");
        String repeatedPassword = "janusz";

        boolean result = userService.registerUser(user, repeatedPassword);
        verify(this.userDAO, times(1)).addUser(any());

        Assert.assertTrue(result);
    }

    private User generateUser1() {
        User user = new User();
        user.setLogin("mateusz");
        user.setPassword("asdasdasdasd");
        user.setId(5);
        return user;
    }

    private User generateUser2() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("21232f297a57a5a743894a0e4a801fc3");
        user.setId(5);
        return user;
    }

    private User generateUser(String login, String hashedPassword, int id) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(hashedPassword);
        user.setId(id);
        return user;
    }

    private User generateUserAndHashPassword(String login, String pass, int id) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(DigestUtils.md5Hex(pass));
        user.setId(id);
        return user;
    }
}
