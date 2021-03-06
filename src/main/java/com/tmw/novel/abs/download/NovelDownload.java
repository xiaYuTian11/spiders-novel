package com.tmw.novel.abs.download;

import com.tmw.novel.api.IChapterSpider;
import com.tmw.novel.api.INovelDownload;
import com.tmw.novel.config.Configuration;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.uitl.ChapterSpiderFactory;
import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 下载
 *
 * @author TMW
 * @date 2020/8/17 10:40
 */
@Slf4j
public class NovelDownload implements INovelDownload {
    @Override
    public String download(String url, Configuration config) {
        final IChapterSpider chapterSpider = ChapterSpiderFactory.getChapterSpider(url);
        final List<Chapter> chapterList = chapterSpider.getChapterList(url);
        // 分批次下载
        final int size = config.getSize();
        int maxThreadSize = (chapterList.size() - 1) / size + 1;

        Map<String, List<Chapter>> downloadTaskAlloc = new ConcurrentHashMap<>(maxThreadSize);
        for (int i = 0; i < maxThreadSize; i++) {
            int fromIndex = i * (size);
            int toIndex = i == maxThreadSize - 1 ? chapterList.size() - 1 : i * (config.getSize()) + config.getSize() - 1;
            downloadTaskAlloc.put(fromIndex + "-" + toIndex, chapterList.subList(fromIndex, toIndex));
        }
        // 通过这两段代码就可以创建缺失的路径
        String savePath = config.getPath() + File.separator + NovelSiteEnum.getEnumByUrl(url).getUrl();
        File file = new File(savePath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            log.info("创建文件夹：{}", mkdirs);
        }

        // 多线程下载
        // final ListeningExecutorService executorService = ThreadPoolUtil.getExecutorService(maxThreadSize);
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadSize);
        Set<String> keySet = downloadTaskAlloc.keySet();
        // List<ListenableFuture<String>> tasks = new ArrayList<>();
        List<Future<String>> tasks = new ArrayList<>();
        keySet.parallelStream().forEach(key -> {
            final Future<String> future = executorService.submit(new DownloadCallable(savePath + "/" + key + ".txt", downloadTaskAlloc.get(key)));
            // final ListenableFuture<String> future = executorService.submit(new DownloadCallable(savePath + "/" + key + ".txt", downloadTaskAlloc.get(key)));
            // Futures.addCallback(future, new DownLoadCallBack(future), executorService);
            tasks.add(future);
        });
        executorService.shutdown();
        tasks.parallelStream().forEach(future -> {
            try {
                log.info("下载成功：{}", future.get());
            } catch (InterruptedException | ExecutionException e) {
                log.error("下载失败：{}", e.getMessage());
            }
        });

        return NovelSpiderUtil.multiFileMerge(savePath, null, true);
    }

}


