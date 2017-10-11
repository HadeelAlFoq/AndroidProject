package com.example.hadeel.samples;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by hadeel on 9/16/2017.
 */

public class App_chooses_Fragment extends Fragment {
    Random r;
    boolean restart=false;
    int angle;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }
    public View onCreateView(LayoutInflater inflater, ViewGroup continer, Bundle savedInstanceState ){
        View rootView=inflater.inflate(R.layout.app_choose,continer,false);
        r=new Random();
        Button one,two,three,four,fife,six,seven,eghit;
        final ImageView arrow=(ImageView)rootView.findViewById(R.id.arrow);
        final Button start=(Button) rootView.findViewById(R.id.start);
        one=(Button) rootView.findViewById(R.id.one);
        two=(Button) rootView.findViewById(R.id.two);
        three=(Button) rootView.findViewById(R.id.three);
        four=(Button) rootView.findViewById(R.id.four);
        fife=(Button) rootView.findViewById(R.id.fife);
        six=(Button) rootView.findViewById(R.id.six);
        seven=(Button) rootView.findViewById(R.id.siven);
        eghit=(Button) rootView.findViewById(R.id.eight);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (restart) {
                    angle=angle%360;
                    RotateAnimation rotate = new RotateAnimation(angle, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(1000);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                    arrow.startAnimation(rotate);
                    start.setText("start");
                    restart=false;
                }
                else {
                    angle=r.nextInt(3600)+360;
                    RotateAnimation rotate = new RotateAnimation(0, angle, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                    rotate.setFillAfter(true);
                    rotate.setDuration(3600);
                    rotate.setInterpolator(new AccelerateDecelerateInterpolator());
                    arrow.startAnimation(rotate);

                    restart=true;
                    start.setText("reStart");

            }
        }
        });
        one.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        two.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        three.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        four.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        fife.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        six.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        seven.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        eghit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent i=new Intent(getActivity(),Main2Activity.class);
                getActivity().startActivity(i);
            }
        });
        return rootView;
    }
}
