package maluevartem.springboot_rest.repository;

import maluevartem.springboot_rest.model.Authorities;
import maluevartem.springboot_rest.model.User;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {

    private final Map<User, List<Authorities>> listUser;
    private final List<Authorities> listAuthorities;

    public UserRepository() {
        listUser = new ConcurrentHashMap<>();
        listAuthorities = new ArrayList<>();

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listUser.put(new User(properties.getProperty("USER"), properties.getProperty("PASSWORD")),
                Arrays.asList(Authorities.values()));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        for (Map.Entry<User, List<Authorities>> entry : listUser.entrySet()) {
            if (entry.getKey().getName().equals(user) && entry.getKey().getPassword().equals(password)) {
                return entry.getValue();
            }
        }
        return listAuthorities;
    }

}
