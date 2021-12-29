package com.afc.springreact.post;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @Size(min=1, max=1000)
    @Column(length=1000)
    private String content;

    private Date timestamp;
}
