package com.qiqi.handler;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by abner.zhang on 18/6/28.
 */
public class ThunderAccountGet {

    /**
     * 获取几天范围内的所有账号
     * @param range
     * @return
     */
    public List<String> getAccountByRange(int range){
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.fenxs.com/category/gongxiang").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = new ArrayList<>();
        Calendar now = Calendar.getInstance();
        String rightTitle =null;
        now.add(Calendar.DAY_OF_YEAR,1);//先加一，后面减
        for(int i=0;i<range;i++){
            now.add(Calendar.DAY_OF_YEAR,-1);
            rightTitle = now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH)+1) + "月" + now.get(Calendar.DAY_OF_MONTH) + "日";
            list.add(rightTitle);
            list.addAll(getAcountByTime(now,doc));
        }
        return list;
    }


    /**
     * 首页
     * @param now
     * @param doc
     * @return
     */
    public List<String> getAcountByTime(Calendar now,Document doc){
        List<String> list = new ArrayList<>();
        String rightTitle = now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH)+1) + "月" + now.get(Calendar.DAY_OF_MONTH) + "日";
        System.out.println(rightTitle);
        Elements links = doc.getElementsByTag("a");
        for (Element link : links) {
            if (link.text().contains("迅雷会员")&&link.text().contains(rightTitle)) {
                System.out.println(link.attr("href"));
                list = getAccountByUrl(link.attr("href"));
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

            if (p.text().contains("账号")&&p.text().contains("密码")) {
                split=p.text().split(" ");
                for(String s:split){
                    s=MessageFormat.format("账号:{0},密码:{1}", s.substring(s.indexOf("账号")+2,s.indexOf("密码")),s.substring(s.indexOf("密码")+2));
                    System.out.println(s);
                    list.add(s);
                }
            }
        }
        return list;

    }



}
