package com.eugene.thingscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText newItemText;
    Button newItemButton;
    ListView itemsList;
    ArrayList<Items> itemsArrayList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        itemsArrayList = new ArrayList<>();

        itemsSetting();
        addListeners();
    }

    private void addListeners(){
        newItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = String.valueOf(newItemText.getText());
                if(inputText.isEmpty()){
                    //nothing
                }else{
                    itemsArrayList.add(new Items(inputText, 0));
                    String[] tmpArray = new String[]{};
                    for(Items entry : itemsArrayList){
                        tmpArray = addElements(tmpArray, entry.name);
                    }
                    adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,tmpArray);
                    itemsList.setAdapter(adapter);
                    newItemText.setText("");
                }

            }
        });
    }

    private String[] addElements(String[] array, String element){
        String[] tmp = new String[array.length + 1];
        System.arraycopy(array, 0,tmp, 0, array.length);
        tmp[array.length] = element;
        return tmp;
    }

    private void itemsSetting(){
        itemsList = findViewById(R.id.itemListId);

        newItemButton = findViewById(R.id.addNewItemButtonId);
        newItemButton.setText("Add item");

        newItemText = findViewById(R.id.addNewItemTextId);
        newItemText.setText("");
    }
}