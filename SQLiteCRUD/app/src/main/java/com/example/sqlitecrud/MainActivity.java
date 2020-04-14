package com.example.sqlitecrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBManager myDB;
    EditText userName,userEmail,userCourse,userId;
    Button sign,viewAll,updateBtnView,DeleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DBManager(this);

        userName = (EditText) findViewById(R.id.t1);
        userEmail = (EditText) findViewById(R.id.t2);
        userCourse = (EditText) findViewById(R.id.t3);
        userId = (EditText) findViewById(R.id.editTextID);

        sign = (Button) findViewById(R.id.submit);
        viewAll = (Button) findViewById(R.id.viewAll);
        updateBtnView = (Button) findViewById(R.id.update);
        DeleteBtn = (Button) findViewById(R.id.buttonDelete);

        addData();
        viewALl();
        UpdateData();
        DeleteData();
    }

    public void DeleteData(){
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleteRows=myDB.deleteData(userId.getText().toString());
                if(deleteRows > 0)
                    Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void UpdateData(){
        updateBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateData(userId.getText().toString(),userName.getText().toString(),userEmail.getText().toString(),userCourse.getText().toString());
                if(isUpdate == true){
                    Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Unsuccessful",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void addData(){
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(userName.getText().toString(),
                        userEmail.getText().toString(),
                        userCourse.getText().toString());
                if (isInserted == true)
                    Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Unsuccessful",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewALl(){
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.getAllData();
                if (res.getCount() == 0){
                    //show message
                    showMessage("Error","No Data");
                    return;
                }else{
                    //store data in buffer
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("id :"+res.getString(0)+"\n");
                        buffer.append("Name :"+res.getString(1)+"\n");
                        buffer.append("Email :"+res.getString(2)+"\n");
                        buffer.append("Course :"+res.getString(3)+"\n\n");
                    }
                    //show all data
                    showMessage("Data",buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
