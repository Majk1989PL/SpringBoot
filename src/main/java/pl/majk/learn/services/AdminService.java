package pl.majk.learn.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.majk.learn.entity.User;

import java.util.List;

public interface AdminService {

    Page<User> findAll(Pageable pageable);
    User findUserById(int id);
    void updateUser(int id, int nrRoli, int activity);
    Page<User> findAllSearch(String param, Pageable pageable);
    void insertInBatch(List<User> userList);
    void saveAll(List<User> userList);
    void deleteUserById(int id);
}
