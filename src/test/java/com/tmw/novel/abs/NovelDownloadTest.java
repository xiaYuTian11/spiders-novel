package com.tmw.novel.abs;

import com.tmw.novel.config.Configuration;
import org.junit.jupiter.api.Test;

/**
 * @author TMW
 * @date 2020/8/17 16:09
 */
class NovelDownloadTest {

    @Test
    void download() {
        // String url = "https://www.230book.com/book/5483/";
        // String url = "https://www.bxwxorg.com/read/49247/";
        String url = "https://www.bxwxorg.com/read/131108/";
        Configuration configuration = new Configuration();
        configuration.setSize(20);
        configuration.setPath("C:\\Users\\Administrator\\Desktop\\download\\");
        NovelDownload novelDownload = new NovelDownload();

        novelDownload.download(url, configuration);

    }
}