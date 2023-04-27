package com.hm.social.controller;

public class HomeControllerDemo {
}
//package com.hm.social.controller;
//
//
//import com.hm.social.data.request.PostRequest;
//import com.hm.social.data.response.HomePage.PostResponse;
//import com.hm.social.service.HomeService;
//import com.hm.social.tables.pojos.Posts;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/homepage")
//public class HomeController {
//    @Autowired
//    private HomeService homeService;
//
//    @PostMapping("/request")
//    @ResponseBody
//    public ResponseEntity<PostResponse> save(@RequestBody PostRequest postModel) {
//        return ResponseEntity.ok(homeService.insert(postModel));
//    }
//
//    @PutMapping("/put")
//    public String update(@RequestBody PostRequest postModel) {
//        homeService.edit(postModel);
//        return "post edited...";
//    }
//
//    @DeleteMapping("delete/{id}")
//    public String delete(@PathVariable Integer id) {
//        homeService.delete(id);
//        return "post deleted...";
//    }
//
//    @GetMapping("")
//    public ResponseEntity<List<Posts>> getAllPost() {
//        return new ResponseEntity<List<Posts>>(homeService.getAllPost(), HttpStatusCode.valueOf(200));
//
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Posts> getNewPost(@PathVariable Integer id) {
//
//        return new ResponseEntity<Posts>(homeService.getNewPost(id), HttpStatusCode.valueOf(200));
//    }
//}



