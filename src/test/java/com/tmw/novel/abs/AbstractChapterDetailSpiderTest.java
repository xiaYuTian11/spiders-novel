package com.tmw.novel.abs;

import com.tmw.novel.entity.ChapterDetail;
import com.tmw.novel.sites.DefaultChapterDetailSpider;
import org.junit.jupiter.api.Test;

/**
 * @author TMW
 * @date 2020/8/17 10:29
 */
class AbstractChapterDetailSpiderTest {

    @Test
    void getChapterDetail1() {
        String url = "https://www.biquge.com.cn/book/32883/196857.html";
        AbstractChapterDetailSpider abstractChapterDetailSpider = new DefaultChapterDetailSpider();
        final ChapterDetail chapterDetail = abstractChapterDetailSpider.getChapterDetail(url);
        System.out.println(chapterDetail);
    }

    @Test
    void getChapterDetail2() {
        String url = "https://www.230book.com/book/5483/2065394.html";
        AbstractChapterDetailSpider abstractChapterDetailSpider = new DefaultChapterDetailSpider();
        final ChapterDetail chapterDetail = abstractChapterDetailSpider.getChapterDetail(url);
        System.out.println(chapterDetail);
    }

    @Test
    void getChapterDetail3() {
        String url = "https://www.bxwxorg.com/read/102487/1631633.html";
        AbstractChapterDetailSpider abstractChapterDetailSpider = new DefaultChapterDetailSpider();
        final ChapterDetail chapterDetail = abstractChapterDetailSpider.getChapterDetail(url);
        System.out.println(chapterDetail);
    }
}