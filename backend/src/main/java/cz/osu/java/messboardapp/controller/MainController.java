package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.Form.AuthForm;
import cz.osu.java.messboardapp.Form.CommentForm;
import cz.osu.java.messboardapp.Form.PostForm;
import cz.osu.java.messboardapp.Form.RegistrationForm;
import cz.osu.java.messboardapp.json.UserToken;
import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.service.AuthService;
import cz.osu.java.messboardapp.service.CommentService;
import cz.osu.java.messboardapp.service.PostService;
import cz.osu.java.messboardapp.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RequestMapping({"/"})
//@RequestMapping("/users") ?
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController
{
    private final AppUserRepository userRepository; //I don't think this is correct bro
    //Sure it is sis
    //sorry 🥺 but you commented it out yourself!

    private final RegistrationService registrationService;
    private final AuthService authService;
    private final PostService postService;
    private final CommentService commentService;


    public MainController(AppUserRepository userRepository, RegistrationService registrationService, AuthService authService, PostService postService, CommentService commentService) {
        this.userRepository = userRepository;
        this.registrationService = registrationService;
        this.authService = authService;
        this.postService = postService;
        this.commentService = commentService;
        //uToken = null;
    }


    @PostMapping("/new_post") //sorry i made bad things here
    public void create(@Valid @RequestBody PostForm newPost){
        //BoardUser user = userRepository.findAppUserByUsername(newPost.getUsername());
        BoardUser user = userRepository.findBoardUserByUserId(newPost.getUserId());
        postService.save(newPost, user);

    }





    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<BoardPost> getAll()
    {
        return postService.findAll(); //?
    }

    @GetMapping("/posts/{id}")
    public BoardPost get(@PathVariable("id") Integer id){
        BoardPost bPost = postService.findByPostId(id).orElse(null);

        return bPost;

    }

    @GetMapping("/comment/{post_id}")
    public Iterable<BoardComment> getByPostId(@PathVariable("post_id") Integer post_id)
    {
        try {
            BoardPost bPost = get(post_id);
            return commentService.findCommentsByPostId(bPost);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    @PostMapping("/newcomment")
    public void createcomment(@Valid @RequestBody CommentForm commentForm){
        BoardUser user = userRepository.findBoardUserByUserId(commentForm.getUserId());




        BoardPost post = postService.findByBPostId(commentForm.getPostId());
        commentService.save(commentForm, user, post);
    }

    @DeleteMapping("/deletecomment/{comment_id}")
    public void deleteCommentsById(@PathVariable("comment_id") Integer id)
    {
        BoardComment boardComment = commentService.findBoardCommentByComment_id(id).orElse(null);
        try {
            commentService.deleteComment(boardComment);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/editcomment/{comment_id}")
    public void editCommentById(@PathVariable("post_id") Integer id, @RequestBody CommentForm commentForm)
    {
        BoardComment boardComment = commentService.findBoardCommentByComment_id(id).orElse(null);
        try
        {
            BoardUser boardUser = new BoardUser();
            boardUser = userRepository.findBoardUserByUserId(commentForm.getUserId());

            BoardPost boardPost = new BoardPost();
            boardPost = postService.findByBPostId(commentForm.getPostId());
            boardComment.setUser(boardUser);
            boardComment.setText(commentForm.getText());
            boardComment.setPost(boardPost);
            boardComment.setCreatedAt(commentForm.getCreatedAt());

            commentService.update(boardComment);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @GetMapping("/commentcount/{user_id}")
    public int getCommentCount(@PathVariable("user_id") Integer id)
    {
        return commentService.getCommentCountByUserId(id);
    }

    @GetMapping("/postcount/{user_id}")
    public int getPostCount(@PathVariable("user_id") Integer id)
    {
        return postService.getPostCountByUserId(id);
    }

    @DeleteMapping("/deletepost/{post_id}")
    public void deletePostById(@PathVariable("post_id") Integer id)
    {
        BoardPost bPost = postService.findByPostId(id).orElse(null);
        postService.deletePost(bPost);
    }
    @PutMapping("/editpost/{post_id}")
    public void editPostById(@PathVariable("post_id") Integer id, @RequestBody PostForm postForm)
    {
        BoardPost bPost = postService.findByPostId(id).orElse(null);
        try
        {
            bPost.setTag(postForm.getTag());
            bPost.setText(postForm.getText());
            bPost.setTitle(postForm.getTitle());
            postService.update(bPost);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> login(@RequestBody AuthForm authForm) {

        AuthService authService = new AuthService(userRepository);

        ResponseEntity<Object> responseEntity = authService.authenticate(authForm);

        return responseEntity;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> register(@RequestBody RegistrationForm registrationForm)
    {

       return registrationService.register(registrationForm);
    }

    @DeleteMapping("/deluser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(BoardUser user, UserToken uToken)
    {
        RegistrationService rService = new RegistrationService(userRepository);
        return rService.delete(user, uToken);

    }
    @GetMapping("/posts-user/{id}")
    public Iterable<BoardPost> userPosts(@PathVariable("id") Integer id)
    {
        return postService.findBoardPostByUserId(id);
    }

    @GetMapping("/searchSimple")
    public Iterable<?> getSimpleSearchResults(@RequestParam(value = "searchSimple", required = true) String search){
        //only looks at titles, ideally it would return by text and title... oh, i can do that
        Iterable<BoardPost> searchResults;
        searchResults = postService.findBoardPostsByTitleCont(search);
        //ah well it doen't exist
        //searchResults = postService.findBoardPostsByTextCont(search)

        return searchResults;
    }

    @GetMapping("/search")
    public Iterable<?> getSearchResults(@RequestParam(value = "search", required = true) String search)
    {

        String[] parts = search.split("_");
        String condition = parts[0];
        String term = parts[1];

        //no text? why not?
        if(condition.equals("tag"))
        {
            return postService.findBoardPostsByTagCont(term);
        }
        else if(condition.equals("title"))
        {
            return postService.findBoardPostsByTitleCont(term);
        }
        else if(condition.equals("user"))
        {
            return userRepository.findBoardUserByUsernameContainingIgnoreCase(term);
        }
        else
        {
            return null;
        }
    }

    @GetMapping("/postssort")
    public Iterable<BoardPost> getFilteredPosts(@RequestParam(value = "filter", required = false) String filter) {
        if (filter == null) {
            // No filter provided, return all posts
            return postService.findAll();
        } else {

            switch (filter) {
                case "newest":
                    return postService.sortBoardPostByTime(true);

                case "oldest":
                    return postService.sortBoardPostByTime(false);

                case "tagsAZ":
                    return postService.sortBoardPostByTag(true);

                case "tagsZA":
                    return postService.sortBoardPostByTag(false);

                case "titleAZ":
                    return postService.sortBoardPostByTitle(true);

                case "titleZA":
                    return postService.sortBoardPostByTitle(false);
                default:
                    return postService.sortBoardPostByTime(true);
            }
        }
    }

}

// %20 is a space in an url
//possibly in the future: search?name=l&year=2005

//To test:
//v body dej raw: {"name":"Black Sabbath"}
// and add header: Content-Type: application/json