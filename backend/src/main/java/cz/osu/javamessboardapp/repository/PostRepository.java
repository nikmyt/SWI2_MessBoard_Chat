package cz.osu.javamessboardapp.repository;

import cz.osu.javamessboardapp.model.BoardPost;
import org.aspectj.apache.bcel.util.Repository;

import java.util.List;

public interface PostRepository extends CrudeRepositoryInterface<BoardPost, Integer>
{
    BoardPost save(BoardPost post);
    List<BoardPost> findAll();

    List<BoardPost> fetchBPostsByBUser(Integer bUserID);


    BoardPost updateBPost(BoardPost post, Integer bPostID);


    void delete(BoardPost bPost);

    List<BoardPost> findByTag(String tag);

}
