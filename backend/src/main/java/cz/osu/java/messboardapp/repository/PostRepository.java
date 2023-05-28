package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<BoardPost, Integer>
{

        public ArrayList<BoardPost> findBoardPostByTag(String tag);

        public ArrayList<BoardPost> findAll();

        public List<BoardPost> findBoardPostByTitleContainingIgnoreCase(String title);

        public List<BoardPost> findBoardPostByTagContainingIgnoreCase(String tag);

        public Iterable<BoardPost> findBoardPostByUser(BoardUser bUser);

        public BoardPost findBoardPostByPostId(int postId);


        public int countAllByUser(BoardUser bUser);





}
