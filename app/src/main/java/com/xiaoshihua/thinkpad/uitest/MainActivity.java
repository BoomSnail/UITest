package com.xiaoshihua.thinkpad.uitest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    NotificationManager manager;
    PopupMenu popupMenu = null;
    String[] monster = new String[]{
            "孙悟空", "  猪八戒", "沙和尚"
    };
    Configuration configuration;
    private EditText editText;
    private Spinner spinner1;
    private Button button, popbutton, dialButton, animButton;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        configuration = getResources().getConfiguration();
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.edit);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        button = (Button) findViewById(R.id.button);
        popbutton = (Button) findViewById(R.id.popmenu);
        dialButton = (Button) findViewById(R.id.dial_button);
        imageView = (ImageView) findViewById(R.id.image);
        animButton = (Button) findViewById(R.id.anim);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.my_anim);
        animation.setFillAfter(true);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, OtherActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,
                        0, intent, 0);
                Notification notification = new Notification.Builder(MainActivity.this)
                        .setAutoCancel(true)
                        .setTicker("有新消息")
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("一条心通知")
                        .setContentText("恭喜您，涨薪资了")
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .build();

                manager.notify(1, notification);
            }
        });

        popbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu = new PopupMenu(MainActivity.this, view);
                getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.red:
                                Toast.makeText(MainActivity.this, "red", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.orange:
                                Toast.makeText(MainActivity.this, "orange", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.blue:
                                Toast.makeText(MainActivity.this, "blue", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        dialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                String data = "tel:15595260778";
                Uri uri = Uri.parse(data);
                intent.setData(uri);
                startActivity(intent);
            }
        });

        animButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                        imageView.startAnimation(animation);
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
                //切换动画，右边进入，左边退出
                overridePendingTransition(R.anim.enteranim,R.anim.outanim);
            }
        });

        initViews();

    }

    private void initViews() {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pattern pattern = Pattern.compile("[a-zA-Z]");
                Matcher matcher = pattern.matcher(charSequence);
                if (!matcher.matches()) {
                    Toast.makeText(MainActivity.this, "请输入字母", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,
                monster);
        spinner1.setAdapter(arrayAdapter);

        String content = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕" : "竖向屏幕";
        String mnc = configuration.mnc + "";
        String mcc = configuration.mcc + "";
        String touchScreen = configuration.touchscreen == Configuration.TOUCHSCREEN_NOTOUCH ?
                "无触摸屏" : "支持触摸屏";
        textView.setText(touchScreen);
    }
}
