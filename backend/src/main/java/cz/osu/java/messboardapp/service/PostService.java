package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService
{

    private final PostRepository postRepo;

    public PostService(PostRepository postRepository)
    {
        this.postRepo=postRepository;
    }

    public BoardPost save(BoardPost newPost)
    {
        postRepo.save(newPost);
        return newPost;
    }

    public Iterable<BoardPost> findAll()
    {

        return postRepo.findAll();
    }

    public Optional<BoardPost> findByPostId(Integer id)
    {
        return postRepo.findById(id);
    }
}
