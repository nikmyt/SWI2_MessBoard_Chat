package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<BoardComment, Integer>
{
        //public Iterable<BoardComment> findBoardCommentByPostId(int postId);

        public Iterable<BoardComment> findBoardCommentByPost(BoardPost bPost);

        public Iterable<BoardComment> findBoardCommentsByUser(BoardUser bUser);

        public BoardComment findBoardCommentByComment_id(int id);

}