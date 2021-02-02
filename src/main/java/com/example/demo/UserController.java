package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/uploadFiles")
    public User upload(@RequestPart("user") String userString, @RequestPart("files") List<MultipartFile> files, HttpServletRequest request) {
        User res = userService.save(userString, files, request);
        return res;
    }

    @GetMapping("/count")
    public Long count() throws IOException {
        return userService.count();
    }
}