package com.qiqi.handler;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by abner.zhang on 18/6/28.
 */
public class ThunderAccountGet2 {


    /**
     *  首页
     * @return
     */
    public List<String> getAccounts() {
        List<String> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.duxianghao.com/").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String title = doc.title();
        System.out.println(title);
        Calendar now = Calendar.getInstance();


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
        String rightTime = sdf.format(new Date());
        System.out.println(rightTime);


        System.out.println();

        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            if (link.text().contains("更新免费迅雷会员账号")) {
                System.out.println(link.attr("href"));
                list.addAll(getAccountByUrl("http://www.duxianghao.com"+link.attr("href")));
                System.out.println(JSONObject.toJSONString(list));
            }
        }
        return list;
    }


    /**
     * 具体有账号的页面
     * @param url
     * @return
     */
    public  List<String>  getAccountByUrl(String url){
        List<String> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String title = doc.title();
        Elements ps = doc.getElementsByTag("p");
        String[] split = null;
        for (Element p : ps) {
            if (p.html().contains("账号：")&&p.html().contains("密码：")) {
                split=p.html().split("<br>");
                for(String s:split){
                    if(s.contains("账号：")&&s.contains("密码：")){
                        System.out.println(s.trim());
                        list.add(s.trim());
                    }
                }
            }
        }
        return list;

    }



}
