package cz.osu.java.messboardapp.Form;


import cz.osu.java.messboardapp.model.BoardPost;
import cz.osu.java.messboardapp.model.BoardUser;
import cz.osu.java.messboardapp.repository.AppUserRepository;
import cz.osu.java.messboardapp.repository.PostRepository;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class PostForm
{
    private PostRepository postRep;
    private String title;

    private String text;

    private String tag;

    @Setter(AccessLevel.NONE)
    private boolean temp;

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

    public void PostForm(boolean temp, String title, String text, String tag, BoardUser user)
    {

        BoardPost bPost = new BoardPost();
        bPost.setTag(tag);
        bPost.setText(text);
        bPost.setTitle(title);
        bPost.setTemp(false);
        int find = (int) postRep.count();
        bPost.setPostId(find+1);
        bPost.setUser(user);
        bPost.setCreatedAt(new Date());

        postRep.save(bPost);
    }
}
