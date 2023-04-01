package cz.osu.javamessboardapp.repository;

import cz.osu.javamessboardapp.model.BoardPost;
import cz.osu.javamessboardapp.model.BoardUser;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<BoardUser, Integer>
{
    boolean existsByUsernameIgnoreCase(String username);
    BoardUser findAppUserByUsername(String username);

    BoardUser saveBUser(BoardUser user);
    List<BoardUser> fetchBUsers();
    BoardUser upadateBUser(BoardUser user, Long bUserID);

    void deleteBUser(Long bUserID);



}
