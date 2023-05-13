package com.hm.social.service;

import com.hm.social.data.response.hompage.CommentResponse;
import com.hm.social.data.response.hompage.PostResponse;
import com.hm.social.data.response.hompage.UserResponse;
import com.hm.social.repository.HomeRepository;
import com.hm.social.tables.pojos.Comments;
import com.hm.social.tables.pojos.Posts;
import com.hm.social.tables.pojos.Users;
import org.jooq.Null;
import org.jooq.impl.QOM;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository){
        this.homeRepository= homeRepository;
    }

//   public List<Comments> getAllComment(Integer id){
//        return homeRepository.getAllComments(id);
//   }
//   public Comments getComment (Integer id ) {
//        Posts post= homeRepository.getPostById(id);
//        return homeRepository.getcommonComment(post.getPostid());
//   }
//    public List<Comments> getallComment(){
//        List<Posts> posts = homeRepository.getAllPost();
//        List<Comments> comments = new ArrayList<>();
//        for(Posts post : posts){
//            Comments comment = homeRepository.getcommonComment(post.getPostid());
//            comments.add(comment);
//        }
//        return comments;
//}

   public List<PostResponse> getAllNewPost() {
      List<Posts> posts = homeRepository.getAllNewPost();

      List<PostResponse> postResponses = new ArrayList<>();


      for(Posts post : posts){
          Comments comment = homeRepository.getcommonComment(post.getPostid());
          if(comment == null){
              continue;
          };

          Users user = homeRepository.getUser(comment.getCommentuserid());
          if(user == null){
              continue;
          }
          Integer replyCount = homeRepository.replyCount(comment.getCommentid());
          CommentResponse commentResponse= new CommentResponse().setCommentDesc(comment.getCommentcontent())
                  .setCreatedAt(comment.getCreatedat())
                  .setVoteCount(comment.getVotecount())
                  .setReplyCount(replyCount);
          UserResponse userResponse = new UserResponse().setUserAvatar(user.getUseravatarurl())
                  .setUserName(user.getUsername());


          PostResponse postResponse = new PostResponse()
                  .setCommentResponse(commentResponse)
                  .setUserResponse(userResponse)
                  .setPostDesc(post.getPostquestion());

          postResponses.add(postResponse);

      }
      return postResponses;

  }

    public List<PostResponse> getAllCommonPost() {
        List<Posts> posts = homeRepository.getAllCommonPost();

        List<PostResponse> postResponses = new ArrayList<>();


        for(Posts post : posts){
            Comments comment = homeRepository.getcommonComment(post.getPostid());
            if(comment == null){
                continue;
            };
            Users user = homeRepository.getUser(comment.getCommentuserid());
            if(user==null){
                continue;
            }
            Integer replyCount = homeRepository.replyCount(comment.getCommentid());
            CommentResponse commentResponse= new CommentResponse().setCommentDesc(comment.getCommentcontent())
                    .setCreatedAt(comment.getCreatedat())
                    .setVoteCount(comment.getVotecount())
                    .setReplyCount(replyCount);
            UserResponse userResponse = new UserResponse().setUserAvatar(user.getUseravatarurl())
                    .setUserName(user.getUsername());


            PostResponse postResponse = new PostResponse()
                    .setCommentResponse(commentResponse)
                    .setUserResponse(userResponse)
                    .setPostDesc(post.getPostquestion());

            postResponses.add(postResponse);

        }
        return postResponses;

    }
}
