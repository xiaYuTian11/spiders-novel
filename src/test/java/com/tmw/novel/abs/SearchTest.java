package com.tmw.novel.abs;

import com.tmw.novel.biz.serivce.NovelService;
import com.tmw.novel.biz.serivce.NovelServiceImpl;
import com.tmw.novel.entity.NovelSearch;
import com.tmw.novel.sites.BqgChapterSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author TMW
 * @date 2020/8/18 14:51
 */
public class SearchTest {

    @Test
    public void test() {
        String url = "https://www.biquge.com.cn/search.php?q=%s";
        BqgChapterSpider bqgChapterSpider = new BqgChapterSpider();
        final String crawl = bqgChapterSpider.crawl(String.format(url, "完美世界"));
        // System.out.println(crawl);
        Document doc = Jsoup.parse(crawl);
        doc.setBaseUri(url);
        final Elements select = doc.select(".result-game-item-detail");
        for (Element element : select) {
            System.out.println(element.getElementsByTag("a").get(0).text());
            System.out.println(element.getElementsByTag("a").get(0).absUrl("href"));
            System.out.println(element.getElementsByTag("p").get(0).text());
            System.out.println(element.getElementsByTag("p").get(1).text());
            System.out.println(element.getElementsByTag("p").get(2).text());
            System.out.println(element.getElementsByTag("p").get(3).text());
            System.out.println(element.getElementsByTag("p").get(4).text());
            System.out.println(element.getElementsByTag("a").get(1).absUrl("href"));
        }
    }

    @Test
    public void test02(){
        NovelService novelService = new NovelServiceImpl();
        final List<NovelSearch> searchList = novelService.getNovelListByKeyword("完美世界");
        for (NovelSearch novelSearch : searchList) {
            System.out.println(novelSearch);
        }

    }
}
