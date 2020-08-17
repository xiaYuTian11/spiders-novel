package com.tmw.novel.api;

import com.tmw.novel.config.Configuration;

/**
 * 小说下载
 *
 * @author TMW
 * @since TODO: 2020/8/17
 */
public interface INovelDownload {
    /**
     * 比如说我下载到D:/novel/biquge.tw/完美世界/完美世界.txt
     *
     * @param url 这个url是指某本小说的章节列表页面
     * @return String
     */
    public String download(String url, Configuration config);
}
