package br.com.math012.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import java.util.Objects;

public class RegisterVO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @Mapping("id")
    private Long idVO;
    private String username;
    private String fullname;
    private String password;

    public RegisterVO(){}

    public RegisterVO(Long idVO, String username, String fullname, String password) {
        this.idVO = idVO;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }

    public Long getIdVO() {
        return idVO;
    }

    public void setIdVO(Long idVO) {
        this.idVO = idVO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterVO that = (RegisterVO) o;
        return Objects.equals(idVO, that.idVO) && Objects.equals(username, that.username) && Objects.equals(fullname, that.fullname) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVO, username, fullname, password);
    }
}