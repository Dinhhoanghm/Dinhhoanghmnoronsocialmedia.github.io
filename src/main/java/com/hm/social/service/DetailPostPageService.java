package com.hm.social.service;


import com.hm.social.data.request.detailPage.CommentRequest;
import com.hm.social.data.request.detailPage.ReplyRequest;
import com.hm.social.data.response.detailPage.CommentResponse;
import com.hm.social.data.response.detailPage.PostResponse;
import com.hm.social.data.response.detailPage.ReplyResponse;
import com.hm.social.data.response.detailPage.UserResponse;
import com.hm.social.repository.DetailPostPageRepo;
import com.hm.social.tables.pojos.*;
import org.jooq.impl.QOM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailPostPageService {
   private final DetailPostPageRepo detailPostPageRepo;

   public DetailPostPageService (DetailPostPageRepo detailPostPageRepo){
       this.detailPostPageRepo = detailPostPageRepo;
   }

   @Autowired
   private KafkaTemplate<String,String> kafkaTemplate;

   public List<Comments> getAllNewComment(Integer id) {
        return  detailPostPageRepo.getAllNewComments(id);
   }
    public List<Comments> getAllCommonComment(Integer id) {
        return  detailPostPageRepo.getAllCommonComments(id);
    }
   public Posts getPostById (Integer id) {
       return  detailPostPageRepo.getPostById(id);
   }
   public List<Reply> getAllReply (Integer id) {
      return detailPostPageRepo.getAllReply(id);
   }

   public List<String> getRandomRecommendPost(Integer id){
       List<Posts> posts = detailPostPageRepo.getPostByTopic(id);
       List<String> postResponses = new ArrayList<>();
       for (Posts posts1:posts){
           postResponses.add(posts1.getPostquestion());
       }
       return postResponses;
   }

   public List<ReplyResponse> getAllReplyResponse (List<Reply> replies){
       List<ReplyResponse> replyResponses = new ArrayList<>();

       for (Reply reply : replies) {
           Users user = detailPostPageRepo.getUser(reply.getUserownerid());
           UserResponse userResponse2 = new UserResponse().setUserAvatar(user.getUseravatarurl())
                   .setUserName(user.getUseraccount());
           ReplyResponse replyResponse = new ReplyResponse().setUserReplyResponse(userResponse2)
                   .setReplyContent(reply.getReplycontent())
                   .setVoteCount(reply.getVotecount());
           replyResponses.add(replyResponse);

       }
       return replyResponses;
   }

    public List<CommentResponse> getAllCommentResponse (List<Comments> comments) {
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comments comments1 : comments) {
            List<Reply> replies = getAllReply(comments1.getCommentid());
            List<ReplyResponse> replyResponses = getAllReplyResponse(replies);
            Users user = detailPostPageRepo.getUser(comments1.getCommentuserid());
            UserResponse userResponse = new UserResponse().setUserAvatar(user.getUseravatarurl())
                    .setUserName(user.getUseraccount());
            CommentResponse commentResponse = new CommentResponse().setUserCommentResponse(userResponse)
                    .setCommentDesc(comments1.getCommentcontent())
                    .setReplyCount(comments1.getReplycount())
                    .setVoteCount(comments1.getVotecount())
                    .setReplyResponses(replyResponses);
            commentResponses.add(commentResponse);

        }
        return commentResponses;
    }
   public PostResponse getPostResponseByDate (Integer id ) {
       Posts post = getPostById(id);
       List<String> recommendPost = getRandomRecommendPost(post.getTopicrelatedid());
       Topic topic = detailPostPageRepo.getTopicById(post.getTopicrelatedid());
       Users user = detailPostPageRepo.getUser(post.getPostid());
       UserResponse userResponse = new UserResponse().setUserAvatar(user.getUseravatarurl())
               .setUserName(user.getUseraccount());
       List<Comments> comments = getAllNewComment(post.getPostid());

//       List<CommentResponse> commentResponses = new ArrayList<>();
//
//       for (Comments comments1 : comments){
//           List<Reply> replies = getAllReply(comments1.getPostid());
//           List<ReplyResponse> replyResponses = new ArrayList<>();
//
//           for (Reply reply : replies) {
//               Users user2 = detailPostPageRepo.getUser(reply.getUserownerid());
//               UserResponse userResponse2 = new UserResponse().setUserAvatar(user2.getUseravatarurl())
//                       .setUserName(user2.getUseraccount());
//               ReplyResponse replyResponse = new ReplyResponse().setUserReplyResponse(userResponse2)
//                       .setReplyContent(reply.getReplycontent())
//                       .setVoteCount(reply.getVotecount());
//               replyResponses.add(replyResponse);
//
//           }
//
//           Users user1 = detailPostPageRepo.getUser(comments1.getCommentuserid());
//           UserResponse userResponse1 = new UserResponse().setUserName(user1.getUseraccount())
//                   .setUserAvatar(user1.getUseravatarurl());
//
//            CommentResponse commentResponse = new CommentResponse().setCommentDesc(comments1.getCommentcontent())
//                    .setVoteCount(comments1.getVotecount())
//                    .setUserCommentResponse(userResponse1)
//                    .setReplyResponses(replyResponses);
//           commentResponses.add(commentResponse);
//       }
       List<CommentResponse> commentResponses = getAllCommentResponse(comments);

       PostResponse postResponse = new PostResponse().setUserResponse(userResponse)
               .setCommentResponses(commentResponses)
               .setPostDesc(post.getPostquestion())
               .setTopic(topic.getTopicdesc())
               .setCommentCount(post.getCommentcount())
               .setPostRecommend(recommendPost);

       return postResponse;
   }

    public PostResponse getPostResponseByCommon (Integer id ) {
        Posts post = getPostById(id);
        List<String> recommendPost = getRandomRecommendPost(post.getTopicrelatedid());
        Topic topic = detailPostPageRepo.getTopicById(post.getTopicrelatedid());
        Users user = detailPostPageRepo.getUser(post.getPostid());
        UserResponse userResponse = new UserResponse().setUserAvatar(user.getUseravatarurl())
                .setUserName(user.getUseraccount());
        List<Comments> comments = getAllCommonComment(post.getPostid());

//        List<CommentResponse> commentResponses = new ArrayList<>();
//
//        for (Comments comments1 : comments){
//            List<Reply> replies = getAllReply(comments1.getPostid());
//            List<ReplyResponse> replyResponses = new ArrayList<>();
//
//            for (Reply reply : replies) {
//                Users user2 = detailPostPageRepo.getUser(reply.getUserownerid());
//                UserResponse userResponse2 = new UserResponse().setUserAvatar(user2.getUseravatarurl())
//                        .setUserName(user2.getUseraccount());
//                ReplyResponse replyResponse = new ReplyResponse().setUserReplyResponse(userResponse2)
//                        .setReplyContent(reply.getReplycontent())
//                        .setVoteCount(reply.getVotecount());
//                replyResponses.add(replyResponse);
//
//            }
//
//            Users user1 = detailPostPageRepo.getUser(comments1.getCommentuserid());
//            UserResponse userResponse1 = new UserResponse().setUserName(user1.getUseraccount())
//                    .setUserAvatar(user1.getUseravatarurl());
//
//            CommentResponse commentResponse = new CommentResponse().setCommentDesc(comments1.getCommentcontent())
//                    .setVoteCount(comments1.getVotecount())
//                    .setUserCommentResponse(userResponse1)
//                    .setReplyResponses(replyResponses);
//            commentResponses.add(commentResponse);
//        }
        List<CommentResponse> commentResponses = getAllCommentResponse(comments);
        PostResponse postResponse = new PostResponse().setUserResponse(userResponse)
                .setCommentResponses(commentResponses)
                .setPostRecommend(recommendPost)
                .setTopic(topic.getTopicdesc())
                .setCommentCount(post.getCommentcount())
                .setPostDesc(post.getPostquestion());

        return postResponse;
    }

    public CommentResponse getComment(CommentRequest commentRequest,Integer id){
        Users user = detailPostPageRepo.getUser(commentRequest.getUserId());
        Posts posts = detailPostPageRepo.getPostById(id);
        Comments comment = new Comments().setCommentcontent(commentRequest.getCommentDesc())
               .setPostid(id)
                .setPostuserid(posts.getUseownerid())
               .setCommentuserid(commentRequest.getUserId());
        Comments newComment = detailPostPageRepo.insertComment(comment);
        detailPostPageRepo.updatePostCommentCount(id);
        UserResponse userResponse = new UserResponse().setUserName(user.getUseraccount())
               .setUserAvatar(user.getUseravatarurl());
       return new CommentResponse().setCommentDesc(newComment.getCommentcontent())
               .setUserCommentResponse(userResponse).setVoteCount(0).setReplyCount(0);
    }
