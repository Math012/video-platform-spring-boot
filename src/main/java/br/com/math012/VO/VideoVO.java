package br.com.math012.VO;

import br.com.math012.models.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class VideoVO implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long idVO;
    private String title;
    private Date postDate;
    private String urlVideo;
    private UserModel user;

    public VideoVO(){}

    public VideoVO(Long idVO, String title, Date postDate, String urlVideo) {
        this.idVO = idVO;
        this.title = title;
        this.postDate = postDate;
        this.urlVideo = urlVideo;
    }

    public Long getIdVO() {
        return idVO;
    }

    public void setIdVO(Long idVO) {
        this.idVO = idVO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoVO videoVO = (VideoVO) o;
        return Objects.equals(idVO, videoVO.idVO) && Objects.equals(title, videoVO.title) && Objects.equals(postDate, videoVO.postDate) && Objects.equals(urlVideo, videoVO.urlVideo) && Objects.equals(user, videoVO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVO, title, postDate, urlVideo, user);
    }
}