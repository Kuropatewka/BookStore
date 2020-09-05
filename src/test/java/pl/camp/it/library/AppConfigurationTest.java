package pl.camp.it.library;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.library.dao.IAuthorDAO;
import pl.camp.it.library.dao.IBookDAO;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.dao.impl.test.AuthorDAOStub;
import pl.camp.it.library.dao.impl.test.BookDAOStub;
import pl.camp.it.library.dao.impl.test.UserDAOStub;
import pl.camp.it.library.session.SessionObject;

@Configuration
@ComponentScan(basePackages =
        {
                "pl.camp.it.library.controllers",
                "pl.camp.it.library.services",
                //"pl.camp.it.library.dao.impl.test" nie chcemy już tych stabów, tylko mockito czyli imitacje obiektow
})
public class AppConfigurationTest { // Stuby zwracajca gotowe obiekty w ramach tekstu, Mock - imitacja obiektów, chcemy udawac ze mamy obiekt i na jakich
    // warunkach ma dzialac

    /*@Bean
    public IUserDAO userDAO() {
        return Mockito.mock(IUserDAO.class); // ma byc obiekt który udaje IUserDAO ale nie musi nic z nim miec wspolnego
    }


    @Bean
    public IBookDAO bookDAO() {
        return Mockito.mock(IBookDAO.class);  // jesli mamy 50 klas to musimy zrobic 50 takich beanow
    }

    @Bean
    public IAuthorDAO authorDAO() {
        return Mockito.mock(IAuthorDAO.class);
    }*/

    @Bean // bean krory jest singletonem w ramach naszej sesji, wzgledem kazdego uzytkownika
    @SessionScope // obejmuje pojedyncza sesje a nie cala aplikacje, kazda sesja ma swoj egzemplarz takiego obiektu
    public SessionObject sessionObject() {
        return new SessionObject();
    }
}
