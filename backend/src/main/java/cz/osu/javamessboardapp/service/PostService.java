package cz.osu.javamessboardapp.service;

import cz.osu.javamessboardapp.model.BoardPost;
import cz.osu.javamessboardapp.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PostService implements PostRepository
{
    @Autowired
    private final PostService postService;

    @Override
    public BoardPost save(BoardPost boardPost) {
        return postService.save(boardPost);
    }

    @Override
    public BoardPost findOne(Integer primaryKey) {
        return postService.findOne(primaryKey);
    }

    @Override
    public List<BoardPost> findAll() {
        return (List<BoardPost>) postService.findAll();
    }

    @Override
    public List<BoardPost> fetchBPostsByBUser(Integer bUserID) {
        return postService.fetchBPostsByBUser(bUserID);
    }

    @Override
    public List<BoardPost> findByTag(String tag) {
        return postService.findByTag(tag);
    }

    @Override
    public BoardPost updateBPost(BoardPost post, Integer bPostID) {
        BoardPost postDB = postService.findOne(bPostID);
        if (Objects.nonNull(post.getTitle()) && !"".equalsIgnoreCase(post.getTitle())) {
            postDB.setTitle(post.getTitle());
        }

        if (Objects.nonNull(post.getText()) && !"".equalsIgnoreCase(post.getText())) {
            postDB.setText(post.getText());
        }

        if (Objects.nonNull(post.getTag()) && !"".equalsIgnoreCase(post.getTag()))
        {
            postDB.setTag(post.getTag());
        }


        return postService.save(postDB);
    }

    @Override
    public void delete(BoardPost bPost)
    {
        postService.delete(bPost);

    }
    @Override
    public Long count()
    {

        //do we need this?
        return null;
    }
    @Override
    public boolean exists(Integer bPostID)
    {
        if(findOne(bPostID) != null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }




}
