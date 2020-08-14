package com.tmw.novel.website;

import java.nio.charset.Charset;

/**
 * 小说网站父类
 *
 * @author TMW
 * @date 2020/8/14 15:40
 */
public interface Website {

    /**
     * 网站url
     *
     * @return
     */
    String getUrl();

    /**
     * 获取小说url
     *
     * @return
     */
    void setBookUrl(String bookUrl);

    /**
     * 获取小说url
     *
     * @return
     */
    String getBookUrl();

    /**
     * 字符集
     *
     * @return
     */
    Charset getCharset();

    /**
     * 目录获取筛选规则
     *
     * @return
     */
    String getRule();

    /**
     * 获取目录标志
     *
     * @return
     */
    default String getChapterFlag() {
        return "href";
    }

}
