package com.tmw.novel.abs.chapter;

import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 文章内容抓取
 *
 * @author TMW
 * @since 2020/8/17
 */
@Slf4j
public abstract class AbstractSpider {
    /**
     * 抓取指定小说网站的内容
     *
     * @param url
     * @return
     */
    public String crawl(String url) {
        HttpGet httpGet = new HttpGet(url);
        RequestConfig build = RequestConfig.custom().setConnectTimeout(10 * 1000)
                .setConnectionRequestTimeout(10 * 1000)
                .setSocketTimeout(2_000).build();
        // 设置请求头
        httpGet.setHeader("User-Agent", "NovelSpider");
        httpGet.setConfig(build);
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            return EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
        } catch (Exception e) {
            log.error("异常原因：{}", e.getMessage());
        }
        return "";
    }
}
