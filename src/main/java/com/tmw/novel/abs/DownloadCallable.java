package com.tmw.novel.abs;

import com.tmw.novel.api.IChapterDetailSpider;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.entity.ChapterDetail;
import com.tmw.novel.uitl.ChapterDetailSpiderFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author TMW
 * @date 2020/8/17 15:48
 */
@Slf4j
public class DownloadCallable implements Callable<String> {
    private List<Chapter> chapterList;
    private String path;
    private int tryTimes;

    public DownloadCallable(String path, List<Chapter> chapterList) {
        this.path = path;
        this.chapterList = chapterList;
        this.tryTimes = 3;
    }

    public DownloadCallable(String path, List<Chapter> chapterList, int tryTimes) {
        this.path = path;
        this.chapterList = chapterList;
        this.tryTimes = tryTimes;
    }

    @Override
    public String call() {
        try (
                PrintWriter out = new PrintWriter(new File(path), StandardCharsets.UTF_8);
        ) {
            for (Chapter chapter : chapterList) {
                for (int i = 0; i < tryTimes; i++) {
                    IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(chapter.getUrl());
                    try {
                        ChapterDetail detail = spider.getChapterDetail(chapter.getUrl());
                        out.println(detail.getTitle());
                        out.println(detail.getContent());
                        break;
                    } catch (RuntimeException e) {
                        System.err.println("尝试第[" + (i + 1) + "/" + tryTimes + "]次下载失败了！");
                    }
                }
            }
        } catch (Exception e) {
            log.error("下载错误：{}", e.getMessage());
        }
        return path;
    }
}
