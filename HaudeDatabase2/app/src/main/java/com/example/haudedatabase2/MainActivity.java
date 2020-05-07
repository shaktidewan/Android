package com.example.haudedatabase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    DBHelper haudeDbObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        haudeDbObject = new DBHelper(this,null,null,1);
        printDatabase();
    }

}

add(){
    String name = entered_product_name.getText().toString();//user le eneter gareko product name leyeako
    Products prod = new Products(name);
    printDatabase();
}

delete(){
    String name = entered_product_name.getText.toString();
    haudeDbObject.removeProduct(name);
    printDatabase();
}

public void printDatabase(){
    String dbString = haudeDbObject.db_Display();
    db_result.setText(dbString);
    entered_product_name.setText("");
}
