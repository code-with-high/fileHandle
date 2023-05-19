/*Write an Android Application to file operation using Stream
classes and follow the instructions.
 There are EditText, Textview and 3 Buttons.
 Edittext used to enter the text and clear after the single
operations.
 Textview is used to display the text from the file.
 “Store” button will store / create a file with name
“Filename.txt”
 “Append” button will append data into the same file.
 “Display” button will display all data from file to TextView.*/

package com.example.filehandle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    // scope of the private variables inside the package l;only
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        

        Button storeButton = findViewById(R.id.storeBtn);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // above is for get the input from user into string in edittext and
                // after that we will store it through store button into the created file
                // this is take the text dynamically inside the edit text
                // this is take the text dynamically inside the edit text
                //

                String text = editText.getText().toString();
                //// Change the color of the EditText to red
                editText.setTextColor(Color.RED);
                // Set the converted input value back to the EditText
                editText.setText(text);

                try {
                    FileOutputStream fos = openFileOutput("fos.txt", MODE_PRIVATE);
                    // it will changes the string into byte of text for the system understanding
                    // and store it into the file after converting into bytes because  file reads
                    // each sting into bytes
                    fos.write(text.getBytes());
                    fos.close();
                    Toast.makeText(MainActivity.this,"File write and saved successful",Toast.LENGTH_SHORT).show();
                    // this is used for to clear the edit text
                    editText.setText("");
                }catch (IOException e){
                    // when we write the e.printStackTrace(); all the input output exception has gone why
                    // why error gone when we print the e.printStackTrace()
                    e.printStackTrace();
                }



                // ---- Now we add the event through append button ---- //

                Button appendButton = findViewById(R.id.appendBtn);

                appendButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String text = editText.getText().toString();
                        try {
                            FileOutputStream fos = openFileOutput("fos.txt", MODE_APPEND);
                            fos.write(text.getBytes());
                            fos.close();
                            Toast.makeText(MainActivity.this, "Text Appended successfully", Toast.LENGTH_SHORT).show();
                            // for clear the edittext

                            editText.setText("");
                        }catch(IOException e) {
                            e.printStackTrace();
                        }


                    }
                });


                // for display the text

                Button displayButton = findViewById(R.id.displayBtn);
                displayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        try {
                            // in reading the file no need of mode
                            FileInputStream fis = openFileInput("fos.txt");
                            InputStreamReader isr = new InputStreamReader(fis);

                            // buffer is like the meta data
                            // buffer is basically the temporary image of the
                            // file that you can edit later
                            BufferedReader br = new BufferedReader(isr);
                            StringBuilder sb = new StringBuilder();

                            String line;
                            // readline method read the whole instead of reading one one word
                            while ((line = br.readLine()) != null) {
                                sb.append(line).append("\n");
                            }
                            textView.setText(sb.toString());
                            fis.close();
                            isr.close();
                            br.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                });


            }
        });



    }
}