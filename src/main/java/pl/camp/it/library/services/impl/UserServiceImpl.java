package pl.camp.it.library.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.library.dao.IUserDAO;
import pl.camp.it.library.model.User;
import pl.camp.it.library.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO; // Spring potrzebuje referencje i jesli je potrzebuje to ich uzywa

    @Override
    public boolean authenticate(User user) { // pokrycie testami 100%
        User userFromDataBase = userDAO.getUserByLogin(user.getLogin());

        if(userFromDataBase == null) {
            return false;
        }

        if (DigestUtils.md5Hex(user.getPassword()).equals(userFromDataBase.getPassword())) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void addUser(User user) { // testowanie tego nie ma sensu, bo testowalibysmy mocka
        this.userDAO.addUser(user);
    }

    @Override
    public boolean registerUser(User user, String repeatedPassword) {
        if(!user.getPassword().equals(repeatedPassword)) {
            return false;
        }

        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        this.userDAO.addUser(user); // pole z tej klasy, jest u gory
        return true;
    }
}
