package org.serjk.f451.service.impl;

import org.serjk.f451.dao.NewsDAO;
import org.serjk.f451.model.News;
import org.serjk.f451.service.NewsService;
import org.serjk.f451.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by bedash on 27.06.14.
 */

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDAO newsDAO;

    @Override
    public void  addNews(News news){
        newsDAO.addNew(news);
    }

    @Override
    public List<News> getAllNews(){
        return  newsDAO.getAllNews();
    }

    @Override
    public List<News> getNewLastDay(){
        Date startDate = DateUtils.getStartOfDay();
        Date nowDate = DateUtils.getNow();

        return newsDAO.getNewsByDate(startDate,nowDate);
    }

    @Override
    public News getNewsById(long id){
        return  newsDAO.getNewsById(id);
    }

}
