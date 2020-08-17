package com.tmw.novel.api;

import com.tmw.novel.entity.ChapterDetail;

/**
 * @author TMW
 * @since 2020/8/17
 */
public interface IChapterDetailSpider {
    /**
     * 给我一个url，我就给你一个对应网站的章节内容实体
     *
     * @param url url
     * @return ChapterDetail
     */
    public ChapterDetail getChapterDetail(String url);
}
