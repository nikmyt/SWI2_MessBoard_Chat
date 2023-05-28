package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.Form.CommentForm;
import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommentService
{
    public final CommentRepository commrep;

    private AppUserRepository userRepository;


    public void save(CommentForm commentForm, BoardUser boardUser, BoardPost post)
    {
            BoardComment boardComment = new BoardComment();
            boardComment.setCreatedAt(commentForm.getCreatedAt()); //time is sorted in creation of CommentForm
            boardComment.setUser(boardUser);
            boardComment.setText(commentForm.getText());
            boardComment.setPost(post);

            commrep.save(boardComment);

    }

    public void deleteComment(BoardComment boardComment)
    {

        commrep.delete(boardComment);
    }

    public void update(BoardComment boardComment)
    {
        commrep.save(boardComment);
    }
    public CommentService(CommentRepository commentRepository)
    {
        this.commrep=commentRepository;
    }



    public Iterable<BoardComment> findCommentsByPostId(BoardPost bPost)
    {
        return commrep.findBoardCommentByPost(bPost);

    }

    public Optional<BoardComment> findBoardCommentByComment_id(int id)
    {
        return commrep.findBoardCommentById(id);
    }

    public int getCommentCountByUserId(BoardUser bUser)
    {
        return commrep.countAllByUser(bUser);
    }

    public long getCommentCount()
    {
        return commrep.count();
    }

}
