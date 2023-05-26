package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<BoardPost, Integer>
{

        public ArrayList<BoardPost> findBoardPostByTag(String tag);

        public ArrayList<BoardPost> findAll();

}
