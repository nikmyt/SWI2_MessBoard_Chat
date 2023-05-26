package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.Form.AuthForm;
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
    public Iterable<BoardComment> getByPostId(@PathVariable("post_id") Integer id)
    {
        BoardPost bPost = get(id);
       return commentService.findCommentsByPostId(bPost);



    }
    @DeleteMapping("/deletepost/{post_id}")
    public void deletePostById(@PathVariable("post_id") Integer id)
    {
        BoardPost bPost = postService.findByPostId(id).orElse(null);
        postService.deletePost(bPost);
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
        RegistrationService rService = new RegistrationService(userRepository);
        return rService.register(registrationForm);

    }

    @DeleteMapping("/deluser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> delete(BoardUser user, UserToken uToken)
    {
        RegistrationService rService = new RegistrationService(userRepository);
        return rService.delete(user, uToken);

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