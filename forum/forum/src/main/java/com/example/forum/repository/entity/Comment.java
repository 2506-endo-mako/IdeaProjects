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

    //insertable = false → 特定のカラムをSQLのINSERT文に含めないように指示する属性
    //insertable = true → 特定のカラムをSQLのINSERT文で値が設定されて挿入される

    //updatable = false → 特定のカラムをSQLのUPDATE文に含めないように指示する属性
    //updatable = true → 特定のカラムをSQLのUPDATE文で値が設定されて挿入される
    @Column(name = "content_id", insertable = true, updatable = false)
    private int contentId;

    @Column(name = "create_date", insertable = false, updatable = false)
    private Date createDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;
}
