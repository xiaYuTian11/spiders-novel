package com.tmw.novel.api;

import com.tmw.novel.entity.Chapter;

import java.util.List;

/**
 * 目录 api
 *
 * @author TMW
 * @date 2020/8/14 15:00
 */
public interface IChapterSpider {

    /**
     * 获取章节目录列表
     *
     * @param website 小说网站
     * @return 目录列表
     */
    // List<Chapter> getChapterList(Website website);

    /**
     * 获取章节目录列表
     *
     * @param url url
     * @return 目录列表
     */
    List<Chapter> getChapterList(String url);
}
