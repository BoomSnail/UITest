package com.xiaoshihua.thinkpad.uitest;

import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends AppCompatActivity {
    private Button button;
    private Button jsonButton, buttonBean;
    private EditText jsonText, jsonBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new MyListener());
        buttonBean = (Button) findViewById(R.id.jsonBean);
        jsonBean = (EditText) findViewById(R.id.json2);

        jsonText = (EditText) findViewById(R.id.json1);


        jsonButton = (Button) findViewById(R.id.json解析);
        jsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> persons = new ArrayList<>();
                Person person = new Person(1, "肖仕华", "上海");
                Person person1 = new Person(2, "花卉四小", "广州");
                persons.add(person);
                persons.add(person1);
                String personsString = JsonTools.getJsonString("persons", persons);
                jsonText.setText(personsString);
            }
        });
        buttonBean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Person> persons = new ArrayList<>();
                Person person = new Person(1, "肖仕华", "上海");
                Person person1 = new Person(2, "花卉四小", "广州");
                persons.add(person);
                persons.add(person1);
                try {
                    StringBuilder stringBuilder = new StringBuilder();
                    JSONObject jsonObject = JsonTools.getJsonObject("persons",persons);
                    JSONArray jsonArray = jsonObject.getJSONArray("persons");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        int id = jsonArray.getJSONObject(i).getInt("id");
                        String name = jsonArray.getJSONObject(i).getString("name");
                        String address = jsonArray.getJSONObject(i).getString("address");
                        stringBuilder.append("id:" + id + " " + "name:" + name + "address:" + address + "\n");
                    }

                    jsonBean.setText(stringBuilder.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //获取资源解析器
            XmlResourceParser parser = getResources().getXml(R.xml.books);
            try {
                //获取字符串处理对象
                StringBuilder stringBuilder = new StringBuilder("");
                //判断是否处于资源文件的最后位置
                while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

                    if (parser.getEventType() == XmlPullParser.START_TAG) {
                        //获取标签名
                        String tagName = parser.getName();
                        //遇到book标签
                        if (tagName.equals("book")) {
                            //获取属性值
                            String bookPrice = parser.getAttributeValue(null, "price");
                            //将获取的添加进去
                            stringBuilder.append("价格:");
                            stringBuilder.append(bookPrice);
//价格：109.0    出版日期：2008  书名：疯狂java讲义
                            String bookDate = parser.getAttributeValue(1);
                            stringBuilder.append("   出版日期：");
                            stringBuilder.append(bookDate);
                            stringBuilder.append(" 书名:");
                            stringBuilder.append(parser.nextText());
                        }
                        stringBuilder.append("\n");
                    }
                    //解析器继续向下解析知道处于末尾
                    parser.next();
                }
                EditText editText = (EditText) findViewById(R.id.edit_other);
                editText.setText(stringBuilder.toString());
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
