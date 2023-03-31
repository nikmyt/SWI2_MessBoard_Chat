package cz.osu.javamessboardapp.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoardComment
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="comment_id", nullable = false)
    private Integer comment_id;



    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="post_id", referencedColumnName = "post_id")
    private BoardPost postId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private BoardUser user;

    @Column(nullable = false, length=300)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;


}
