package com.tmw.novel.abs.novel;

import com.tmw.novel.abs.chapter.AbstractSpider;
import com.tmw.novel.api.INovelSpider;
import com.tmw.novel.entity.Novel;
import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 一个抽象的小说列表抓取，已经实现了解析tr元素的方法
 *
 * @author TMW
 * @date 2020/8/18 9:58
 */
@Slf4j
public abstract class AbstractNovelSpider extends AbstractSpider implements INovelSpider {
    protected Element nextPageElement;
    /**
     * 下一页的url
     */
    protected String nextPage;

    /**
     * 默认的抓取方法，最多会尝试 {@link INovelSpider#MAX_TRY_TIMES} 次下载
     *
     * @param url
     * @return
     * @throws Exception
     */
    protected Elements getElements(String url) throws Exception {
        return getElements(url, INovelSpider.MAX_TRY_TIMES);
    }

    /**
     * 以指定次数的形式去解析对应网页
     *
     * @param url
     * @param maxTryTimes 如果为null， 则 默认是 {@link INovelSpider#MAX_TRY_TIMES}
     * @return
     * @throws Exception
     */

    protected Elements getElements(String url, Integer maxTryTimes) {
        maxTryTimes = maxTryTimes == null ? INovelSpider.MAX_TRY_TIMES : maxTryTimes;
        Elements trs;
        for (int i = 0; i < maxTryTimes; i++) {
            try {
                String result = super.crawl(url);
                Map<String, String> context = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
                String novelSelector = context.get("novel-selector");
                if (novelSelector == null)
                    throw new RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl() + ",url=" + url + "目前不支持抓取小说列表");
                Document doc = Jsoup.parse(result);
                doc.setBaseUri(url);
                trs = doc.select(novelSelector);

                String nextPageSelector = context.get("novel-next-page-selector");
                if (nextPageSelector != null) {
                    Elements nextPageElements = doc.select(nextPageSelector);
                    nextPageElement = nextPageElements == null ? null : nextPageElements.first();

                    if (nextPageElement != null) {
                        nextPage = nextPageElement.absUrl("href");
                    } else {
                        nextPage = "";
                    }
                }
                return trs;
            } catch (Exception e) {
                log.error("下载错误：{}", e.getMessage());
            }
        }
        throw new RuntimeException(url + ",尝试了" + maxTryTimes + "次依然下载失败了！");
    }

    @Override
    public boolean hasNext() {
        return !nextPage.isEmpty();
    }

    @Override
    public String next() {
        return nextPage;
    }

    @Override
    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
        nextPage = firstPage;
        return new NovelIterator(this, maxTryTimes);
    }
}
