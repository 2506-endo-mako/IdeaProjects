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
    //自動で連番をつける
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String comment;

    //５ついる！
    @Column
    private int contentId;

    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;

    @Column(name = "create_date", insertable = false, updatable = false)
    private Date updateDate;
}
