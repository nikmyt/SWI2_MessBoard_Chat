package cz.osu.java.messboardapp.model;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BoardPost
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id", nullable = false)
    private Integer postId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private BoardUser user;

    @Column(nullable = false, length=300)
    private String text;

    @Column(nullable = false, length=300)
    private String title;
    @Column(nullable = false, length=15)
    private String tag;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @BooleanFlag
    @Column(nullable = true, name = "temporary")
    private boolean temp;
}
