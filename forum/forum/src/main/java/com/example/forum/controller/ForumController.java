package com.example.forum.controller;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.controller.form.ReportForm;
import com.example.forum.service.CommentService;
import com.example.forum.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class ForumController {
    @Autowired
    ReportService reportService;
    //使いまわしできる
    @Autowired
    CommentService commentService;

    /*
     * 投稿内容表示処理　→　日付の情報も取得したい
     */
    @GetMapping
    //@RequestParam（name）型　変数　→　ブラウザからのリクエストの値（パラメータ）を取得することができるアノテーション。
    public ModelAndView top(@RequestParam(name="start_date", required=false) String startDate
            ,@RequestParam(name="end_date", required=false) String endDate) {
        ModelAndView mav = new ModelAndView();
        // 投稿を全件取得　findAllは全件取得　findAll　→　SQL文の「select * from report;」
        List<ReportForm> contentData = reportService.findAllReport(startDate,endDate);
        // 画面遷移先を指定
        mav.setViewName("/top");
        // 投稿データオブジェクトを保管
        mav.addObject("contents", contentData);

        //コメント返信内容表示処理
        List<CommentForm> commentData = commentService.findAllComment();
        // 画面遷移先を指定
        mav.setViewName("/top");
        // コメント返信オブジェクトを保管
        mav.addObject("comments", commentData);
        // コメント返信用に、空のcommentFormを準備
        mav.addObject("formModel", new CommentForm());

        return mav;
    }


    /*
     * 新規投稿画面表示
     */
    @GetMapping("/new")
    //@Validated String contentをいれる？
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        ReportForm reportForm = new ReportForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", reportForm);
        return mav;
    }

    /*
     * 新規投稿処理
     */
    @PostMapping("/add")
    public ModelAndView addContent(@ModelAttribute("formModel") ReportForm reportForm){
        // 投稿をテーブルに格納
        reportService.saveReport(reportForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * 投稿削除処理
     */
    @DeleteMapping("/delete/{id}")
    public ModelAndView deleteContent(@PathVariable Integer id) {
        // 投稿をテーブルに格納
        reportService.deleteReport(id);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * コメントの返信削除処理
     */
    //deleteメソッド→＠deleteに飛んできた
    @DeleteMapping("/comment.delete/{id}")
    //@PathVariable Integer id　でURLパス内の変数をメソッドの引数にマッピングするためのアノテーション
    public ModelAndView DeleteComment(@PathVariable Integer id) {
        // コメント返信のidをServiceに送る
        commentService.deleteComment(id);
        // top画面？？？へリダイレクト
        return new ModelAndView("redirect:/");
    }


    /*
     * 編集画面表示処理
     */
    @GetMapping("/edit/{id}")
    public ModelAndView editContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        // 編集する投稿を取得
        ReportForm report = reportService.editReport(id);
        // 編集する投稿をセット
        mav.addObject("formModel", report);
        // 画面遷移先を指定
        mav.setViewName("/edit");
        return mav;
    }

    /*
     * コメント返信の編集画面表示処理
     */
    @GetMapping("/comment.edit/{id}")
    public ModelAndView commentEditContent(@PathVariable Integer id) {
        ModelAndView mav = new ModelAndView();
        // 編集する投稿を取得
        CommentForm comment = commentService.editComment(id);
        // 編集する投稿をセット
        mav.addObject("formModel", comment);
        // 画面遷移先を指定
        mav.setViewName("/comment.edit");
        return mav;
    }



    /*
     * 編集処理
     */
    @PutMapping("/update/{id}")
    public ModelAndView updateContent (@PathVariable Integer id, @ModelAttribute("formModel") ReportForm report) {
        // UrlParameterのidを更新するentityにセット
        report.setId(id);
        // 編集した投稿を更新
        reportService.saveReport(report);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

    /*
     * コメント返信の編集処理
     */
    @PutMapping("/commentUpdate/{id}")
    //@PathVariable Integer id　でURLパス内の変数をメソッドの引数にマッピングするためのアノテーション
    //@ModelAttribute　リクエストパラメータをオブジェクトにマッピングし、ビューに渡すためのアノテーション
    //　→　上記のアノテーションは、自動的に、送られてきたCommentForm型のデータを箱に格納し、ビューに渡す機能まで兼ね備えている
    public ModelAndView commentUpdateContent (@PathVariable Integer id, @ModelAttribute("formModel") CommentForm comment) {
        // UrlParameterのidを更新するentityにセット
        comment.setId(id);
        // 編集したコメント返信を更新（save）
        commentService.saveComment(comment);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }



    /*
     * コメントの返信投稿
     */
    @PostMapping("/commentAdd")
    public ModelAndView addComment(@ModelAttribute("formModel") CommentForm commentForm){
        // 投稿をテーブルに格納
        commentService.saveComment(commentForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/");
    }

}

