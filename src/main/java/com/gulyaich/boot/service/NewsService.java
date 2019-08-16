package com.gulyaich.boot.service;

import com.gulyaich.boot.entity.News;
import com.gulyaich.boot.repository.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    @Qualifier("newsDaoJooq")
    private NewsDao newsDao;

    public News save(News news) {
        return newsDao.save(news);
    }

    public List<News> getAllNews() {
        return newsDao.findAll();
    }
}
