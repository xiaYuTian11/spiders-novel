package com.tmw.novel.website;

import lombok.Data;

import java.nio.charset.Charset;

/**
 * 顶点文学
 *
 * @author TMW
 * @date 2020/8/14 16:04
 */
public class DDWXSite implements Website {
    private String bookUrl;

    @Override
    public String getUrl() {
        return "https://www.230book.com/";
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
        return Charset.forName("GBK");
    }

    @Override
    public String getRule() {
        return "#list li a";
    }
}
