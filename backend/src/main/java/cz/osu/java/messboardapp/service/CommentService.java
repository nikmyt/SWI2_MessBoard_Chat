package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService
{
    public final CommentRepository commrep;

    private AppUserRepository userRepository;



    public CommentService(CommentRepository commentRepository)
    {
        this.commrep=commentRepository;
    }

    public Iterable<BoardComment> findCommentByPostId(int postId)
    {
        return commrep.findBoardCommentByPostId(postId);
    }

    public Iterable<BoardComment> findCommentsByPostId(BoardPost bPost)
    {
        return commrep.findBoardCommentsByPostId(bPost);
    }

    public int getPostCountByUserId(int id)
    {
        BoardUser bUser = userRepository.findBoardUserByUserId(id);
        ArrayList<BoardComment> posts = (ArrayList<BoardComment>) commrep.findBoardCommentsByUser(bUser);
        return posts.size();
    }

}
