package com.gulyaich.boot.kafka.listener;

import com.gulyaich.boot.entity.News;
import com.gulyaich.boot.kafka.model.NewsResponse;
import com.gulyaich.boot.kafka.model.NewsStatus;
import com.gulyaich.boot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Value("${kafka.topic.news.request}")
    private String requestTopic;

    @Autowired
    private NewsService newsService;

    @KafkaListener(topics = "${kafka.topic.news.request}", containerFactory = "requestListenerContainerFactory")
    @SendTo
    public NewsResponse tryToSaveNews(News news) {
        NewsResponse newsResponse = createNewsResponse(news);
        try {
            newsService.save(news);
            newsResponse.setNewsStatus(NewsStatus.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            newsResponse.setNewsStatus(NewsStatus.ERROR);
            newsResponse.setErrorReason(e.toString());
        }
        return newsResponse;
    }

    private NewsResponse createNewsResponse(News news) {
        NewsResponse newsResponse = new NewsResponse();
        newsResponse.setTitle(news.getTitle());
        newsResponse.setBody(news.getBody());
        newsResponse.setDate(news.getDate());
        return newsResponse;
    }
}
