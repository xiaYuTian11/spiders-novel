package com.tmw.novel.abs.novel;

import com.tmw.novel.entity.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * @author TMW
 * @date 2020/8/18 10:02
 */
public class NovelIterator implements Iterator<List<Novel>> {
    private Integer maxTryTimes;
    private AbstractNovelSpider abstractNovelSpider;

    public NovelIterator(AbstractNovelSpider abstractNovelSpider, Integer maxTryTimes) {
        this.maxTryTimes = maxTryTimes;
        this.abstractNovelSpider = abstractNovelSpider;
    }

    @Override
    public boolean hasNext() {
        return abstractNovelSpider.hasNext();
    }

    @Override
    public List<Novel> next() {
        List<Novel> novels = abstractNovelSpider.getNovelList(abstractNovelSpider.next(), maxTryTimes);
        return novels;
    }
}
