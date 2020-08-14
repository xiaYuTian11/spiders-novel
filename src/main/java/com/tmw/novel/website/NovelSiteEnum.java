package com.tmw.novel.website;

/**
 * 已经被支持的小说网站枚举
 *
 * @author TMW
 * @date 2020/8/14 16:21
 */
public enum NovelSiteEnum {
    /**
     * 顶点小说
     */
    DDXS(1, "230book.com"),
    /**
     * 笔趣阁
     */
    BQG(2, "biquge.com.cn");
    private int id;
    private String url;

    private NovelSiteEnum(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static NovelSiteEnum getEnumById(int id) {
        switch (id) {
            case 1:
                return DDXS;
            case 2:
                return BQG;
            default:
                throw new RuntimeException("id=" + id + "是不被支持的小说网站");
        }
    }

    public static NovelSiteEnum getEnumByUrl(String url) {
        for (NovelSiteEnum novelSiteEnum : values()) {
            if (url.contains(novelSiteEnum.url)) {
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("url=" + url + "是不被支持的小说网站");
    }
}
