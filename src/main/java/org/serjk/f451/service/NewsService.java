package org.serjk.f451.service;

import org.serjk.f451.model.News;

import java.util.List;

/**
 * Created by bedash on 27.06.14.
 */
public interface NewsService {
    public void  addNews(News news);
    public List<News> getAllNews();
    public List<News> getNewLastDay();
    public News getNewsById(long id);
}
