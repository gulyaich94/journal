package com.gulyaich.boot.repository;

import com.gulyaich.boot.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("newsDaoRepository")
public interface NewsDaoRepositoryImpl extends JpaRepository<News, Long>, NewsDao {

}