//public void getComment(CommentRequest commentRequest,Integer id){
//    Comments comment = new Comments();
//    comment.setCommentContent(commentRequest.getCommentDesc());
//    comment.setPostId(id);
//    comment.setCommentuserid(commentRequest.getUserId());
//    detailPostPageRepo.insertComment(comment);
//
//}
public ReplyResponse getReply (ReplyRequest replyRequest, Integer postId, Integer commentId){
    Users user = detailPostPageRepo.getUser(replyRequest.getUserReplyId());
    Posts posts = detailPostPageRepo.getPostById(postId);
    Comments comment = detailPostPageRepo.getCommentById(commentId);
    Reply reply = new Reply().setReplycontent(replyRequest.getReplyContent())
            .setPostid(postId)
            .setCommentid(commentId)
            .setUsercommentid(comment.getCommentuserid())
            .setUserownerid(replyRequest.getUserReplyId())
            .setUserpostid(posts.getUseownerid());
    Reply newReply = detailPostPageRepo.insertReply(reply);
    detailPostPageRepo.updateCommentReplyCount(commentId);
    UserResponse userResponse = new UserResponse().setUserName(user.getUseraccount())
            .setUserAvatar(user.getUseravatarurl());
    return new ReplyResponse().setReplyContent(newReply.getReplycontent())
            .setVoteCount(0)
            .setUserReplyResponse(userResponse)
            .setUserCommentName(detailPostPageRepo.getUser(comment.getCommentuserid()).getUsername());
}

public Integer commentVote(Integer postId,Integer commentId){
       Posts post= getPostById(postId);
       Comments comment = detailPostPageRepo.getCommentById(commentId);
       return  detailPostPageRepo.updateCommentVote(comment);
   }
    public Integer replyVote(Integer postId,Integer commentId,Integer replyId){
        Posts post= getPostById(postId);
        Comments comment = detailPostPageRepo.getCommentById(commentId);
        Reply reply = detailPostPageRepo.getReplyById(replyId);
        return  detailPostPageRepo.updateReplyVote(reply);
    }
}

