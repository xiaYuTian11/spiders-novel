package com.tmw.novel.api;

import com.tmw.novel.entity.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * 爬取某个站点的小说列表
 *
 * @author TMW
 * @date 2020/8/18 9:54
 */
public interface INovelSpider {
    /**
     * 抓取某一个页面时最大的尝试次数3
     */
    public static final int MAX_TRY_TIMES = 3;

    /**
     * 给我一个URL，我就个你一堆的小说实体
     *
     * @param url
     * @param maxTryTimes 网页下载的最大次数（允许失败重试的次数）
     * @return
     */
    public List<Novel> getNovelList(String url, Integer maxTryTimes);

    public boolean hasNext();

    public String next();

    /**
     * List<Novel> novels = new ArrayList<>();
     * 假设novels中有很多的元素
     * for (int index = 0, size = novels.size(); index < size; index++) {
     * System.out.println("第" + index + "个元素是：" + novel);
     * }
     * for (Novel novel : novels) {
     * System.out.println(novel);
     * }
     * Iterator<Novel> iterator = novels.iterator();
     * while (iterator.hasNext()) {
     * Novel novel = iterator.next();
     * System.out.println(novel);
     * }
     */
    public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes);

}
