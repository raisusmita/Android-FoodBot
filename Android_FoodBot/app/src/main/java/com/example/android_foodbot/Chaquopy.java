package com.example.android_foodbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;


    public class Chaquopy extends AppCompatActivity{

        @Override
        protected void onCreate( Bundle savedInstanceState ){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chaquopy);

            if(!Python.isStarted()){
                Python.start(new AndroidPlatform(this));
            }

            Python py = Python.getInstance();
            PyObject pym = py.getModule("BotClass");
//            PyObject pyf = pym.callAttr("response");
//            if(!(pyf ==null)){
//                Toast.makeText(this, "not null" +pyf, Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show();
//            }

//

//            TextView name = (TextView)findViewById(R.id.my_text_view);

//            name.setText(pyf.toString());

            ////name = pyf.toString();

            //name.setText("Susmita");

            //

            //val py: Python = Python.getInstance();

            //var pythonText=py.getModule("PythonClass").callAttr("get_python_text", "Susmita")

            //my_text_view.text = pythonText.toString()

        }
}
