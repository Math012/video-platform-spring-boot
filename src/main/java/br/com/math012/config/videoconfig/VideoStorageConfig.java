package br.com.math012.config.videoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class VideoStorageConfig {

    private String upload_dir;

    public String getUpload_dir(){
        return this.upload_dir;
    }

    public void setUpload_dir(String upload_dir){
        this.upload_dir = upload_dir;
    }
}
