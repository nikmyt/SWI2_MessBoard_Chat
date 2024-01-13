package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.Configs.DynamicRoutingDataSource;
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
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping({"/"})
//@RequestMapping("/users") ?
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController
{
    @Autowired
    private DynamicRoutingDataSource dynamicRoutingDataSource;
    private final AppUserRepository userRepository;
    private final RegistrationService registrationService;
    private final AuthService authService;
    private final PostService postService;
    private final CommentService commentService;

    @PostConstruct
    public void init() {
        dynamicRoutingDataSource.setDataSource("mariadb");
    }

    public MainController(AppUserRepository userRepository, RegistrationService registrationService, AuthService authService, PostService postService, CommentService commentService) {
        this.userRepository = userRepository;
        this.registrationService = registrationService;
        this.authService = authService;
        this.postService = postService;
        this.commentService = commentService;
    }

    //BoardPost control
    @PostMapping("/new_post")
    public void create(@Valid @RequestBody PostForm newPost){
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
    @GetMapping("/postsbyuser/{id}")
    public Iterable<BoardPost> getPostsByUser(@PathVariable("id") Integer id)
    {
        BoardUser bUser = userRepository.findBoardUserByUserId(id);
        Iterable<BoardPost> posts = postService.findBoardPostByUserId(bUser);
        return posts;
    }
    @GetMapping("/postcount/{user_id}")
    public int getPostCount(@PathVariable("user_id") Integer id)
    {
        BoardUser bUser = userRepository.findBoardUserByUserId(id);
        return postService.getPostCountByUserId(bUser);
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
    @GetMapping("/posts-user/{id}")
    public Iterable<BoardPost> userPosts(@PathVariable("id") Integer id)
    {
        BoardUser bUser = userRepository.findBoardUserByUserId(id);
        return postService.findBoardPostByUserId(bUser);
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

    //BoardComment control
    @GetMapping("/comment/{post_id}")
    public Iterable<CommentForm> getByPostId(@PathVariable("post_id") Integer post_id) {
        try {
            BoardPost bPost = postService.findByBPostId(post_id);
            Iterable<BoardComment> comments = commentService.findCommentsByPostId(bPost);
            ArrayList<CommentForm> formCom = new ArrayList<>();
            // Initialize the user objects before serialization
            for (BoardComment comment : comments) {
                formCom.add((new CommentForm()).assets(comment.getText(), comment.getCreatedAt(), comment.getUser().getUserId(), comment.getPost().getPostId(), comment.getUser().getUsername()));
            }
            return formCom;
        } catch (Exception e) {
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
        BoardUser bUser = userRepository.findBoardUserByUserId(id);
        return commentService.getCommentCountByUserId(bUser);
    }


    //BoardUser control
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

    //Search control
    @GetMapping("/search/{term}")
    public Iterable<BoardPost> getSearchResults(@PathVariable(value = "term", required = true) String term)
    {


        ArrayList<BoardPost> boardPostsX = new ArrayList<>();
        List<BoardPost> boardPosts= postService.findBoardPostsByTagCont(term);

        List<BoardPost> boardPoststitle= postService.findBoardPostsByTitleCont(term);

        Set<BoardPost> mergedPosts = new HashSet<>();
        for (BoardPost post : boardPosts) {
            mergedPosts.add(post);
        }
        for (BoardPost post : boardPoststitle) {
            mergedPosts.add(post);
        }

        Iterable<BoardPost> mergedIterable = mergedPosts;

        for (BoardPost bPost: mergedIterable
             ) {
            boardPostsX.add(bPost);

        }
        return boardPostsX;


    }
}

