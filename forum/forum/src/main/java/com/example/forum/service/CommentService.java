package com.example.forum.service;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.CommentRepository;
import com.example.forum.repository.ReportRepository;
import com.example.forum.repository.entity.Comment;
import com.example.forum.repository.entity.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    /*
     * コメント全件取得処理
     */
    public List<CommentForm> findAllComment() {
        List<Comment> results = commentRepository.findAllByOrderByIdDesc();
        List<CommentForm> comments = setCommentForm(results);
        return comments;
    }

    /*
     * DBから取得したコメントデータをFormに設定
     */
    private List<CommentForm> setCommentForm(List<Comment> results) {
       //空の変数(new)
        List<CommentForm> comments = new ArrayList<>();
        //
        for (int i = 0; i < results.size(); i++) {
            CommentForm comment = new CommentForm();
            Comment result = results.get(i);
            comment.setId(result.getId());
            comment.setContentId(result.getContentId());
            comment.setComment(result.getComment());
            comments.add(comment);
        }
        return comments;
    }

    /*
     * コメント追加
     */
    public void saveComment(CommentForm reqComment) {
        Comment saveComment = setCommentEntity(reqComment);
        commentRepository.save(saveComment);
    }

    /*
     * リクエストから取得したコメント情報をEntityに設定
     */
    //Comment型のsetCommentEntityメソッドを使う　引数はCommentForm型のreqComment
    private Comment setCommentEntity(CommentForm reqComment) {
        //Comment型のcomment変数にCommentメソッドを使って空箱を作った（new = 空箱）
        Comment comment = new Comment();
        //commentという変数にreqCommentに入っているIdをgetしたものをsetIdメソッドを使ってsetしている
        comment.setId(reqComment.getId());
        //commentという変数にreqCommentに入っているContentIdをgetしたものをsetContentIdメソッドを使ってsetしている
        comment.setContentId(reqComment.getContentId());
        comment.setComment(reqComment.getComment());
        comment.setCreateDate(reqComment.getCreateDate());
        comment.setUpdateDate(reqComment.getUpdateDate());
        return comment;
    }
}