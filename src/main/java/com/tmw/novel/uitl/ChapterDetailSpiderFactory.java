package com.tmw.novel.uitl;

import com.tmw.novel.api.IChapterDetailSpider;
import com.tmw.novel.sites.DefaultChapterDetailSpider;
import com.tmw.novel.website.NovelSiteEnum;

/**
 * 小说详情选择器
 *
 * @author TMW
 * @date 2020/8/17 14:54
 */
public final class ChapterDetailSpiderFactory {

    private ChapterDetailSpiderFactory() {
    }

    /**
     * 通过给定的url拿到实现了IChapterDetailSpider的具体实现类
     *
     * @param url 文章地址
     * @return
     */
    public static IChapterDetailSpider getChapterDetailSpider(String url) {
        IChapterDetailSpider spider = null;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
            case BXWX:
            case DDXS:
            case BQG:
                spider = new DefaultChapterDetailSpider();
                break;
            default:

        }
        return spider;
    }
}
