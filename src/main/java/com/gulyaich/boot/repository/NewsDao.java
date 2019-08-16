package com.gulyaich.boot.repository;

import com.gulyaich.boot.entity.News;

import java.util.List;

public interface NewsDao {

    List<News> findAll();
    News save(News news);
}
