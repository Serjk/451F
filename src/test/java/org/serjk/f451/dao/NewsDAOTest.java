package org.serjk.f451.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.serjk.f451.BaseTest;
import org.serjk.f451.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NewsDAOTest extends BaseTest {

    @Autowired
    private NewsDAO newsDAO;

    private long AUTHOR_ID = 42;
    private String TITLE = "title";
    private String SUMMATY = "summary";

    @Test
    public void testAddNews() {
        createNewsDefault();
        News news = newsDAO.getNewsById(1);
        assertEquals(news.getAuthorId(),AUTHOR_ID);
        assertEquals(news.getTitle(),TITLE);
        assertEquals(news.getSummary(),SUMMATY);
    }

    @Test
    public  void testGetAllNews() {
        createNewsDefault();
        createNewsDefault();
        createNewsDefault();
        List<News> newsList = newsDAO.getAllNews();
        assertEquals(newsList.size(),3);
    }

    @Test
    public void testGetNewsByDate() {
        createNewsDefault();
        Date date = new Date(System.currentTimeMillis()-10000);
        News news = new News();
        news.setAuthorId(AUTHOR_ID);
        news.setDate(date);
        news.setTitle(TITLE);
        news.setSummary(SUMMATY);
        newsDAO.addNew(news);
        Date startDate = new Date(System.currentTimeMillis()-1000);
        Date finishDate = new Date();
        List<News> newsList = newsDAO.getNewsByDate(startDate, finishDate);
        assertEquals(newsList.size(), 1);
    }

    private void createNewsDefault() {
        News news = new News();
        news.setAuthorId(AUTHOR_ID);
        news.setDate(new Date());
        news.setTitle(TITLE);
        news.setSummary(SUMMATY);
        newsDAO.addNew(news);
    }
}
