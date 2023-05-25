package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.Form.PostForm;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService
{

    private final PostRepository postRepo;

    public PostService(PostRepository postRepository)
    {
        this.postRepo=postRepository;
    }

    public void save(PostForm newPost, BoardUser user)
    {
        //previously idealistic solution incompatible with out cruel world (or with my shitty code)
        BoardPost post = new BoardPost();

        post.setCreatedAt(Calendar.getInstance().getTime()); //watch out for locale
        post.setUser(user);
        post.setText(newPost.getText());
        post.setTitle(newPost.getTitle());
        post.setTag(newPost.getTag());
        post.setTemp(false); //temp temp

        //postRepo.save(post);
        //return post; //please let this be fix of dupli issue
    }

    public boolean update(int id, BoardPost updatePost) {
        Optional<BoardPost> postOptional = findByPostId(id); //??!
        if (postOptional.isPresent()) {
            BoardPost post = postOptional.get();
            post.setText(updatePost.getText());
            post.setTitle(updatePost.getTitle());
            post.setTag(updatePost.getTag());
            post.setTemp(updatePost.isTemp());
            return true;
        } else {
            return false;
        }
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
