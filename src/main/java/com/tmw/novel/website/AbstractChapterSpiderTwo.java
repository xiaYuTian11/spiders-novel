package com.tmw.novel.website;

/**
 * @author TMW
 * @date 2020/8/17 10:09
 */
// public class AbstractChapterSpiderTwo extends AbstractSpider implements IChapterSpider {
//     @Override
//     public List<Chapter> getChapterList(Website website) {
//         final String crawl = super.crawl(website.getBookUrl());
//         log.info("result: " + crawl);
//
//         final Document document = Jsoup.parse(crawl);
//         document.setBaseUri(website.getBookUrl());
//         final Elements elements = document.select(website.getRule());
//
//         List<Chapter> characterList = new ArrayList<>(500);
//         elements.forEach(element -> {
//             Chapter chapter = new Chapter();
//             chapter.setTitle(element.text());
//             chapter.setUrl(element.absUrl(website.getChapterFlag()));
//             characterList.add(chapter);
//         });
//         return characterList;
//     }
// }
