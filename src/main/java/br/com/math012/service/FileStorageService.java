package br.com.math012.service;

import br.com.math012.config.videoconfig.VideoStorageConfig;
import br.com.math012.exceptions.NotFoundException;
import br.com.math012.exceptions.VideoStorageException;
import br.com.math012.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(VideoStorageConfig videoStorageConfig){
        Path path = Paths.get(videoStorageConfig.getUpload_dir())
                .toAbsolutePath()
                .normalize();
        this.fileStorageLocation = path;
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new VideoStorageException("Could not create the directory");
        }
    }

    @Autowired
    private UserRepository userRepository;

    public String storeFile(MultipartFile file, String username){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = this.fileStorageLocation;
        if (userRepository.findByUsername(username) == null){
            throw new NotFoundException("This user is not registered");
        }
        var createFolder = new File(path+"\\"+username).mkdir();
        Path pathFolder = Path.of(path+"\\"+username);
        try {
            if (filename.contains("..")) throw new VideoStorageException("please verify the filename!");
            Path location = pathFolder.resolve(filename);
            Files.copy(file.getInputStream(),location, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception e){
            throw new VideoStorageException("could not create the directory");
        }
    }

    public Resource loadVideo(String filename, String username){
        try {
            Path path = this.fileStorageLocation;
            Path locationPath = Path.of(path+"\\"+username);
            Path filePath = locationPath.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) return resource;
            else throw new VideoStorageException("File not found");
        }catch (Exception e){
            throw new VideoStorageException("The video not found, try again");
        }
    }
}
