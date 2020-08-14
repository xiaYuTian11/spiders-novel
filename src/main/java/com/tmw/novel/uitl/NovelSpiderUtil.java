package com.tmw.novel.uitl;

import com.tmw.novel.website.NovelSiteEnum;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小说工具类
 *
 * @author TMW
 * @date 2020/8/14 16:16
 */
public class NovelSpiderUtil {

    private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<>();
    static {
        init();
    }

    @SuppressWarnings("unchecked")
    private static void init() {
        SAXReader reader = new SAXReader();
        try {

            Document doc = reader.read(new File("resources/Spider-Rule.xml"));
            Element root = doc.getRootElement();
            List<Element> sites = root.elements("site");
            for (Element site : sites) {
                List<Element> subs = site.elements();
                Map<String, String> subMap = new HashMap<>();
                for (Element sub : subs) {
                    String name = sub.getName();
                    String text = sub.getTextTrim();
                    subMap.put(name, text);
                }
                CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")), subMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拿到对应网站的解析规则
     */
    public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
        return CONTEXT_MAP.get(novelSiteEnum);
    }

}
