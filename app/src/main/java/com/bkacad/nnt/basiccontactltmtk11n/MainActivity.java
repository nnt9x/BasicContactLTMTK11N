package com.bkacad.nnt.basiccontactltmtk11n;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Khai bao view, bind id
    private EditText edtSearch;
    private Button btnNewContact;
    private TextView tvSize;
    private ListView lvContact;

    // Adapter
    private ArrayAdapter<String> arrayAdapter;
    // Dữ liệu
    private List<String> data;

    // Khai báo dialog
    private NewContactDialog contactDialog;

    private void initView(){
        edtSearch = findViewById(R.id.edt_main_search);
        btnNewContact = findViewById(R.id.btn_main_new_contact);
        tvSize = findViewById(R.id.tv_main_contact_size);
        lvContact = findViewById(R.id.lv_main_contact);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // Thử xem listview đã hoạt động hay chưa ?
        data = new ArrayList<>();
        data.add("Contact 1 - 0918206178");
        data.add("Contact 2 - 0918206179");
        data.add("Contact 3 - 0918206180");

        tvSize.setText(data.size()+" liên hệ");

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        lvContact.setAdapter(arrayAdapter);

        // Tao đối tượng dialog
        contactDialog = new NewContactDialog(this) {
            @Override
            public void newContact(String contact) {
                // Xử lý thêm mới contact
                data.add(contact);
                arrayAdapter.notifyDataSetChanged();
                tvSize.setText(data.size()+" liên hệ");
            }
        };

        btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactDialog.show();
            }
        });

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Delete "+position,Toast.LENGTH_SHORT).show();
                data.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}