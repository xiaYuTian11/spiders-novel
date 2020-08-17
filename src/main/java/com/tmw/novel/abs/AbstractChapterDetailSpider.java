package com.tmw.novel.abs;

import com.tmw.novel.api.IChapterDetailSpider;
import com.tmw.novel.entity.ChapterDetail;
import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * 文章内容
 *
 * @author TMW
 * @since 2020/8/17
 */
public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {

    @Override
    public ChapterDetail getChapterDetail(String url) {
        try {
            String result = super.crawl(url);
            result = result.replace("&nbsp;", "  ").replace("<br />", "\n").replace("<br/>", "\n");
            Document doc = Jsoup.parse(result);
            doc.setBaseUri(url);
            Map<String, String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));

            // 拿标题内容
            String titleSelector = contexts.get("chapter-detail-title-selector");
            String[] splits = titleSelector.split(",");
            splits = parseSelector(splits);
            ChapterDetail detail = new ChapterDetail();
            detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            // 拿章节内容
            String contentSelector = contexts.get("chapter-detail-content-selector");
            detail.setContent(doc.select(contentSelector).first().text());

            // 拿前一章的地址
            String prevSelector = contexts.get("chapter-detail-prev-selector");
            splits = prevSelector.split(",");
            splits = parseSelector(splits);
            detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl(contexts.get("chapter-rule")));

            // 拿后一章的地址
            String nextSelector = contexts.get("chapter-detail-next-selector");
            splits = nextSelector.split(",");
            splits = parseSelector(splits);
            detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl(contexts.get("chapter-rule")));
            return detail;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 处理具体元素的下标索引,针对前一章后一章的 a 标签处理
     */
    private String[] parseSelector(String[] splits) {
        String[] newSplits = new String[2];
        if (splits.length == 1) {
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return newSplits;
        } else {
            return splits;
        }
    }

}
