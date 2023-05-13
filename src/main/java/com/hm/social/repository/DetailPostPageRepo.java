package com.hm.social.repository;

import com.hm.social.data.request.PostRequest;
import com.hm.social.tables.pojos.*;
import com.hm.social.tables.records.CommentsRecord;
import com.hm.social.tables.records.ReplyRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.jooq.impl.DSL.rand;

@Repository
public class DetailPostPageRepo {
    private final DSLContext dslContext;

    public DetailPostPageRepo(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    private com.hm.social.tables.Posts posts = com.hm.social.tables.Posts.POSTS;
    private com.hm.social.tables.Comments comments = com.hm.social.tables.Comments.COMMENTS;
    private com.hm.social.tables.Users users = com.hm.social.tables.Users.USERS;
    private com.hm.social.tables.Reply reply = com.hm.social.tables.Reply.REPLY;
    public Posts getPostById(Integer id) {
        Posts post = dslContext.select()
                .from(posts)
                .where(posts.POSTID.eq(id))
                .fetchOneInto(Posts.class);
        return post;
    }

    public List<Comments> getAllNewComments(Integer id) {
        List<Comments> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POSTID.eq(id))
                .orderBy(comments.CREATEDAT)
                .fetchInto(Comments.class);
        return allCommentSortedByDate;
    }

    public List<Comments> getAllCommonComments(Integer id) {
        List<Comments> allCommentSortedByDate = dslContext.select()
                .from(comments)
                .where(comments.POSTID.eq(id))
                .orderBy(comments.VOTECOUNT)
                .fetchInto(Comments.class);
        return allCommentSortedByDate;
    }

    public Users getUser(Integer id) {
        Users user = dslContext.select()
                .from(users)
                .where(users.USERID.eq(id))
                .fetchOneInto(Users.class);
        return user;
    }

    public List<Reply> getAllReply(Integer id) {
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

    public Topic getTopicById(Integer id) {
        return dslContext.select()
                .from(com.hm.social.tables.Topic.TOPIC)
                .where(com.hm.social.tables.Topic.TOPIC.TOPICID.eq(id))
                .fetchOneInto(Topic.class);
    }

    public Comments getCommentById(Integer id) {
        return dslContext.select()
                .from(comments)
                .where(comments.COMMENTID.eq(id))
                .fetchOneInto(Comments.class);
    }

    public Reply getReplyById(Integer id) {
        return dslContext.select()
                .from(reply)
                .where(reply.REPLYID.eq(id))
                .fetchOneInto(Reply.class);
    }

    public Comments insertComment(Comments comment) {
        Posts post = getPostById(comment.getPostid());
        CommentsRecord commentsRecord =  dslContext.insertInto(comments, comments.COMMENTCONTENT, comments.CREATEDAT
                        , comments.POSTID, comments.COMMENTUSERID,
                        comments.POSTUSERID,comments.VOTECOUNT
                ,comments.REPLYCOUNT
                )
                .values(comment.getCommentcontent(), LocalDateTime.now(), comment.getPostid(),
                        comment.getCommentuserid(), comment.getPostuserid(),0,0)
                .returning(comments.COMMENTID)
                .fetchOne();


        int id = commentsRecord.getValue(comments.COMMENTID);
        return getCommentById(id);
    }
//public boolean insertComment (Comments comment){
//    Posts post = getPostById(comment.getPostId());
//    return dslContext.insertInto(comments)
//                .set(comments.COMMENT_CONTENT, comment.getCommentContent())
//                .set(comments.CREATED_AT, LocalDateTime.now())
//                .set(comments.COMMENTUSERID,comment.getCommentuserid())
//                .set(comments.POST_ID, comment.getPostId())
//                .set(comments.POSTUSER_ID,post.getUseownerid())
//                .execute()==1;
//
//}
public Reply insertReply(Reply reply1) {
    Posts post = getPostById(reply1.getPostid());
    ReplyRecord replyRecord = dslContext.insertInto(reply, reply.REPLYCONTENT,reply.COMMENTID,
                    reply.POSTID,reply.CREATEDAT,reply.USERCOMMENTID, reply.USEROWNERID
            ,reply.VOTECOUNT,reply.USERPOSTID)
            .values(reply1.getReplycontent(), reply1.getCommentid()
            ,reply1.getPostid(), LocalDateTime.now(), reply1.getUsercommentid()
            ,reply1.getUserownerid(),0,reply1.getUserpostid())
            .returning(reply.REPLYID)
            .fetchOne();

    int id = replyRecord.getValue(reply.REPLYID);
    return getReplyById(id);
}

public boolean updatePostCommentCount( Integer id){
        Posts posts1= getPostById(id);
        return dslContext.update(posts)
                   .set(posts.COMMENTCOUNT, posts1.getCommentcount()+1)
                   .where(posts.POSTID.eq(id))
                   .execute()==1 ;

}

public boolean updateCommentReplyCount (Integer id) {
        Comments comments1 = getCommentById(id);
  return dslContext.update(comments)
            .set(comments.REPLYCOUNT,comments1.getReplycount()+1)
          .where(comments.COMMENTID.eq(id))
            .execute()==1;
}

public Integer updateCommentVote(Comments comment){
        int commentvote = comment.getVotecount();
        commentvote= commentvote+1;
        dslContext.update(comments)
                .set(comments.VOTECOUNT,commentvote)
                .where(comments.COMMENTID.eq(comment.getCommentid()))
                .execute();
        return getCommentById(comment.getCommentid()).getVotecount();
}

    public Integer updateReplyVote(Reply replys1){
         dslContext.update(reply)
                .set(reply.VOTECOUNT,replys1.getVotecount()+1)
                .where(reply.REPLYID.eq(replys1.getReplyid()))
                .execute();
        return getReplyById(replys1.getReplyid()).getVotecount();
    }
}
