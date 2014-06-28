package org.serjk.f451.controllers;

import org.serjk.f451.error.ErrorInfo;
import org.serjk.f451.model.News;
import org.serjk.f451.model.User;
import org.serjk.f451.service.NewsService;
import org.serjk.f451.service.UserService;
import org.serjk.f451.service.impl.UserLoginService;
import org.serjk.f451.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

/**
 * Created by bedash on 27.06.14.
 */
@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    @Autowired
    UserLoginService userLoginService;

    @RequestMapping(value = "/user/rest/news/new", method = RequestMethod.POST)
    public @ResponseBody
    ErrorInfo addNews(@RequestParam(value ="title") String title,
                            @RequestParam(value ="summary") String summary,
                            @RequestParam(value ="description") String description){

        if(title!="" || summary != "" || description!=""){
            User user = userLoginService.getLoginUser();
            News news = new News();
            news.setDate(DateUtils.getNow());
            news.setAutorId(user.getId());
            news.setSummary(summary);
            news.setText(description);
            news.setTitle(title);
            newsService.addNews(news);
            return new ErrorInfo("news.add.succses","Новость успешно добавлена");
        }
        else {
            return new ErrorInfo("news.add.error","Не указаны поля новости");
        }
    }

    @RequestMapping(value = "/user/news")
    public String getNews( Model model){
        User loginUser  = userLoginService.getLoginUser();
        model.addAttribute("loginUser",loginUser);
        return "news";
    }

    @RequestMapping(value = "/user/rest/news/all")
    public @ResponseBody
    List<News> getAllNews(){
    return  newsService.getAllNews();
    }

    @RequestMapping(value = "/user/rest/news/last")
    public @ResponseBody
    List<News> getNewLastDay(){
        return  newsService.getNewLastDay();
    }

    @RequestMapping(value = "/user/news/find/{newsId}")
    public String getNewsById(@PathVariable("newsId") long newsId, Model model){
        User loginUser = userLoginService.getLoginUser();
        News news = newsService.getNewsById(newsId);
        User autor = userService.getUserById(news.getAutorId());
        model.addAttribute("autor",autor);
        model.addAttribute("news", news);
        model.addAttribute("loginUser",  loginUser);

        return  "newsdetals";
    }

}
