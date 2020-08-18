package com.tmw.novel.biz.serivce;

import com.tmw.novel.entity.Novel;
import com.tmw.novel.entity.NovelSearch;

import java.util.List;

/**
 * @author TMW
 * @date 2020/8/18 14:28
 */
public interface NovelService {
    /**
     * 通过查询关键词，去数据库中查询结果，然后返回想要的内容
     *
     * @param keyword
     * @return
     */
    public List<NovelSearch> getNovelListByKeyword(String keyword);

    /**
     * 查找对应平台下面的小说
     *
     * @param keyword
     * @param platformId
     * @return
     */
    public List<Novel> getNovelListByKeyword(String keyword, int platformId);
}
