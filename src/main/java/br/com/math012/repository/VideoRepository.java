package br.com.math012.repository;

import br.com.math012.models.UserModel;
import br.com.math012.models.VideoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<VideoModel, Long> {

    @Query("SELECT v FROM VideoModel v WHERE u.user=:user")
    List<VideoModel> findAllByUsername(@Param("user")UserModel userModel);
}
