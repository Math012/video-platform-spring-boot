package br.com.math012.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.math012.VO.VideoVO;
import br.com.math012.controller.VideoController;
import br.com.math012.dozer.DozerConverter;
import br.com.math012.exceptions.NotFoundException;
import br.com.math012.models.VideoModel;
import br.com.math012.repository.UserRepository;
import br.com.math012.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<VideoVO> findAll(){
        var videoVO = DozerConverter.parseListObject(videoRepository.findAll(), VideoVO.class);
        return videoVO;
    }

    public List<VideoVO> findAllVideosByUsername(String username){
        var user = userRepository.findByUsername(username);
        if (user == null){
            throw new NotFoundException("This username:" + username + ", not found, try again!");
        }
        var video = DozerConverter.parseListObject(videoRepository.findAllByUsername(user), VideoVO.class);
        return video;
    }

    public VideoVO uploadVideo(VideoVO video, String username){
        var user = userRepository.findByUsername(username);
        if (user == null){
            throw new NotFoundException("This username:" + username + ", not found, try again!");
        }
        var videoModel = DozerConverter.parseObject(video, VideoModel.class);
        videoModel.setUser(user);
        //for correction = new Date()
        videoModel.setDatePost(new Date());
        var videoVO = DozerConverter.parseObject(videoRepository.save(videoModel), VideoVO.class);
        return videoVO;

    }
}