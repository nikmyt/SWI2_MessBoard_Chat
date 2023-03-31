package cz.osu.javamessboardapp.repository;

import cz.osu.javamessboardapp.model.BoardUser;


import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<BoardUser, Integer>
{
    boolean existsByUsernameIgnoreCase(String username);
    BoardUser findAppUserByUsername(String username);
}
