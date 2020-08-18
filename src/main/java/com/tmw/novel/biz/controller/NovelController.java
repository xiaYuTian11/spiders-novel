package com.tmw.novel.biz.controller;

import com.tmw.novel.api.IChapterDetailSpider;
import com.tmw.novel.api.IChapterSpider;
import com.tmw.novel.biz.serivce.NovelService;
import com.tmw.novel.entity.Chapter;
import com.tmw.novel.entity.ChapterDetail;
import com.tmw.novel.entity.JSONResponse;
import com.tmw.novel.uitl.ChapterDetailSpiderFactory;
import com.tmw.novel.uitl.ChapterSpiderFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author TMW
 * @date 2020/8/18 14:27
 */
@Controller
@RequestMapping("/novel")
public class NovelController {
    @Resource
    private NovelService service;

    @GetMapping(value = "/getChapterList")
    @ResponseBody
    public JSONResponse getChapterList(String url) {
        IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = spider.getChapterList(url);
        return JSONResponse.success(chapters);
    }

    @GetMapping("/getChapterDetail")
    @ResponseBody
    public JSONResponse getChapterDetail(String url) {
        IChapterDetailSpider spider = ChapterDetailSpiderFactory.getChapterDetailSpider(url);
        ChapterDetail detail = spider.getChapterDetail(url);
        return JSONResponse.success(detail);
    }

    @GetMapping("/search")
    @ResponseBody
    public JSONResponse getNovelListByKeyword(String keyword) {
        return JSONResponse.success(service.getNovelListByKeyword(keyword));
    }

    @GetMapping("/searchByPlatformId")
    @ResponseBody
    public JSONResponse getsNovelByKeyword(String keyword, int platformId) throws UnsupportedEncodingException {
        return JSONResponse.success(service.getNovelListByKeyword(keyword, platformId));
    }

    @GetMapping(value = "/showChapterList")
    public ModelAndView showChapterList(String url) {
        ModelAndView view = new ModelAndView();
        view.setViewName("chapterList");
        view.getModel().put("chapters", ChapterSpiderFactory.getChapterSpider(url).getChapterList(url));
        view.getModel().put("baseUrl", url);
        return view;
    }

    @GetMapping("/chapterDetailByBaseUrl")
    public ModelAndView showChapterDetail(String url, String baseUrl) {
        ModelAndView view = new ModelAndView();
        view.setViewName("chapterDetail");
        try {
            ChapterDetail detail = ChapterDetailSpiderFactory.getChapterDetailSpider(url).getChapterDetail(url);
            detail.setContent(detail.getContent().replaceAll("\n", "<br>"));
            view.getModel().put("chapterDetail", detail);
            view.getModel().put("isSuccess", true);
        } catch (Exception e) {
            e.printStackTrace();
            view.getModel().put("isSuccess", false);
        }
        view.getModel().put("baseUrl", baseUrl);
        return view;
    }
}
