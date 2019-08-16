package com.gulyaich.boot.repository;

import com.gulyaich.boot.entity.News;
import com.gulyaich.boot.exception.NewsErrorSaveException;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static com.gulyaich.boot.jooq.Sequences.SEQ_NEWS_ID;
import static com.gulyaich.boot.jooq.Tables.NEWS;


@Repository("newsDaoJooq")
public class NewsDaoJooqImpl implements NewsDao {

    private static final String NEWS_SAVE_ERROR_MSG = "Ошибка при сохранении новости: ";

    private static final RecordMapper<Record, News> NEWS_RECORD_MAPPER = new RecordMapper<Record, News>() {
        @Override
        public News map(Record record) {
            News news = new News();
            news.setBody(record.getValue(NEWS.BODY));
            news.setDate(record.get(NEWS.DATE));
            news.setTitle(record.getValue(NEWS.TITLE));
            return news;
        }
    };

    @Autowired
    private DSLContext dsl;

    @Override
    public List<News> findAll() {
        return dsl.select().from(NEWS).orderBy(NEWS.DATE.desc()).fetch(NEWS_RECORD_MAPPER);
    }

    @Override
    public News save(News news) {
        Long nextID = dsl.nextval(SEQ_NEWS_ID);

        int isSuccessfulInsert = dsl.insertInto(NEWS)
                .set(NEWS.NEWS_ID, SEQ_NEWS_ID.nextval())
                .set(NEWS.TITLE, news.getTitle())
                .set(NEWS.BODY, news.getBody())
                .set(NEWS.DATE, new Timestamp(news.getDate().getTime()))
                .execute();

        if (isSuccessfulInsert == 0) {
            throw new NewsErrorSaveException(NEWS_SAVE_ERROR_MSG + news.getTitle() + " " + news.getDate());
        }

        return findById(nextID);
    }

    public News findById(Long id) {
        return dsl.select().from(NEWS).where(NEWS.NEWS_ID.eq(id)).fetchOne(NEWS_RECORD_MAPPER);
    }
}
