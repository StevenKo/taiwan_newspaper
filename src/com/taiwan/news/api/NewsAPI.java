package com.taiwan.news.api;

import java.util.ArrayList;
import java.util.Date;

import com.taiwan.news.entity.Category;
import com.taiwan.news.entity.News;

public class NewsAPI {
    final int APPLE     = 0;
    final int FREEDOM   = 1;
    final int UNION     = 2;
    final int CHINATIME = 3;
    final int ECONOMY   = 4;

    public static ArrayList<Category> getSourceCategory(int source) {
        Category c1 = new Category(1, "娛樂");
        Category c2 = new Category(2, "財經");
        ArrayList<Category> categories = new ArrayList();
        categories.add(c1);
        categories.add(c2);
        return categories;
    }

    public static ArrayList<News> getPromotionNews(int source) {
        ArrayList<News> news = new ArrayList();
        ArrayList<String> pics = new ArrayList<String>();
        pics.add("http://twimg.edgesuite.net/images/ReNews/20121126/420_2c70a27cecf7a6e96312b605b5e5b130.jpg");
        pics.add("http://twimg.edgesuite.net/images/ReNews/20121126/420_2c70a27cecf7a6e96312b605b5e5b130.jpg");
        News n1 = new News(1, "蘋果", pics, "tests tests", 1, new Date(), "娛樂", "title1");
        News n2 = new News(2, "蘋果", pics, "tests 財經", 1, new Date(), "財經", "title2");
        news.add(n1);
        news.add(n2);
        return news;
    }

    public static ArrayList<News> getCateroyNews(int source, int category, int page) {
        ArrayList<News> news = new ArrayList();
        ArrayList<String> pics = new ArrayList<String>();
        pics.add("http://twimg.edgesuite.net/images/ReNews/20121126/420_2c70a27cecf7a6e96312b605b5e5b130.jpg");
        pics.add("http://twimg.edgesuite.net/images/ReNews/20121126/420_2c70a27cecf7a6e96312b605b5e5b130.jpg");
        News n1 = new News(1, "蘋果", pics, "tests tests 娛樂", 1, new Date(), "娛樂", "title1");
        News n2 = new News(2, "蘋果", pics, "tests 娛樂", 1, new Date(), "娛樂", "title2");
        news.add(n1);
        news.add(n2);
        return news;
    }

    public static News getNewsDetail(int newsId) {
        ArrayList<String> pics = new ArrayList<String>();
        pics.add("http://twimg.edgesuite.net/images/ReNews/20121126/420_2c70a27cecf7a6e96312b605b5e5b130.jpg");
        pics.add("http://twimg.edgesuite.net/images/ReNews/20121126/420_2c70a27cecf7a6e96312b605b5e5b130.jpg");
        News n2 = new News(2, "蘋果", pics, "tests 娛樂", 1, new Date(), "娛樂", "title2");
        return n2;
    }
}
