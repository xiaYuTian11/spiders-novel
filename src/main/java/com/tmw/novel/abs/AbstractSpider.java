package com.tmw.novel.abs;

import com.tmw.novel.uitl.NovelSpiderUtil;
import com.tmw.novel.website.NovelSiteEnum;
import lombok.extern.slf4j.Slf4j;
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
    protected String crawl(String url) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse httpResponse = httpClient.execute(new HttpGet(url))) {
            return EntityUtils.toString(httpResponse.getEntity(), NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("charset"));
        } catch (Exception e) {
            log.error("异常原因：{}", e.getMessage());
        }
        return "";
    }
}
