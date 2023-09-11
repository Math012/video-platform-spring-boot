package br.com.math012.controller;

import br.com.math012.VO.VideoVO;
import br.com.math012.service.FileStorageService;
import br.com.math012.service.VideoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/video/v1")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping(value = "/{username}")
    public List<VideoVO> findAllByUsername(@PathVariable(value = "username")String username){
        return videoService.findAllVideosByUsername(username);
    }

    @GetMapping(value = "/videos")
    public List<VideoVO> findAll(){
        return videoService.findAll();
    }

    @PostMapping(value = "/uploads/{username}")
    public VideoVO upload(@RequestParam("file")MultipartFile file,@PathVariable(value = "username")String username){
        var filename = fileStorageService.storeFile(file,username);
        String videoDownload = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/videos/v1/download/")
                .path(username).path("/")
                .path(filename)
                .toUriString();
        var videoVO = new VideoVO(filename,new Date(),videoDownload);
        videoService.uploadVideo(videoVO,username);
        return videoVO;
    }

    @GetMapping(value = "/download/{username}/{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable(value = "username")String username, @PathVariable(value = "filename")String filename, HttpServletRequest request){
        Resource resource = fileStorageService.loadVideo(filename,username);
        String content = null;
        try {
            content = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (Exception e){
            e.getMessage();
        }
        if (content == null){
            content = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(content)).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"").body(resource);
    }

}
