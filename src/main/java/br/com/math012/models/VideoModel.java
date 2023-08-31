package br.com.math012.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "videos")
public class VideoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "date_post", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datePost;

    @Column(name = "url_video")
    private String urlVideo;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    public VideoModel(){}

    public VideoModel(String title, Date datePost, String urlVideo, UserModel user) {
        this.title = title;
        this.datePost = datePost;
        this.urlVideo = urlVideo;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}