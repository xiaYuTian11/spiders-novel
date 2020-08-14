package com.tmw.novel.website;

import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 笔趣阁
 *
 * @author TMW
 * @date 2020/8/14 15:44
 */
public class BQGSite implements Website {

    private String bookUrl;

    @Override
    public String getUrl() {
        return "https://www.biquge.com.cn/";
    }

    @Override
    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    @Override
    public String getBookUrl() {
        return bookUrl;
    }

    @Override
    public Charset getCharset() {
        return StandardCharsets.UTF_8;
    }

    @Override
    public String getRule() {
        return "#list dd a";
    }
}
