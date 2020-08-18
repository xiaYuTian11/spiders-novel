package com.tmw.novel.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author TMW
 * @date 2020/8/18 15:27
 */
@Data
@ToString
public class NovelSearch {

    private String name;
    private String bookUrl;
    private String author;
    private String introduction;
    private String type;
    private String updateTime;
    private String newChapter;
    private String chapterUrl;
}
