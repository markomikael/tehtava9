package com.example.harjoitus9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GestureDetector gd;
    LinearLayout ll;


    public class Kuuntelija extends GestureDetector.SimpleOnGestureListener {

        private static final long VELOCITY_THRESHOLD = 1;


        @Override
        public boolean onDoubleTap(MotionEvent e) {

            LinearLayout ll = findViewById(R.id.tausta);

            ColorDrawable vc = (ColorDrawable) ll.getBackground();
            int color = vc.getColor();

            if (color == Color.WHITE)
                ll.setBackgroundColor(Color.BLACK);
            else
                ll.setBackgroundColor(Color.WHITE);




            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            {



                if (Math.abs(velocityX) < VELOCITY_THRESHOLD
                        && Math.abs(velocityY) < VELOCITY_THRESHOLD) {
                    return false;//if the fling is not fast enough then it's just like drag

                }



                else if (Math.abs(velocityX) > Math.abs(velocityY)) {

                    double vari = Math.abs((double)velocityX);

                    LinearLayout ll = findViewById(R.id.tausta);

                    ll.setBackgroundColor(Color.rgb((int) (vari/16000*255+50), 0, 0));
                    Log.d("x",String.valueOf(vari/16000*255+50));
                    return true;
                } else {

                    LinearLayout ll = findViewById(R.id.tausta);
                    double vari2 = Math.abs((double)velocityY);
                    ll.setBackgroundColor(Color.rgb(0,0, (int) (vari2/16000*255+50)));
                    Log.d("y",String.valueOf(vari2/16000*255+50));
                    return true;

                }


                }}}




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gd = new GestureDetector(MainActivity.this, new Kuuntelija());

        LinearLayout ll  = findViewById(R.id.tausta);

        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gd.onTouchEvent(event);
            }
        });








    }

}
