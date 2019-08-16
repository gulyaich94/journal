/*
 * This file is generated by jOOQ.
 */
package com.gulyaich.boot.jooq;


import com.gulyaich.boot.jooq.tables.News;
import com.gulyaich.boot.jooq.tables.Student;
import com.gulyaich.boot.jooq.tables.records.NewsRecord;
import com.gulyaich.boot.jooq.tables.records.StudentRecord;

import javax.annotation.Generated;

import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.11"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<NewsRecord> CONSTRAINT_2 = UniqueKeys0.CONSTRAINT_2;
    public static final UniqueKey<StudentRecord> CONSTRAINT_B = UniqueKeys0.CONSTRAINT_B;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<NewsRecord> CONSTRAINT_2 = Internal.createUniqueKey(News.NEWS, "CONSTRAINT_2", News.NEWS.NEWS_ID);
        public static final UniqueKey<StudentRecord> CONSTRAINT_B = Internal.createUniqueKey(Student.STUDENT, "CONSTRAINT_B", Student.STUDENT.STUDENT_ID);
    }
}