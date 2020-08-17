package com.tmw.novel.uitl;

import com.tmw.novel.website.NovelSiteEnum;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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

    @SuppressWarnings("all")
    private static void init() {
        SAXReader reader = new SAXReader();
        try {
            final URL resource = NovelSpiderUtil.class.getClassLoader().getResource("config/Spider-Rule.xml");
            Document doc = reader.read(new File(resource.getPath()));
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

    /**
     * 多个文件合并为一个文件，合并规则：按文件名分割排序
     *
     * @param path        基础目录，该根目录下的所有文本文件都会被合并到 mergeToFile
     * @param mergeToFile 被合并的文本文件，这个参数可以为null,合并后的文件保存在path/merge.txt
     */
    public static void multiFileMerge(String path, String mergeToFile, boolean deleteThisFile) {
        mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
        File[] files = new File(path).listFiles((dir, name) -> name.endsWith(".txt"));
        assert files != null;
        Arrays.sort(files, (o1, o2) -> {
            int o1Index = Integer.parseInt(o1.getName().split("-")[0]);
            int o2Index = Integer.parseInt(o2.getName().split("-")[0]);
            return Integer.compare(o1Index, o2Index);
        });

        PrintWriter out = null;
        try {
            out = new PrintWriter(new File(mergeToFile), StandardCharsets.UTF_8);
            for (File file : files) {
                BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                String line;
                while ((line = bufr.readLine()) != null) {
                    out.println(line);
                }
                bufr.close();
            }
            // 删除文件
            if (deleteThisFile) {
                Arrays.stream(files).forEach(File::deleteOnExit);
            }
        } catch (IOException e1) {
            throw new RuntimeException(e1);
        } finally {
            assert out != null;
            out.close();
        }
    }

}
