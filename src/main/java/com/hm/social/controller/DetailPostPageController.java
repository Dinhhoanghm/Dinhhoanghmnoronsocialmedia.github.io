package com.hm.social.controller;


import com.hm.social.data.request.DetailPage.CommentRequest;
import com.hm.social.data.request.DetailPage.UserRequest;
import com.hm.social.data.response.DetailPage.CommentResponse;
import com.hm.social.data.response.DetailPage.PostResponse;
import com.hm.social.service.DetailPostPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homepage/detail")
public class DetailPostPageController {

    @Autowired
    private DetailPostPageService detailPostPageService;

    @GetMapping("/{id}/newest")
    public ResponseEntity<PostResponse> getPostDetailByDate (@PathVariable Integer id){
        return new ResponseEntity<PostResponse>(detailPostPageService.getPostResponseByDate(id), HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}/common")
    public ResponseEntity<PostResponse> getPostDetailByCommon (@PathVariable Integer id){
        return new ResponseEntity<PostResponse>(detailPostPageService.getPostResponseByCommon(id), HttpStatusCode.valueOf(200));

    }

    @PostMapping("/{id}")
    @ResponseBody
    public String getComment (@RequestBody CommentRequest commentRequest)
    {
       detailPostPageService.getComment(commentRequest);
       return "Comment added...";
    }


}
