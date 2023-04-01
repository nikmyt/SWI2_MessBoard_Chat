package cz.osu.javamessboardapp.repository;

import cz.osu.javamessboardapp.model.BoardPost;
import org.aspectj.apache.bcel.util.Repository;

import java.util.List;

public interface PostRepository extends CrudeRepositoryInterface<BoardPost, Integer>
{
    BoardPost saveBPost(BoardPost post);
    List<BoardPost> fetchBPosts();

    List<BoardPost> fetchBPostsByBUser(Integer bUserID);

    List<BoardPost> fetchBPostsByTag(Integer bUserID);
    BoardPost updateBPost(BoardPost post, Long bPostID);

    void deleteBPost(Long bPostID);


}
