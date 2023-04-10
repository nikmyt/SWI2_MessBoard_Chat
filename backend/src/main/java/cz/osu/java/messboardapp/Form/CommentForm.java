package cz.osu.java.messboardapp.Form;


import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.model.BoardPost;
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

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = new Date(createdAt.getTime());
    }
    public void setUser(BoardUser user) {
        this.userId = user.getUserId();
    }
    public void setPost(BoardPost post) {
        this.postId = post.getPostId();
    }

}
