package cz.osu.java.messboardapp.repository;

import cz.osu.java.messboardapp.model.BoardComment;

import java.util.List;

public interface CommentRepository extends CrudeRepositoryInterface<BoardComment, Integer>
{


    BoardComment save(BoardComment comm);

}
