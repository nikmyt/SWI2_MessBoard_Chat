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

    /*
    @Setter(AccessLevel.NONE)
    private Integer userId;
    */

    @Setter(AccessLevel.NONE)
    private Integer postId;

    //what?!
    //public void setCreatedAt(java.sql.Timestamp createdAt) {
    //    this.createdAt = new Date(createdAt.getTime());
    //}

    public void PostForm(String title, String text, String tag, String username, int userId)
    {
        this.title = title;
        this.text = text;
        this.tag = tag;
        this.username = username;
        this.createdAt = Calendar.getInstance().getTime();
        this.temp = false;

        this.userId=userId;
        int find = (int) postRep.count();
        this.postId = find+1;

        //don't save it here!!! it creates duplis

    }
}
