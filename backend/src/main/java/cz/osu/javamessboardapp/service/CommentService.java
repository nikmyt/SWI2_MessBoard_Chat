package cz.osu.javamessboardapp.service;

import cz.osu.javamessboardapp.model.BoardComment;

import cz.osu.javamessboardapp.repository.CommentRepository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CommentService implements CommentRepository
{
    @Autowired
    private final CommentService commentService;

    @Override
    public BoardComment save(BoardComment comment) {
        return commentService.save(comment);
    }

    @Override
    public BoardComment findOne(Integer primaryKey) {
        return commentService.findOne(primaryKey);
    }

    @Override
    public List<BoardComment> findAll() {
        return (List<BoardComment>) commentService.findAll();
    }



    @Override
    public List<BoardComment> fetchBCommentsByBPost(Integer bPostID) {
        return commentService.fetchBCommentsByBPost(bPostID);
    }

    @Override
    public List<BoardComment> fetchBCommentsByBUser(Integer bUserID) {
        return commentService.fetchBCommentsByBUser(bUserID);
    }
    @Override
    public BoardComment updateBComment(BoardComment comment, Integer bCommentID) {
        BoardComment commentDB = commentService.findOne(bCommentID);
        if (Objects.nonNull(comment.getText()) && !"".equalsIgnoreCase(comment.getText())) {
            commentDB.setText(comment.getText());
        }



        return commentService.save(commentDB);
    }

    @Override
    public void delete(BoardComment bComment)
    {
        commentService.delete(bComment);

    }
    @Override
    public Long count()
    {
        //do we need this?
        return null;
    }
    @Override
    public boolean exists(Integer bCommentID)
    {
        if(findOne(bCommentID) != null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
