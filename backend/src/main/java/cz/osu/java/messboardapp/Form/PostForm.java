package cz.osu.java.messboardapp.Form;

import cz.osu.java.messboardapp.repository.PostRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
public class PostForm
{
    private PostRepository postRep;
    private String title;

    private String text;

    private String tag;

    private String username;
    private int userId;

    @Setter(AccessLevel.NONE)
    private boolean temp;

    @Setter(AccessLevel.NONE)
    private Date createdAt;



    @Setter(AccessLevel.NONE)
    private Integer postId;



    public PostForm uses(String title, String text, String tag, int userId, int postId, Date createdAt)
    {
        this.title = title;
        this.text = text;
        this.tag = tag;

        this.createdAt = createdAt;
        this.temp = false;

        this.userId=userId;
        this.postId = postId;
        return this;

    }
}
