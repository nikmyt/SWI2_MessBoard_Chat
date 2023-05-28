package cz.osu.java.messboardapp.service;

import cz.osu.java.messboardapp.Form.PostForm;
import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class PostService
{

    private final PostRepository postRepo;
    private AppUserRepository userRepository;

    private CommentService commentService;

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

        postRepo.save(post);
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

    public BoardPost findByBPostId(Integer id)
    {
        return postRepo.findBoardPostByPostId(id);
    }
    public Iterable<BoardPost> findBoardPostByTag(String tag, boolean desc) {
        ArrayList<BoardPost> list = postRepo.findBoardPostByTag(tag);

        if (desc) {
            Collections.sort(list, (post1, post2) -> post2.getCreatedAt().compareTo(post1.getCreatedAt()));
        } else {
            Collections.sort(list, (post1, post2) -> post1.getCreatedAt().compareTo(post2.getCreatedAt()));
        }

        return list;

    }

    public Iterable<BoardPost> findBoardPostByTagAZ(String tag, boolean az) {
        ArrayList<BoardPost> list = postRepo.findBoardPostByTag(tag);

        if (az) {

            Collections.sort(list, Comparator.comparing(BoardPost::getTag));
        } else {

            Collections.sort(list, Comparator.comparing(BoardPost::getTag).reversed());
        }

        return list;
    }
    public Iterable<BoardPost> findBoardPostByTagTitleAZ(String tag, boolean az)
    {
        ArrayList<BoardPost> list = postRepo.findBoardPostByTag(tag);
        if (az) {

            Collections.sort(list, Comparator.comparing(BoardPost::getTitle));
        } else {

            Collections.sort(list, Comparator.comparing(BoardPost::getTitle).reversed());
        }

        return list;
    }

    public Iterable<BoardPost> sortBoardPostByTitle(boolean az)
    {
        ArrayList<BoardPost> list = postRepo.findAll();

        if (az) {

            Collections.sort(list, Comparator.comparing(BoardPost::getTitle));
        } else {

            Collections.sort(list, Comparator.comparing(BoardPost::getTitle).reversed());
        }

        return list;
    }
    public Iterable<BoardPost> sortBoardPostByTime(boolean desc)
    {
        ArrayList<BoardPost> list = postRepo.findAll();

        if (desc) {
            Collections.sort(list, (post1, post2) -> post2.getCreatedAt().compareTo(post1.getCreatedAt()));
        } else {
            Collections.sort(list, (post1, post2) -> post1.getCreatedAt().compareTo(post2.getCreatedAt()));
        }

        return list;
    }

    public Iterable<BoardPost> sortBoardPostByTag(boolean az)
    {
        ArrayList<BoardPost> list = postRepo.findAll();

        if (az) {

            Collections.sort(list, Comparator.comparing(BoardPost::getTag));
        } else {

            Collections.sort(list, Comparator.comparing(BoardPost::getTag).reversed());
        }

        return list;
    }

    public void deletePost(BoardPost bPost) {
        if (commentService != null) {
            ArrayList<BoardComment> boardComments = (ArrayList<BoardComment>) commentService.findCommentsByPostId(bPost);

            for (BoardComment bComment : boardComments) {
                commentService.deleteComment(bComment);
            }

        }
        postRepo.delete(bPost);


    }
    public void update(BoardPost bPost)
    {
        postRepo.save(bPost);
    }

    public List<BoardPost> findBoardPostsByTitleCont(String title)
    {
        return postRepo.findBoardPostByTitleContainingIgnoreCase(title);
    }
    public List<BoardPost> findBoardPostsByTagCont(String tag)
    {
        return postRepo.findBoardPostByTagContainingIgnoreCase(tag);
    }

    public Iterable<BoardPost> findBoardPostByUserId(BoardUser boardUser)
    {



        return postRepo.findBoardPostByUser(boardUser);
    }



    public int getPostCountByUserId(BoardUser bUser)
    {
        return postRepo.countAllByUser(bUser);
    }
}