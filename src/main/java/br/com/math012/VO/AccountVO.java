package br.com.math012.VO;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Objects;

public class AccountVO extends RepresentationModel<AccountVO> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public AccountVO(){}

    public AccountVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        AccountVO accountVO = (AccountVO) o;
        return Objects.equals(username, accountVO.username) && Objects.equals(password, accountVO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
