package com.xiaoshihua.thinkpad.uitest;


import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * Created by ThinkPad on 2016/8/22.
 */
public class PullPaser {

    public static List<Map<String, String>> getBookData(Context context, int resId) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }




        List<Map<String, String>> list = new ArrayList<>();
//        XmlResourceParser parser = context.getResources().getXml(resId);
//
//        try {
//            while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
//                if (parser.getEventType() == XmlPullParser.START_TAG) {
//                    String xmlName = parser.getName();
//                    if (xmlName.equals("books")) {
//                        Map<String, String> map = new HashMap<>();
//                        String price = parser.getAttributeValue(null, "price");
//                        map.put("price", price);
//                        String date = parser.getAttributeValue(1);
//                        map.put("date", date);
//                        map.put("bookName", parser.nextText());
//                        map.put("\n", "\n");
//                        list.add(map);
//                    }
//                }
//                parser.next();
//            }
//
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return list;
    }

}
