package com.hm.social.repository;

import com.hm.social.tables.pojos.Comments;
import com.hm.social.tables.pojos.Posts;
import com.hm.social.tables.pojos.Reply;
import com.hm.social.tables.pojos.Users;
import org.jooq.Comment;
import org.jooq.DSLContext;

import org.jooq.impl.QOM;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeRepository {
    private final DSLContext dslContext;

    public HomeRepository (DSLContext dslContext){
        this.dslContext = dslContext;
    }

    private com.hm.social.tables.Posts posts = com.hm.social.tables.Posts.POSTS;
    private com.hm.social.tables.Comments comments = com.hm.social.tables.Comments.COMMENTS;

    private com.hm.social.tables.Users users = com.hm.social.tables.Users.USERS;
    private com.hm.social.tables.Reply reply = com.hm.social.tables.Reply.REPLY;

    public List<Posts> getAllNewPost(){
        List<Posts> allPostSortedByDate = dslContext.selectFrom(posts)
                .orderBy(posts.CREATEDAT.desc())
                .fetchInto(Posts.class);
        return allPostSortedByDate;
    }

    public List<Posts> getAllCommonPost(){
        List<Posts> allPostSortedByDate = dslContext.selectFrom(posts)
                .orderBy(posts.COMMENTCOUNT)
                .fetchInto(Posts.class);
        return allPostSortedByDate;
    }



    public List<Comments> getAllComments(Integer id){
        List<Comments> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POSTID.eq(id))
                .orderBy(comments.CREATEDAT)
                .fetchInto(Comments.class);
        return allCommentSortedByDate;
    }
   public Comments getcommonComment(Integer id) {
         Comments commonComment = dslContext.select()
                 .from(comments)
                 .where(comments.POSTID.eq(id))
                 .orderBy(comments.VOTECOUNT)
                 .limit(1)
                 .offset(0)
                 .fetchOneInto(Comments.class);
         return commonComment;
   }
  public Users getUser (Integer id) {
        Users user = dslContext.select()
                .from(users)
                .where(users.USERID.eq(id))
                .fetchOneInto(Users.class);
        return user;
  }
    public Integer replyCount(Integer id){
        return dslContext.fetchCount(dslContext.selectFrom(reply)
                .where(reply.COMMENTID.eq(id)));
    }


}
