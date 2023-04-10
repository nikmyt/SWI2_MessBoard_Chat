package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardPost;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudeRepositoryInterface<BoardPost, Integer>
{



    boolean existsByPostId(Integer primaryKey);

    BoardPost findByPostId(Integer id);
}
