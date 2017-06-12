package com.sunx.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 */
public class Page {
    private Document doc = null;

    /**
     * 构造函数构造对象
     * @return
     */
    public static Page me(){
        return new Page();
    }
    /**
     * 初始化网页源码为dom树结构
     */
    public Page bind(String src){
        try {
            if(src != null && src.length() >= 0){
                doc = Jsoup.parse(src);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 抓取数据
     * @param cssQuery
     * @return
     */
    public Node $(String cssQuery){
        Elements eles = doc.select(cssQuery);
        return Node.me().$(eles);
    }

    /**
     * 根据集合抽取,抽取其中的第i个在抽取集合
     * @param cssQuery
     * @param index
     * @return
     */
    public Node $(String cssQuery,int index){
        Elements eles = doc.select(cssQuery);
        if(eles == null || eles.size() <= 0 || eles.size() > index)return null;
        return Node.me().$(eles);
    }

    /**
     * 1 获取标签的文本内容
     * <a>测试标签</a>
     *
     * Page.me().css('a')
     *
     * @param cssQuery
     * @return
     */
    public String css(String cssQuery){
        return css(cssQuery,"text","");
    }
    /**
     * 抽取数据
     * @param cssQuery
     * @param param
     * @return
     */
    public String css(String cssQuery,String param){
        return css(cssQuery,"attr",param);
    }
    /**
     * 抽取数据
     * @param cssQuery
     * @param method
     * @param param
     * @return
     */
    public String css(String cssQuery,String method,String param){
        try {
            Elements eles = doc.select(cssQuery);
            if(eles == null || eles.size() <= 0)return null;
            String result = (String) Ext.extractor(eles, method, param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 抽取根据cssQuery查询到的标签集合中的第i个子标签的文本内容
     * @param cssQuery
     * @return
     */
    public String css(String cssQuery,int index){
        return css(cssQuery,index,"text","");
    }
    /**
     * 抽取根据cssQuery查询到的标签集合中的第i个子标签的文本内容
     * @param cssQuery
     * @return
     */
    public String css(String cssQuery,int index,String param){
        return css(cssQuery,index,"attr",param);
    }

    /**
     * 解析数据
     * @param cssQuery
     * @param index
     * @param method
     * @param param
     * @return
     */
    public String css(String cssQuery,int index,String method,String param){
        try {
            Elements eles = doc.select(cssQuery);
            if(eles == null || eles.size() <= 0)return null;
            if(index <= 0 || index >= eles.size())return null;
            Element ele = eles.get(index);
            if(ele == null || ele.toString().length() <= 0)return null;
            String result = (String) Ext.extractor(ele.getAllElements(), method,param);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过filter过滤过滤过后的数据获取相应的文本内容
     * @param cssQuery
     * @param filter
     * @return
     */
    public String filter(String cssQuery,String filter){
        return filter(cssQuery,filter,"text","");
    }

    /**
     * 获取属性值
     * @param cssQuery
     * @param filter
     * @param param
     * @return
     */
    public String filter(String cssQuery,String filter,String param){
        return filter(cssQuery,filter,"attr",param);
    }

    /***
     * 获取数据
     * @param cssQuery
     * @param filter
     * @param method
     * @param param
     * @return
     */
    public String filter(String cssQuery,String filter,String method,String param){
        try{
            Elements eles = doc.select(cssQuery);
            if(eles == null || eles.size() <= 0)return null;
            Elements ef = eles.select(filter);
            if(ef != null){
                ef.remove();
            }
            return (String)Ext.extractor(eles, method, param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过filter过滤过滤过后的数据获取相应的文本内容
     * @param cssQuery
     * @param filter
     * @return
     */
    public String filter(String cssQuery,String filter,int index){
        return filter(cssQuery,filter,index,"text","");
    }

    /**
     * 获取属性值
     * @param cssQuery
     * @param filter
     * @param param
     * @return
     */
    public String filter(String cssQuery,String filter,int index,String param){
        return filter(cssQuery,filter,index,"attr",param);
    }

    /***
     * 获取数据
     * @param cssQuery
     * @param filter
     * @param method
     * @param param
     * @return
     */
    public String filter(String cssQuery,String filter,int index,String method,String param){
        try{
            Elements eles = doc.select(cssQuery);
            if(eles == null || eles.size() <= 0)return null;
            if(index <= -1 || index >= eles.size()){
                throw new Exception("超出解析集合的大小.");
            }
            Element ele = eles.get(index);
            Elements ef = ele.select(filter);
            if(ef != null){
                ef.remove();
            }
            return (String)Ext.extractor(ele.getAllElements(), method, param);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取网页源码
     * @return
     */
    public String html(){
        return doc.html();
    }

    /**
     * 获取谋个标签查到的网页源码
     * @param cssQuery
     * @return
     */
    public String html(String cssQuery){
        Elements eles = doc.select(cssQuery);
        if(eles == null || eles.size() <= 0)return null;
        return eles.html();
    }

    /**
     *
     * @param cssQuery
     * @param index
     * @return
     */
    public String html(String cssQuery,int index){
        return html(cssQuery,"",index);
    }

    /**
     * 通过过滤以后获取第index的网页源码
     * @param cssQuery
     * @param filter
     * @param index
     * @return
     */
    public String html(String cssQuery,String filter,int index){
        try{
            Elements eles = doc.select(cssQuery);
            if(eles == null || eles.size() <= 0)return null;
            if(index <= -1 || index >= eles.size()){
                throw new Exception("超出解析集合的大小.");
            }
            if(filter != null || filter.length() > 0){
                Elements f = eles.select(filter);
                if(f != null)f.remove();
            }
            return eles.get(index).html();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void clean(String cssQuery){
        try{
            Elements eles = doc.select(cssQuery);
            eles.empty();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除标签
     * @param cssQuery
     */
    public void remove(String cssQuery){
        try{
            Elements eles = doc.select(cssQuery);
            eles.remove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除第index标签
     * @param cssQuery
     * @param index
     */
    public void remove(String cssQuery,int index){
        try{
            Elements eles = doc.select(cssQuery);
            if(index <0 && index != -1)return;
            if(index > eles.size())return;
            eles.get(index).remove();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 给指定的标签添加内容
     * @param cssQuery
     * @param html
     */
    public void append(String cssQuery,String html){
        try{
            Elements eles = doc.select(cssQuery);
            eles.append(html);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 找到标签后,删除指定标签后再增加
     * @param cssQuery
     * @param removeCssQuery
     * @param html
     */
    public void append(String cssQuery,String html,String... removeCssQuery){
        try{
            Elements eles = doc.select(cssQuery);
            for(String removeCss : removeCssQuery){
                eles.select(removeCss).remove();
            }
            eles.append(html);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 判断标签是否已经存在
     * @param cssQuery
     * @return
     */
    public boolean exits(String cssQuery){
        Elements eles = doc.select(cssQuery);
        if(eles == null || eles.size() <= 0)return false;
        return true;
    }

    /**
     * 判断属性是否存在
     * @param cssQuery
     * @param attribute
     * @return
     */
    public boolean exits(String cssQuery,String attribute){
        Elements eles = doc.select(cssQuery);
        if(eles == null || eles.size() <= 0)return false;
        return eles.hasAttr(attribute);
    }

    /**
     * 获取title
     * @return
     */
    public String title(){
        return doc.title();
    }

    /**
     * 获取int类型的数据
     * @param cssQuery
     * @return
     */
    public int cssInt(String cssQuery){
        String css = css(cssQuery);
        if(css == null || css.length() <= 0 || css.replaceAll("[^0-9]","").length() == css.length())return -1;
        try{
            return Integer.parseInt(css);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }
}
