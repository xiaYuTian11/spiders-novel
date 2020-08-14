package com.tmw.novel;

import com.tmw.novel.abs.AbstractChapterSpider;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.website.BQGSite;
import com.tmw.novel.website.DDWXSite;
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
        AbstractChapterSpider abstractChapterSpider = new AbstractChapterSpider();
        Website website = new BQGSite();
        website.setBookUrl(url);
        final List<Chapter> chapterList = abstractChapterSpider.getChapterList(website);
        chapterList.forEach(System.out::println);
    }

    @Test
    void getChapterListDDWX() {
        String url = "https://www.230book.com/book/5483/";
        AbstractChapterSpider abstractChapterSpider = new AbstractChapterSpider();
        Website website = new DDWXSite();
        website.setBookUrl(url);
        final List<Chapter> chapterList = abstractChapterSpider.getChapterList(website);
        chapterList.forEach(System.out::println);
    }
}