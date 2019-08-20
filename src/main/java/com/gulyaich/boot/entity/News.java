package com.gulyaich.boot.entity;

import com.gulyaich.boot.kafka.model.NewsStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue
    @Column(name = "NEWS_ID")
    private Long newsId;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "BODY")
    private String body;

    @NotNull
    @Column(name = "DATE")
    private Date date;

    private NewsStatus status;

    public News() {
    }

    public News(String title, String body, Date date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getNewsId() {
        return newsId;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}