package com.hm.social.service;

public class HomeSeviceDemo {
}
//package com.hm.social.service;
//
//
//import com.hm.social.data.request.PostRequest;
//import com.hm.social.data.response.HomePage.PostResponse;
//import com.hm.social.data.response.HomePage.UserResponse;
//import com.hm.social.repository.HomeRepository;
//import com.hm.social.tables.pojos.Posts;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class HomeService {
//    private final HomeRepository homeRepository;
//
//    public HomeService(HomeRepository homeRepository) {
//        this.homeRepository = homeRepository;
//    }
//
//
//    public List<Posts> getAllPost() {
//        return homeRepository.getAllPost();
//    }
//
//    public Posts getNewPost(Integer id) {
//        return homeRepository.getNewPost(id);
//    }
//
//    public PostResponse insert(PostRequest postModel) {
//        Posts post = new Posts();
//        post.setDesc(postModel.getDesc());
//        post.setUserid(1);
//        post.setQuestion(postModel.getTitle());
//        post.setId(postModel.getId());
//        post.setVoteNumber(0);
//        Posts newPost = homeRepository.insert(post);
//        return new PostResponse()
//                .setId(newPost.getId())
//                .setDesc(newPost.getDesc())
//                .setUser(new UserResponse().setUserId(1).setUserName("ssss"));
//    }
//
//    public void edit(PostRequest postModel) {
//        homeRepository.update(postModel);
//
//    }
//
//    public void delete(Integer id) {
//        homeRepository.delete(id);
//    }
//}


