package ImageHoster.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Comment")
public class Comment implements Serializable{
    // These annotations auto-increments the id column for us whenever
    // a new Image object is stored into the database
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Image image;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Comment() { }

    public Comment(String text, User user, Image image) {
        this.text = text;
        this.user = user;
        this.image = image;
        this.createdDate = LocalDate.now();
    }

    public int getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() { return this.text; }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreatedDate() {
        return createdDate.toString();
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public void setImage(Image image) { this.image = image; }

    public Image getImage() { return this.image; }

    public void setUser(User user) { this.user = user; }

    public User getUser() { return this.user; }
}
