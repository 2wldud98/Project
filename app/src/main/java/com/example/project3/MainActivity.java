package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    ViewFlipper flipper;
    ToggleButton toggle_Flipping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button moveButton = (Button)findViewById(R.id.btn1);
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.btn_link);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kosaf.go.kr/"));
                startActivity(intent);
            }
        });

        flipper = (ViewFlipper) findViewById(R.id.flipper);
        Animation showIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        flipper.setInAnimation(showIn);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

        toggle_Flipping = (ToggleButton) findViewById(R.id.toggle_auto);
        toggle_Flipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO Auto-generated method stub
                if (isChecked) {//On 으로 바뀌었으므로 ..자동 Flipping 시작..
                    flipper.setFlipInterval(3000);//플리핑 간격(2000ms)
                    flipper.startFlipping();//자동 Flipping 시작
                } else {//OFF로 변경되었으므로  Flipping 정지
                    flipper.stopFlipping();
                }

                ImageView imageView1 = (ImageView) findViewById(R.id.ad1);
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kosaf.go.kr/ko/notice.do?mode=view&searchStr=&searchType=&page=1&ctgrId1=&ctgrId2=&seqNo=11829"));
                        startActivity(intent);
                    }
                });
                ImageView imageView2 = (ImageView) findViewById(R.id.ad2);
                imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.post.naver.com/viewer/postView.nhn?volumeNo=27403210&memberNo=20182790&vType=VERTICAL"));
                        startActivity(intent);
                    }
                });
                ImageView imageView3 = (ImageView) findViewById(R.id.ad3);
                imageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bokjiro.go.kr/"));
                        startActivity(intent);
                    }
                });
                ImageView imageView4 = (ImageView) findViewById(R.id.ad4);
                imageView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kosaf.go.kr/ko/scholar.do?pg=scholarship05_12_17"));
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_previous:
                flipper.showPrevious();//이전 View로 교체
                break;
            case R.id.btn_next:
                flipper.showNext();//다음 View로 교체
                break;
        }
    }
}
