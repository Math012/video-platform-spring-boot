package br.com.math012.repository;

import br.com.math012.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT p FROM UserModel p WHERE p.userName =:userName")
    UserModel findByUsername(@Param("userName")String userName);
}
