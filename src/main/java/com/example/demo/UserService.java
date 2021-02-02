package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class UserService {

    private final Path fileStorageLocation;

    @Autowired
    public UserService() {

        this.fileStorageLocation = Paths.get("./media")
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
        }

    }

    public User save(String userString, List<MultipartFile> files, HttpServletRequest request){
        User user = new User();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            user = objectMapper.readValue(userString, User.class);
        } catch (Exception err) {
            System.err.printf("Error converting user info.");
        }

        files.forEach(file -> {
            try {
                String fileName = file.getOriginalFilename();
                Path targetLocation = this.fileStorageLocation.resolve(fileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        int fileCount = files.size();
        user.setCount(fileCount);

        return user;
    }

    public Long count() throws IOException {
        return Files.list(fileStorageLocation).count();
    }

}