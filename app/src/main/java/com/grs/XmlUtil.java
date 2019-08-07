package com.grs;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author:gaoruishan
 * @date:202019/3/21/11:34
 * @email:grs0515@163.com
 */
public class XmlUtil {

    private static final String TAG = XmlUtil.class.getSimpleName();

    public static String getXmlAppId(Context context) {
        try {
            InputStream is = context.getResources().getAssets().open("data/dcloud_control.xml");
            return getXmlAppId(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getXmlAppId(InputStream is) {

        try {
            //传入文件名：language.xml；用来获取流
//            InputStream is = getResources().getAssets().open("dcloud_control.xml");
            //首先创造：DocumentBuilderFactory对象
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            //获取：DocumentBuilder对象
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            //将数据源转换成：document         对象
            Document document = dBuilder.parse(is);
            //获取根元素
            Element element = (Element) document.getDocumentElement();
            //获取子对象的数值         读取lan标签的内容
            NodeList nodeList = element.getElementsByTagName("app");
            for (int i = 0; i < nodeList.getLength(); i++) {
                //获取对应的对象
                Element lan = (Element) nodeList.item(i);
                //lan.getAttribute("id")         获取id的值
                String appid = lan.getAttribute("appid");
                Log.e(TAG, "(WebActivity.java:115) " + appid);
                return appid;
                //获取name标签下的内容
//                tv_show.append(lan.getElementsByTagName("name").item(0).getTextContent() + "\n");
                //获取ide标签下的内容
//                tv_show.append(lan.getElementsByTagName("ide").item(0).getTextContent() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
}
