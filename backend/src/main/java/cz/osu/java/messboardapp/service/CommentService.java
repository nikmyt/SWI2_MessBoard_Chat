package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.model.BoardComment;

import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.repository.CommentRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService implements CommentRepository
{
    public final CommentRepository commrep;

    @Override
    public BoardComment save(BoardComment comm)
    {
        commrep.save(comm);
        return comm;
    }



    @Override
    public Iterable<BoardComment> findAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void delete(BoardComment entity) {

    }



}
