package com.tmw.novel.abs;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutionException;

/**
 * 下载完后处理
 *
 * @author TMW
 * @date 2020/8/17 16:04
 */
@Slf4j
public class DownLoadCallBack implements FutureCallback<String> {

    private ListenableFuture<String> listenableFuture;

    public DownLoadCallBack(ListenableFuture<String> listenableFuture) {
        this.listenableFuture = listenableFuture;
    }

    @Override
    public void onSuccess(@Nullable String result) {
        try {
            log.info("下载成功: {}", listenableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            log.info("下载失败：{}", e.getMessage());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        log.info("下载失败：{}", t.getMessage());
    }
}
