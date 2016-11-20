package com.example.wengzipeng.mycontacts;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText moblieEdiText;
    private EditText qqEdiText;
    private EditText danweiEdiText;
    private EditText addressEdiText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
        setTitle("添加联系人");

        nameEditText = (EditText) findViewById(R.id.name);
        moblieEdiText = (EditText) findViewById(R.id.mobile);
        qqEdiText = (EditText) findViewById(R.id.qq);
        danweiEdiText = (EditText) findViewById(R.id.danwei);
        addressEdiText = (EditText) findViewById(R.id.address);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"保存");
        menu.add(Menu.NONE,2,Menu.NONE,"返回");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                if(!nameEditText.getText().toString().equals("")) {
                    User user = new User();
                    user.setName(nameEditText.getText().toString());
                    user.setMobile(moblieEdiText.getText().toString());
                    user.setDanwei(danweiEdiText.getText().toString());
                    user.setQq(qqEdiText.getText().toString());
                    user.setAddress(addressEdiText.getText().toString());

                    ContactsTable ct = new ContactsTable(AddContactsActivity.this);
                    if(ct.addData(user)){
                        Toast.makeText(AddContactsActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(AddContactsActivity.this,"添加失败",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(AddContactsActivity.this,"请先输入数据",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

