package br.com.math012.controller;

import br.com.math012.VO.VideoVO;
import br.com.math012.service.FileStorageService;
import br.com.math012.service.VideoService;
import br.com.math012.utils.mediatype.ConstMediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Endpoint for videos")
@RestController
@RequestMapping(value = "api/video/v1")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileStorageService fileStorageService;

    @Operation(summary = "Endpoint list of videos by username")
    @GetMapping(value = "/{username}", produces = {ConstMediaType.APPLICATION_JSON, ConstMediaType.APPLICATION_XML, ConstMediaType.APPLICATION_YML})
    public List<VideoVO> findAllByUsername(@PathVariable(value = "username")String username){
        return videoService.findAllVideosByUsername(username);
    }

    @Operation(summary = "Endpoint list of all videos")
    @GetMapping(value = "/videos", produces = {ConstMediaType.APPLICATION_JSON, ConstMediaType.APPLICATION_XML, ConstMediaType.APPLICATION_YML})
    public List<VideoVO> findAll(){
        return videoService.findAll();
    }

    @Operation(summary = "Endpoint upload videos")
    @PostMapping(value = "/uploads/{username}")
    public VideoVO upload(@RequestParam("file")MultipartFile file,@PathVariable(value = "username")String username){
        var filename = fileStorageService.storeFile(file,username);
        String videoDownload = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/video/v1/download/")
                .path(username).path("/")
                .path(filename)
                .toUriString();
        var videoVO = new VideoVO(filename,new Date(),videoDownload);
        videoService.uploadVideo(videoVO,username);
        return videoVO;
    }

    @Operation(summary = "Endpoint download videos")
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
