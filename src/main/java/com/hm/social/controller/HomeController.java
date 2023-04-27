package com.hm.social.controller;


import com.hm.social.data.response.Hompage.PostResponse;
import com.hm.social.service.HomeService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/homepage")
public class HomeController {
    private final HomeService homeService;

    public HomeController (HomeService homeService) {
        this.homeService = homeService;
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Comments> getComment(@PathVariable Integer id){
//        return new ResponseEntity<Comments>(homeService.getComment(id),HttpStatusCode.valueOf(200));
//    }


    @GetMapping("/newest")
    public ResponseEntity<List<PostResponse>> getAllNewPostResponse () {
        return new ResponseEntity<List<PostResponse>>(homeService.getAllNewPost(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/common")
    public ResponseEntity<List<PostResponse>> getAllCommonPostResponse () {
        return new ResponseEntity<List<PostResponse>>(homeService.getAllCommonPost(), HttpStatusCode.valueOf(200));
    }

//@GetMapping("")
//    public ResponseEntity<List<Comments>> getAllComment () {
//        return new ResponseEntity<List<Comments>>(homeService.getallComment(),HttpStatusCode.valueOf(200));
//}

}
