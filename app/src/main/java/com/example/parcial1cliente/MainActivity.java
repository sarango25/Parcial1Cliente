package com.example.parcial1cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText number;
    private EditText positionX;
    private EditText positionY;
    private CheckBox red;
    private CheckBox green;
    private CheckBox blue;

    private Button clean;
    private Button create;

    private BufferedWriter bWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.GroupName);
        number = findViewById(R.id.NumberObjects);
        positionX = findViewById(R.id.PositionX);
        positionY = findViewById(R.id.PositionY);
        red = findViewById(R.id.RedType);
        green = findViewById(R.id.GreenType);
        blue = findViewById(R.id.BlueType);

        clean = findViewById(R.id.Clean);
        create = findViewById(R.id.Create);

        startClient();

        create.setOnClickListener((view) ->{
            if (name.getText().toString().isEmpty()||number.getText().toString().isEmpty()||positionX.getText().toString().isEmpty()||positionY.getText().toString().isEmpty() ){
                Toast.makeText(this, "no pueden haber campos vacios", Toast.LENGTH_SHORT).show();
            }else if(red.isChecked()&&green.isChecked()){
                Toast.makeText(this, "solo se puede seleccionar un tipo", Toast.LENGTH_SHORT).show();
            }else if(red.isChecked()&&blue.isChecked()) {
                Toast.makeText(this, "solo se puede seleccionar un tipo", Toast.LENGTH_SHORT).show();
            }else if(blue.isChecked()&&green.isChecked()) {
                Toast.makeText(this, "solo se puede seleccionar un tipo", Toast.LENGTH_SHORT).show();
            }else{
                int type = 0;
                if(red.isChecked()){
                    type = 1;
                }else if(green.isChecked()) {
                    type = 2;
                }else if(blue.isChecked()){
                    type = 3;
                }
                for(int i = 0; i < Integer.parseInt(number.getText().toString()); i++){
                    String groupName = name.getText().toString();
                    int x = Integer.parseInt(positionX.getText().toString());
                    int y = Integer.parseInt(positionY.getText().toString());
                    int velx = (int)(-10+Math.floor(Math.random()*20));
                    int vely = (int)(-10+Math.floor(Math.random()*20));

                    enviarJson(x,y,type,velx,vely,groupName);
                }
            }

        } );

        clean.setOnClickListener((view) -> {
            deletemessage();

        });





    }

    private void deletemessage() {

        new Thread(
                ()-> {

                    try {
                        bWriter.write("del" + "\n");
                        bWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }

    public void startClient(){
        new Thread(
                ()->{
                    try {
                        Socket socket = new Socket("10.0.2.2",5000);

                        OutputStream os = socket.getOutputStream(); //para saber el flujo de datos
                        OutputStreamWriter osw = new OutputStreamWriter(os);
                        bWriter = new BufferedWriter(osw);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
    private void enviarJson(int x, int y,int type, int velX, int velY, String groupName) {

        Gson gson = new Gson();
        Circle c = new Circle (x,y,type,velX,velY,groupName);

        //Serializacion
        String circleStr = gson.toJson(c);
        new Thread(
                ()-> {

                    try {
                        bWriter.write(circleStr + "\n");
                        bWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();

    }
}