package pl.camp.it.library;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import pl.camp.it.library.session.SessionObject;

@Configuration
@ComponentScan("pl.camp.it.library")
public class AppConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory(); //zwraca nam się egzemplarz tej klasy, z pliku konfiguracyjnego ta sesja
    }

    @Bean // bean krory jest singletonem w ramach naszej sesji, wzgledem kazdego uzytkownika
    @SessionScope // obejmuje pojedyncza sesje a nie cala aplikacje, kazda sesja ma swoj egzemplarz takiego obiektu
    public SessionObject sessionObject() {
        return new SessionObject();
    }
}
