package com.tmw.novel.abs;

import com.tmw.novel.entity.Chapter;

import java.util.List;

/**
 * 通用排序，按照url来切割
 *
 * @author TMW
 * @date 2020/8/17 15:28
 */
public abstract class AbstractCommonSortChapterSpider extends AbstractChapterSpider {
    @Override
    public List<Chapter> getChapterList(String url) {
        List<Chapter> chapters = super.getChapterList(url);
        chapters.sort((o1, o2) -> {
            try {
                String o1Url = o1.getUrl();
                String o2Url = o2.getUrl();
                String o1Index = o1Url.substring(o1Url.lastIndexOf('/') + 1, o1Url.lastIndexOf('.'));
                String o2Index = o2Url.substring(o2Url.lastIndexOf('/') + 1, o2Url.lastIndexOf('.'));
                return Integer.compare(Integer.parseInt(o1Index), Integer.parseInt(o2Index));
            } catch (Exception e) {
                return -1;
            }
        });
        return chapters;
    }
}
