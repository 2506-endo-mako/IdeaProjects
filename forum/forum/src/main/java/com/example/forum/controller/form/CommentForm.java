package com.example.forum.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentForm {

    private int id;
    private int contentId;
    private String comment;
    private Date createDate;
    private Date updateDate;

}

