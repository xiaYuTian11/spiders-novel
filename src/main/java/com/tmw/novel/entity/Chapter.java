package com.tmw.novel.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 小说章节实体
 *
 * @author TMW
 * @date 2020/8/14 14:55
 */
public class Chapter implements Serializable {
    private static final long serialVersionUID = 3308908081700174964L;
    /**
     * 标题
     */
    private String title;
    /**
     * 地址
     */
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(title, chapter.title) &&
                Objects.equals(url, chapter.url);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, url);
    }

    public String getTitle() {
        return title;
    }

    public Chapter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Chapter setUrl(String url) {
        this.url = url;
        return this;
    }
}
