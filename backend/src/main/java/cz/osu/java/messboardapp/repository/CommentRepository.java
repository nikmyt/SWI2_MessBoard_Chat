package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<BoardComment, Integer>
{
        public Iterable<BoardComment> findBoardCommentByPost(BoardPost bPost);

        public Optional<BoardComment> findBoardCommentById(int id);

        public int countAllByUser(BoardUser bUser);
}