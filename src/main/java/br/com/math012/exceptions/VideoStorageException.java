package br.com.math012.exceptions;

import br.com.math012.config.videoconfig.VideoStorageConfig;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class VideoStorageException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public VideoStorageException(String error){
        super(error);
    }
}