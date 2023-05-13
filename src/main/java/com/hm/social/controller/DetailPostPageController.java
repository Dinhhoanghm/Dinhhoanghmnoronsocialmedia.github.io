package com.hm.social.controller;


import com.hm.social.data.request.detailPage.CommentRequest;
import com.hm.social.data.request.detailPage.ReplyRequest;
import com.hm.social.data.response.detailPage.CommentResponse;
import com.hm.social.data.response.detailPage.PostResponse;
import com.hm.social.data.response.detailPage.ReplyResponse;
import com.hm.social.service.DetailPostPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homepage/detail")
public class DetailPostPageController {

    @Autowired
    private DetailPostPageService detailPostPageService;

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;


    @GetMapping("/{id}/newest")
    public ResponseEntity<PostResponse> getPostDetailByDate(@PathVariable Integer id) {
        return new ResponseEntity<PostResponse>(detailPostPageService.getPostResponseByDate(id), HttpStatusCode.valueOf(200));

    }

    @GetMapping("/{id}/common")
    public ResponseEntity<PostResponse> getPostDetailByCommon(@PathVariable Integer id) {
        return new ResponseEntity<PostResponse>(detailPostPageService.getPostResponseByCommon(id), HttpStatusCode.valueOf(200));

    }

//    @PostMapping("/{id}")
//    @ResponseBody
//    public String getComment (@RequestBody CommentRequest commentRequest, @PathVariable("id") Integer id)
//    {
//       detailPostPageService.getComment(commentRequest,id);
//       return "Comment added...";
//    }

    @PostMapping("/{postId}")
    @ResponseBody
    public ResponseEntity<CommentResponse> getComment(
            @RequestBody CommentRequest commentRequest, @PathVariable("postId") Integer id) {
        return
                new ResponseEntity<CommentResponse>
                        (detailPostPageService.getComment(commentRequest, id), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{postId}/reply/{commentId}")
    @ResponseBody
    public ResponseEntity<ReplyResponse> getReply(@RequestBody ReplyRequest replyRequest,
                                                  @PathVariable("postId") Integer postId,
                                                  @PathVariable("commentId") Integer commentId ) {
        return new ResponseEntity<ReplyResponse>(detailPostPageService.getReply(replyRequest, postId,commentId), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/{postId}/reply/{commentId}/CommentVote")
    public Integer updateCommentVote ( @PathVariable("postId") Integer postId,
                                       @PathVariable("commentId") Integer commentId){
        return detailPostPageService.commentVote(postId,commentId);
    }
    @PostMapping("/{postId}/reply/{commentId}/ReplyVote")
    public Integer updateCommentVote ( @PathVariable("postId") Integer postId,
                                       @PathVariable("commentId") Integer commentId,
                                       @PathVariable("ReplyId") Integer replyId){
        return detailPostPageService.replyVote(postId,commentId,replyId);
    }

}
