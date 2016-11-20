package com.example.wengzipeng.mycontacts;

/**
 * Created by wengzipeng on 2016/11/20.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hp on 2016/11/15.
 */
public class FindDialog extends Dialog{
    private Context l_context;
    FindDialog(Context context) {
        super(context);
        l_context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);
        setTitle("查询联系人");
        final Button find = (Button) findViewById(R.id.find);
        Button cancel = (Button) findViewById(R.id.cancel);
        find.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText value = (EditText) findViewById(R.id.value);
                ContactsTable ct = new ContactsTable(l_context);
                //模糊查询联系人
                User[] users = ct.findUserByKey(value.getText().toString());
                for(int i=0;i<users.length;i++){
                    System.out.println("姓名："+users[i].getNAME()+",电话"+users[i].getMOBLIE());
                }
                //主界面数据更新
                ((MainActivity)l_context).setUsers(users);
                ((MainActivity)l_context).getListViewAdapter().notifyDataSetChanged();
                ((MainActivity)l_context).setSelectItem(0);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
}
