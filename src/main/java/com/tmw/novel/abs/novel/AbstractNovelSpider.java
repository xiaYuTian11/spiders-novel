package com.tmw.novel.abs.novel;

import com.tmw.novel.api.INovelSpider;
import com.tmw.novel.entity.Novel;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;

/**
 * 一个抽象的小说列表抓取，已经实现了解析tr元素的方法
 *
 * @author TMW
 * @date 2020/8/18 9:58
 */
public class AbstractNovelSpider implements INovelSpider {
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
        // return getNovelList(url, INovelSpider.MAX_TRY_TIMES);
        return null;
    }

    @Override
    public List<Novel> getNovelList(String url, Integer maxTryTimes) {
        return null;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    @Override
    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
        return null;
    }
}
