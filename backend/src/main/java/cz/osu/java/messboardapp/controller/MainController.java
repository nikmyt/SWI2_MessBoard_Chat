package cz.osu.java.messboardapp.controller;

import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.service.AuthService;
import cz.osu.java.messboardapp.service.CommentService;
import cz.osu.java.messboardapp.service.PostService;
import cz.osu.java.messboardapp.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping({"/"})
//@RequestMapping("/users") ?
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController
{
    private final AppUserRepository userRepository; //i don't think this is correct bro
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
    }


    @PostMapping("/new_post") // /new
    public BoardPost create(@Valid @RequestBody BoardPost newPost){
        return postService.save(newPost);
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
    public BoardComment getByPostId(@PathVariable("id") Integer id){
        BoardComment bComm = commentService.findCommentByPostId(id);

        return bComm;

    }

    // %20 is a space in a url
    //possibly in the future: search?name=l&year=2005

}

//To test:
//v body dej raw: {"name":"Black Sabbath"}
// and add header: Content-Type: application/json