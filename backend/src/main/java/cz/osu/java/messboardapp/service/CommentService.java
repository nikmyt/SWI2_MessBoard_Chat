package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService
{
    public final CommentRepository commrep;



    public CommentService(CommentRepository commentRepository)
    {
        this.commrep=commentRepository;
    }

    public BoardComment findCommentByPostId(int postId)
    {
        return commrep.findBoardCommentByPostId(postId);
    }


}
