package cz.osu.javamessboardapp.repository;

import cz.osu.javamessboardapp.model.BoardComment;
import cz.osu.javamessboardapp.model.BoardPost;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface CommentRepository extends CrudeRepositoryInterface<BoardComment, Integer>
{
    BoardComment saveBComment(BoardComment comment);
    List<BoardComment> fetchBComment();

    List<BoardComment> fetchBCommentsByBPost(Integer bPostID);

    List<BoardComment> fetchBCommentsByBUser(Integer bUserID);
    BoardComment updateBComment(BoardComment comment, Long bCommentID);

    void deleteBComment(Long bCommentID);
}
