package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardUser;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<BoardUser, Integer>
{
    boolean existsByUsernameIgnoreCase(String username);
    public BoardUser findAppUserByUsername(String username);

    BoardUser save(BoardUser user);
    List<BoardUser> findAll();


    BoardUser findBoardUserByUserId(int userId);




}
