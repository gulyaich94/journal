package com.gulyaich.boot.kafka.listener;

import com.gulyaich.boot.kafka.model.News;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "kafka_news", groupId = "news_group")
    public void consume(News news) {
        System.out.println("Consume " + news);
    }
}
