package org.serjk.f451.dao;

import org.serjk.f451.model.News;

import java.util.Date;
import java.util.List;

/**
 * Created by bedash on 27.06.14.
 */
public interface NewsDAO {
    public void  addNew(News news);
    public List<News> getAllNews();
    public List<News> getNewsByDate(Date startDate, Date endDate);
    public News getNewsById(long id);
}
