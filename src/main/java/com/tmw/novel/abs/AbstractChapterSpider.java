package com.tmw.novel.abs;

import com.tmw.novel.api.IChapterSpider;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * 章节目录抽象类
 *
 * @author TMW
 * @date 2020/8/14 15:04
 */
@Slf4j
public abstract class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    @Override
    public List<Chapter> getChapterList(String url) {
        final String crawl = super.crawl(url);
        final Document document = Jsoup.parse(crawl);
        document.setBaseUri(url);
        final Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
        final Elements elements = document.select(context.get("chapter-list-selector"));

        Set<Chapter> characterList = new HashSet<>(500);
        elements.forEach(element -> {
            Chapter chapter = new Chapter();
            chapter.setTitle(element.text());
            chapter.setUrl(element.absUrl(context.get("chapter-rule")));
            characterList.add(chapter);
        });
        return new ArrayList<>(characterList);
    }

}
