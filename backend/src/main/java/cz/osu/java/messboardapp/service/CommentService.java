package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.model.BoardComment;

import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.repository.CommentRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class CommentService
{
    public final CommentRepository commrep;


    public CommentService(CommentRepository commentRepository)
    {
        this.commrep=commentRepository;
    }


}
