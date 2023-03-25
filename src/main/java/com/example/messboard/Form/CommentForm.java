package com.example.messboard.Form;


import com.example.messboard.Model.db.BoardPost;
import com.example.messboard.Model.db.BoardUser;
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
