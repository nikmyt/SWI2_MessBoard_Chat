package cz.osu.java.messboardapp.Form;


import cz.osu.java.messboardapp.model.BoardComment;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.CommentRepository;
import cz.osu.java.messboardapp.repository.PostRepository;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class CommentForm
{

    private String text;

    @Setter(AccessLevel.NONE)
    private Date createdAt;

    @Setter(AccessLevel.NONE)
    private Integer userId;

    @Setter(AccessLevel.NONE)
    private Integer postId;



    private String username;


    public CommentForm assets(String text, Date createdAt, int userId, int postId, String username)
    {

        this.createdAt=createdAt;
        this.postId=postId;
        this.text=text;
        this.userId = userId;
        this.username=username;
        return this;

    }

}
