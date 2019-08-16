package com.gulyaich.boot.kafka.listener;

import com.gulyaich.boot.entity.News;
import com.gulyaich.boot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private NewsService newsService;

    @KafkaListener(topics = "kafka_news", groupId = "news_group")
    @SendTo("kafka_news_response")
    public News consume(News news) {
        System.out.println("Message consumed " + news);
        try {
            newsService.save(news);
            news.setStatus("DONE");
        } catch (Exception e) {
            System.out.println("News don't save " + news + ". Reason: " + e);
            news.setStatus("ERROR");
        }
        return news;
    }
}
