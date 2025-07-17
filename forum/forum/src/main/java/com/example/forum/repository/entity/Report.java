package com.example.forum.repository.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "report")
@Getter
@Setter
public class Report {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content", insertable = true, updatable = true)
   //@NotBlank
    private String content;

    @Column(name = "create_date", insertable = false, updatable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date", insertable = false, updatable = false)
    private Date updateDate;

}
