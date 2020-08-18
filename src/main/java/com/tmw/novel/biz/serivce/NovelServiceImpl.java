package com.tmw.novel.biz.serivce;

import com.tmw.novel.biz.mapper.NovelMapper;
import com.tmw.novel.entity.Novel;
import com.tmw.novel.entity.NovelSearch;
import com.tmw.novel.sites.BqgChapterSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TMW
 * @date 2020/8/18 14:37
 */
@Service
public class NovelServiceImpl implements NovelService {
    @Autowired
    private NovelMapper mapper;

    @Override
    public List<NovelSearch> getNovelListByKeyword(String keyword) {
        String url = "https://www.biquge.com.cn/search.php?q=%s";
        BqgChapterSpider bqgChapterSpider = new BqgChapterSpider();
        final String crawl = bqgChapterSpider.crawl(String.format(url, keyword));
        Document doc = Jsoup.parse(crawl);
        List<NovelSearch> novelSearchList = new ArrayList<>(20);
        doc.setBaseUri(url);
        final Elements select = doc.select(".result-game-item-detail");
        for (Element element : select) {
            NovelSearch novelSearch = new NovelSearch();
            novelSearch.setName(element.getElementsByTag("a").get(0).text());
            novelSearch.setBookUrl(element.getElementsByTag("a").get(0).absUrl("href"));
            novelSearch.setAuthor(element.getElementsByTag("p").get(0).text());
            novelSearch.setIntroduction(element.getElementsByTag("p").get(1).text());
            novelSearch.setType(element.getElementsByTag("p").get(2).text());
            novelSearch.setUpdateTime(element.getElementsByTag("p").get(3).text());
            novelSearch.setNewChapter(element.getElementsByTag("p").get(4).text());
            novelSearch.setChapterUrl(element.getElementsByTag("a").get(1).absUrl("href"));
            novelSearchList.add(novelSearch);
        }

        return novelSearchList;
    }

    @Override
    public List<Novel> getNovelListByKeyword(String keyword, int platformId) {
        return null;
    }
}
