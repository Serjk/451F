package org.serjk.f451.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.serjk.f451.dao.NewsDAO;
import org.serjk.f451.model.News;
import org.serjk.f451.model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by bedash on 27.06.14.
 */
@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    private Session openSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void  addNew(News news){
        openSession().saveOrUpdate(news);
    }

    @Transactional
    public List<News> getAllNews(){
        Query query = openSession().createQuery("from News");
        return query.list();
    }

    @Transactional
    public List<News> getNewsByDate(Date startDate, Date endDate){
        Query query = openSession().createQuery("FROM News  AS n WHERE n.date>=:startDate AND n.date <=:endDate ");
        query.setParameter("startDate",  startDate);
        query.setParameter("endDate",  endDate);
        if (query.list().isEmpty()) return null; else
            return (List<News>) query.list();
    }

    @Transactional
    public News getNewsById(long id){
        Query query = openSession().createQuery("FROM News  AS n WHERE n.id=:id");
        query.setParameter("id",  id);
        if (query.list().isEmpty()) return null; else
            return (News) query.list().get(0);
    }

}
