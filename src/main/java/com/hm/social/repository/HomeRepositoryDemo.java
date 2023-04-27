package com.hm.social.repository;

public class HomeRepositoryDemo {
}
//package com.hm.social.repository;
//
//
//import com.hm.social.data.request.PostRequest;
//import com.hm.social.tables.pojos.Posts;
//import org.jooq.DSLContext;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static com.hm.social.Tables.POSTS;
//
//@Repository
//public class HomeRepository {
//    private final DSLContext dslContext;
//
//    public HomeRepository(DSLContext dslContext) {
//        this.dslContext = dslContext;
//    }
//
//
//    public List<Posts> getAllPost() {
//        return dslContext.selectFrom(POSTS)
//                .fetchInto(Posts.class);
//    }
//
//    public Posts getNewPost(Integer id) {
//        return dslContext.select()
//                .from(POSTS)
//                .where(POSTS.ID.eq(id))
//                .fetchOneInto(Posts.class);
//    }
//
//
//    public Posts insert(Posts post) {
//        int execute = dslContext.insertInto(POSTS)
//                .set(POSTS.ID, post.getId())
//                .set(POSTS.USERID, post.getUserid())
//                .set(POSTS.DESC, post.getDesc())
//                .set(POSTS.QUESTION, post.getQuestion())
//                .set(POSTS.VOTE_NUMBER, post.getVoteNumber())
//                .execute();
//        return post;
//    }
//
//    public boolean update(PostRequest postModel) {
//        return dslContext.update(POSTS)
//                .set(POSTS.UPDATEDAT, LocalDateTime.now())
//                .set(POSTS.DESC, postModel.getDesc())
//                .set(POSTS.QUESTION, postModel.getTitle())
//                .where(POSTS.ID.eq(postModel.getId()))
//                .execute() == 1;
//    }
//
//    public boolean delete(Integer id) {
//        return dslContext.deleteFrom(POSTS)
//                .where(POSTS.ID.eq(id))
//                .execute() == 1;
//    }
//
//}


