package com.hm.social.service;


import com.hm.social.data.request.DetailPage.CommentRequest;
import com.hm.social.data.request.DetailPage.UserRequest;
import com.hm.social.data.response.DetailPage.CommentResponse;
import com.hm.social.data.response.DetailPage.PostResponse;
import com.hm.social.data.response.DetailPage.ReplyResponse;
import com.hm.social.data.response.DetailPage.UserResponse;
import com.hm.social.repository.DetailPostPageRepo;
import com.hm.social.tables.pojos.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetailPostPageService {
   private final DetailPostPageRepo detailPostPageRepo;

   public DetailPostPageService (DetailPostPageRepo detailPostPageRepo){
       this.detailPostPageRepo = detailPostPageRepo;
   }

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

   public PostResponse getPostResponseByDate (Integer id ) {
       Posts post = getPostById(id);
       List<String> recommendPost = getRandomRecommendPost(post.getTopicrelatedid());
       Topic topic = detailPostPageRepo.getTopicById(post.getTopicrelatedid());
       Users user = detailPostPageRepo.getUser(post.getPostid());
       UserResponse userResponse = new UserResponse().setUserAvatar(user.getUseravatarurl())
               .setUserName(user.getUseraccount());
       List<Comments> comments = getAllNewComment(post.getPostid());

       List<CommentResponse> commentResponses = new ArrayList<>();

       for (Comments comments1 : comments){
           List<Reply> replies = getAllReply(comments1.getPostId());
           List<ReplyResponse> replyResponses = new ArrayList<>();

           for (Reply reply : replies) {
               Users user2 = detailPostPageRepo.getUser(reply.getUserownerid());
               UserResponse userResponse2 = new UserResponse().setUserAvatar(user2.getUseravatarurl())
                       .setUserName(user2.getUseraccount());
               ReplyResponse replyResponse = new ReplyResponse().setUserResponse(userResponse2)
                       .setReplyContent(reply.getReplycontent())
                       .setVoteCount(reply.getVotecount());
               replyResponses.add(replyResponse);

           }

           Users user1 = detailPostPageRepo.getUser(comments1.getCommentuserid());
           UserResponse userResponse1 = new UserResponse().setUserName(user1.getUseraccount())
                   .setUserAvatar(user1.getUseravatarurl());

            CommentResponse commentResponse = new CommentResponse().setCommentDesc(comments1.getCommentContent())
                    .setVoteCount(comments1.getVotecount())
                    .setUserResponse(userResponse1)
                    .setReplyResponses(replyResponses);
           commentResponses.add(commentResponse);
       }

       PostResponse postResponse = new PostResponse().setUserResponse(userResponse)
               .setCommentResponses(commentResponses)
               .setPostDesc(post.getPostquestion())
               .setTopic(topic.getTopicdesc())
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

        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comments comments1 : comments){
            List<Reply> replies = getAllReply(comments1.getPostId());
            List<ReplyResponse> replyResponses = new ArrayList<>();

            for (Reply reply : replies) {
                Users user2 = detailPostPageRepo.getUser(reply.getUserownerid());
                UserResponse userResponse2 = new UserResponse().setUserAvatar(user2.getUseravatarurl())
                        .setUserName(user2.getUseraccount());
                ReplyResponse replyResponse = new ReplyResponse().setUserResponse(userResponse2)
                        .setReplyContent(reply.getReplycontent())
                        .setVoteCount(reply.getVotecount());
                replyResponses.add(replyResponse);

            }

            Users user1 = detailPostPageRepo.getUser(comments1.getCommentuserid());
            UserResponse userResponse1 = new UserResponse().setUserName(user1.getUseraccount())
                    .setUserAvatar(user1.getUseravatarurl());

            CommentResponse commentResponse = new CommentResponse().setCommentDesc(comments1.getCommentContent())
                    .setVoteCount(comments1.getVotecount())
                    .setUserResponse(userResponse1)
                    .setReplyResponses(replyResponses);
            commentResponses.add(commentResponse);
        }

        PostResponse postResponse = new PostResponse().setUserResponse(userResponse)
                .setCommentResponses(commentResponses)
                .setPostRecommend(recommendPost)
                .setTopic(topic.getTopicdesc())
                .setPostDesc(post.getPostquestion());

        return postResponse;
    }

//    public CommentResponse getComment(CommentRequest commentRequest){
//       Comments comment = new Comments().setCommentContent(commentRequest.getCommentDesc())
//               .setPostId(commentRequest.getPostId()).setCommentuserid(commentRequest.getUserRequest().getUserId())
//               ;
//       Comments newComment = detailPostPageRepo.insertComment(comment);
//       UserResponse userResponse = new UserResponse().setUserName(commentRequest.getUserRequest().getUserName())
//               .setUserAvatar(commentRequest.getUserRequest().getUrlPic());
//       return new CommentResponse().setCommentDesc(newComment.getCommentContent())
//               .setUserResponse(userResponse);
//    }
public void getComment(CommentRequest commentRequest){
    Comments comment = new Comments();
    comment.setCommentContent(commentRequest.getCommentDesc());
    comment.setPostId(commentRequest.getPostId());
    comment.setCommentuserid(commentRequest.getUserId());
    detailPostPageRepo.insertComment(comment);

}

}
