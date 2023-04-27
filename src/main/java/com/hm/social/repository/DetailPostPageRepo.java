package com.hm.social.repository;

import com.hm.social.tables.pojos.*;
import com.hm.social.tables.records.CommentsRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.jooq.impl.DSL.rand;

@Repository
public class DetailPostPageRepo {
    private final DSLContext dslContext;

    public DetailPostPageRepo(DSLContext dslContext){
        this.dslContext = dslContext;
    }

    private com.hm.social.tables.Posts posts = com.hm.social.tables.Posts.POSTS;
    private com.hm.social.tables.Comments comments = com.hm.social.tables.Comments.COMMENTS;
    private com.hm.social.tables.Users users = com.hm.social.tables.Users.USERS;
    private com.hm.social.tables.Reply reply = com.hm.social.tables.Reply.REPLY;

    public Posts getPostById(Integer id){
        Posts post = dslContext.select()
                .from(posts)
                .where(posts.POSTID.eq(id))
                .fetchOneInto(Posts.class);
        return post;
    }
    public List<Comments> getAllNewComments(Integer id){
        List<Comments> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POST_ID.eq(id))
                .orderBy(comments.CREATED_AT)
                .fetchInto(Comments.class);
        return allCommentSortedByDate;
    }

    public List<Comments> getAllCommonComments(Integer id){
        List<Comments> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POST_ID.eq(id))
                .orderBy(comments.VOTECOUNT)
                .fetchInto(Comments.class);
        return allCommentSortedByDate;
    }
    public Users getUser (Integer id) {
        Users user = dslContext.select()
                .from(users)
                .where(users.USERID.eq(id))
                .fetchOneInto(Users.class);
        return user;
    }

    public List<Reply> getAllReply(Integer id){
        List<Reply> allReplySortedByDate = dslContext.select()
                .from(reply)
                .where(reply.COMMENTID.eq(id))
                .orderBy(reply.CREATEDAT)
                .fetchInto(Reply.class);
        return allReplySortedByDate;
    }
    public List<Posts> getPostByTopic(Integer id) {
        return dslContext.selectFrom(posts)
                .where(posts.TOPICRELATEDID.eq(id))
                .orderBy(rand())
                .limit(2)
                .offset(0)
                .fetchInto(Posts.class);
    }
   public Topic getTopicById(Integer id ){
        return dslContext.select()
                .from(com.hm.social.tables.Topic.TOPIC)
                .where(com.hm.social.tables.Topic.TOPIC.TOPICID.eq(id))
                .fetchOneInto(Topic.class);
   }
   public Comments getCommentById(Integer id){
        return dslContext.select()
                .from(comments)
                .where(comments.COMMENTID.eq(id))
                .fetchOneInto(Comments.class);
   }
//   public Comments insertComment (Comments comment){
//        Posts post = getPostById(comment.getPostId());
//        int id  = dslContext.insertInto(comments,comments.COMMENT_CONTENT, comments.CREATED_AT
//                ,comments.POST_ID,comments.COMMENTUSERID,comments.POSTUSER_ID)
//                .values(comment.getCommentContent(), LocalDateTime.now(),comment.getPostId(),
//                        comment.getCommentuserid(),comment.getPostuserId())
//                .returning(comments.COMMENTID)
//                .execute();
//
//
//       return getCommentById(id);
//   }
public boolean insertComment (Comments comment){
    Posts post = getPostById(comment.getPostId());
    return dslContext.insertInto(comments)
                .set(comments.COMMENT_CONTENT, comment.getCommentContent())
                .set(comments.CREATED_AT, LocalDateTime.now())
                .set(comments.COMMENTUSERID,comment.getCommentuserid())
                .set(comments.POST_ID, comment.getPostId())
                .set(comments.POSTUSER_ID,post.getUseownerid())
                .execute()==1;

}
}
