package com.tmw.novel.sites;

import com.tmw.novel.abs.AbstractCommonSortChapterSpider;

/**
 * 笔下文学章节，添加排序规则
 *
 * @author TMW
 * @date 2020/8/17 10:51
 */
public class BxwxChapterSpider extends AbstractCommonSortChapterSpider {
    // @Override
    // public List<Chapter> getChapterList(String url) {
    //     List<Chapter> chapters = super.getChapterList(url);
        // chapters.sort((o1, o2) -> {
        //     try {
        //         String o1Title = o1.getTitle().split(" ")[0];
        //         String o2Title = o2.getTitle().split(" ")[0];
        //         if (!Objects.equals(o1Title.length(), o2Title.length())) {
        //             return Integer.compare(o1Title.length(), o2Title.length());
        //         }
        //         return o1.getTitle().compareTo(o2.getTitle());
        //     } catch (Exception e) {
        //         return -1;
        //     }
        // });
    //     return chapters;
    // }
}
