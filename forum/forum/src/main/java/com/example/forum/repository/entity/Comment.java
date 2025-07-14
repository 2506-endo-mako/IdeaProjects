package com.example.forum.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @Column
    private String comment;

    //５ついる！
    @Column
    private int id;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;
}
