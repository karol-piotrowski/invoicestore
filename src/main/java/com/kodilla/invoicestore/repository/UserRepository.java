package com.kodilla.invoicestore.repository;

import com.kodilla.invoicestore.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Override
    User save(User user);

    @Override
    void deleteById(Long userId);

    @Override
    Optional<User> findById(Long userId);
}
