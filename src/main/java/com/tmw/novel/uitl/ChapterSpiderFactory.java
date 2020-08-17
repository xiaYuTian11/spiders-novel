package com.tmw.novel.uitl;

import com.tmw.novel.api.IChapterSpider;
import com.tmw.novel.sites.BxwxChapterSpider;
import com.tmw.novel.sites.DefaultChapterSpider;
import com.tmw.novel.website.NovelSiteEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 小说章节选择器
 *
 * @author TMW
 * @date 2020/8/17 14:47
 */
@Slf4j
public final class ChapterSpiderFactory {

    private ChapterSpiderFactory(){}

    /**
     * 通过给定的url，返回一个实现了IChapterSpider接口的实现类
     *
     * @param url 小说地址
     * @return
     */
    public static IChapterSpider getChapterSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum) {
            case BXWX:
                chapterSpider = new BxwxChapterSpider();
                break;
            case DDXS:
            case BQG:
                chapterSpider = new DefaultChapterSpider();
                break;
            default:
                log.error("该网站暂时未适配");
        }
        return chapterSpider;
    }
}
