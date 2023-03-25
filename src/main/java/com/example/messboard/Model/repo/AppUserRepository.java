package com.example.messboard.Model.repo;

import com.example.messboard.Model.db.BoardUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<BoardUser, Integer>
{
    boolean existsByUsernameIgnoreCase(String username);
    BoardUser findAppUserByUsername(String username);
}
