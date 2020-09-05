package pl.camp.it.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

// metody inline - w kodzie to jest jako funckja, lecz fizycznie metoda zostanie usunieta, i dopisany zostanie kod z tej metody
// java robi to sama i sama decyduje kiedy zrobic metode inline a kiedy nie, a sami tego nie mozemy wymusic, w JVM takie cos istnieje i to sie zdarza
// mechhanizm jest pod kontrola witrualnej maszyny JAVY a JVM czyta bytecode

// zapytania zagniezdzone SQL - moeze byc select w seleckie