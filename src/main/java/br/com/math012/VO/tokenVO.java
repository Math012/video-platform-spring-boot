package br.com.math012.VO;

import java.util.Date;
import java.util.Objects;

public class tokenVO {
    private static final long serialVersionUID = 1L;

    private String username;
    private Boolean auth;
    private Date created;
    private String token;

    public tokenVO(){}

    public tokenVO(String username, Boolean auth, Date created, String token) {
        this.username = username;
        this.auth = auth;
        this.created = created;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        tokenVO tokenVO = (tokenVO) o;
        return Objects.equals(username, tokenVO.username) && Objects.equals(auth, tokenVO.auth) && Objects.equals(created, tokenVO.created) && Objects.equals(token, tokenVO.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, auth, created, token);
    }
}
