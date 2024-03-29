package com.example.trabalhosd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class TrabalhoSD_Tela2 extends AppCompatActivity {


    private Button Button_add, button_calcular;
    private EditText editTextNumeros;
    private TextView editviewadd, Result_media, Result_mediana;
    public String mandarproservidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabalho_sd__tela2);

        editTextNumeros = (EditText) findViewById(R.id.editText2);
        Button_add = (Button) findViewById(R.id.button_add);
        editviewadd = (TextView) findViewById(R.id.textView);
        button_calcular = (Button) findViewById(R.id.button_calcular);

        Result_mediana = (TextView) findViewById(R.id.Result_mediana);
        Result_media = (TextView) findViewById(R.id.Result_media);


        Button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editviewadd.setText(editviewadd.getText()+" "+editTextNumeros.getText());

                if (mandarproservidor!= null) {
                    mandarproservidor = editTextNumeros.getText()+";"+mandarproservidor;
                }else{
                    mandarproservidor = editTextNumeros.getText()+"";
                }
                editTextNumeros.setText("");
            }
        });

        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String response = Connection.run(mandarproservidor, getApplicationContext());

                String[] r = response.split(";");

                Result_media.setText(String.format("%.2f", Double.parseDouble(r[0])));
                Result_mediana.setText(String.format("%.2f", Double.parseDouble(r[1])));
                //Toast.makeText(getApplicationContext(), mandarproservidor, Toast.LENGTH_LONG).show();
            }
        });
    }
}
