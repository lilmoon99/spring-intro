package ru.lilmoon.demo_spring_boot;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
@Component
public class UserRepository {

private final List<User> users;

    public UserRepository(List<User> users) {
        this.users = users;
        users.add(new User("User #1"));
        users.add(new User("User #2"));
        users.add(new User("User #3"));
        users.add(new User("User #4"));
        users.add(new User("User #5"));
        users.add(new User("User #6"));
    }

    public List<User> getAll(){
        return List.copyOf(users);
    }

    public User getById(long id){
        return users.stream().filter(it -> Objects.equals(it.getId(),id)).findFirst().orElse(null);
    }

    public User getByName(String name){
        return users.stream().filter(it -> Objects.equals(it.getName(),name)).findFirst().orElse(null);
    }
}
