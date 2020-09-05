package pl.camp.it.library.dao.impl.test;


import org.springframework.stereotype.Repository;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.model.User;

@Repository
public class UserDAOStub implements IUserDAO { // testowa klasa Stub, prawdziwe obiekty udajace obiekty DAO

    @Override
    public User getUserByLogin(String login) {
        User user = new User();
        user.setId(4);
        user.setLogin("mateusz");
        user.setPassword("asdasdasfdadfdf");

        return user;
    }

    @Override
    public void addUser(User user) {

    }
}
