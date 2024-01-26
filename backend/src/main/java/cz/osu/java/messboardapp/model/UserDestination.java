package cz.osu.java.messboardapp.model;

import jakarta.persistence.*;

import java.io.Serializable;


@Embeddable
class UserDestinationId implements Serializable {
    private Long userId;

    private Long destinationId;

    public void setDestinationId(Long destinationId)
    {
        this.destinationId = destinationId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public Long getDestinationId()
    {
        return this.destinationId;
    }
    public Long getUserId()
    {
        return this.userId;
    }
    // default constructor, getters, setters, and equals/hashCode methods
}
@Entity
public class UserDestination {
    @EmbeddedId
    private UserDestinationId id;

    public void setId(Long userId, Long destinationId)
    {
        this.id = new UserDestinationId();
        this.id.setUserId(userId);
        this.id.setDestinationId(destinationId);
    }
    public Long getIdDestinationId()
    {
        return this.id.getDestinationId();
    }
    public Long getIdUserId()
    {
        return this.id.getUserId();
    }

}
