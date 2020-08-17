package com.tmw.novel;

import com.tmw.novel.abs.AbstractChapterSpider;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.sites.BqgChapterSpider;
import com.tmw.novel.sites.BxwxChapterSpider;
import com.tmw.novel.sites.DdxsChapterSpider;
import com.tmw.novel.sites.DefaultChapterSpider;
import com.tmw.novel.website.Website;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author TMW
 * @date 2020/8/14 15:25
 */
class AbstractChapterSpiderTest {

    @Test
    void crawl() {
    }

    @Test
    void getChapterListBQG() {
        String url = "https://www.biquge.com.cn/book/23488/";
        AbstractChapterSpider abstractChapterSpider = new BqgChapterSpider();
        final List<Chapter> chapterList = abstractChapterSpider.getChapterList(url);
        chapterList.forEach(System.out::println);
    }

    @Test
    void getChapterListDDWX() {
        String url = "https://www.230book.com/book/5483/";
        AbstractChapterSpider abstractChapterSpider = new DdxsChapterSpider();
        final List<Chapter> chapterList = abstractChapterSpider.getChapterList(url);
        chapterList.forEach(System.out::println);
    }

    @Test
    void getChapterListBXWX() {
        String url = "https://www.bxwxorg.com/read/102487/";
        AbstractChapterSpider abstractChapterSpider = new BxwxChapterSpider();
        final List<Chapter> chapterList = abstractChapterSpider.getChapterList(url);
        chapterList.forEach(System.out::println);
    }
}