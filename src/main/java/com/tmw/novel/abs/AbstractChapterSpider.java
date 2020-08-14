package com.tmw.novel.abs;

import com.tmw.novel.api.IChapterSpider;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import com.tmw.novel.website.Website;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 章节目录抽象类
 *
 * @author TMW
 * @date 2020/8/14 15:04
 */
@Slf4j
public class AbstractChapterSpider implements IChapterSpider {
    /**
     * 抓取数据
     *
     * @param url
     * @return
     */
    protected String crawl(String url) {
        final HttpGet httpGet = new HttpGet(url);
        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                final CloseableHttpResponse response = httpClient.execute(httpGet);
        ) {
            return EntityUtils.toString(response.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "";
    }

    @Override
    public List<Chapter> getChapterList(String url) {
        final String crawl = crawl(url);
        final Document document = Jsoup.parse(crawl);
        document.setBaseUri(url);
        final Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
        final Elements elements = document.select(context.get("chapter-list-selector"));

        List<Chapter> characterList = new ArrayList<>(500);
        elements.forEach(element -> {
            Chapter chapter = new Chapter();
            chapter.setTitle(element.text());
            chapter.setUrl(element.absUrl(context.get("chapter-rule")));
            characterList.add(chapter);
        });
        return characterList;
    }

    @Override
    public List<Chapter> getChapterList(Website website) {
        final String crawl = crawl(website.getBookUrl());
        log.info("result: " + crawl);

        final Document document = Jsoup.parse(crawl);
        document.setBaseUri(website.getBookUrl());
        final Elements elements = document.select(website.getRule());

        List<Chapter> characterList = new ArrayList<>(500);
        elements.forEach(element -> {
            Chapter chapter = new Chapter();
            chapter.setTitle(element.text());
            chapter.setUrl(element.absUrl(website.getChapterFlag()));
            characterList.add(chapter);
        });
        return characterList;
    }
}
