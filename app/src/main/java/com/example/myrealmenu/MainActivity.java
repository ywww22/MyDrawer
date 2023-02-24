package com.example.myrealmenu;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    ImageButton btnMenu;
    LinearLayout btnFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu = findViewById(R.id.btnMenu);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout ll = (LinearLayout)inflater.inflate(R.layout.activity_menu, null);

        AtomicInteger state = new AtomicInteger(); // 메뉴 상태 - 0이면 닫혀있는 상태, 1이면 열려있는 상태

        final Animation animOpen = AnimationUtils.loadAnimation(this,R.anim.anim_translate_left);
        final Animation animClose = AnimationUtils.loadAnimation(this,R.anim.anim_translate_right);

        btnMenu.setOnClickListener(view -> {
            if(state.get() == 0) {
                Log.v("yes", "yes");
                LinearLayout.LayoutParams paramll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                addContentView(ll, paramll);
                ll.startAnimation(animOpen);
                //ll.setBackgroundColor(Color.parseColor("#70000000"));
                state.set(1);

                btnFirst = findViewById(R.id.first);
                btnFirst.setOnClickListener(fistview ->{
                    Toast.makeText(getApplicationContext(), "Selected First", Toast.LENGTH_LONG).show();
                });
            } else {
                Log.v("yes", "no");
                ll.startAnimation(animClose);
                ((ViewManager)ll.getParent()).removeView(ll);
                state.set(0);
            }
        });
    }

}