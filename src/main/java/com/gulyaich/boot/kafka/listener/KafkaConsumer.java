package com.gulyaich.boot.kafka.listener;

import com.gulyaich.boot.entity.News;
import com.gulyaich.boot.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private NewsService newsService;

    @KafkaListener(topics = "kafka_news", groupId = "news_group")
    public void consume(News news) {
        System.out.println("Message consumed " + news);
        newsService.save(news);
    }
}
