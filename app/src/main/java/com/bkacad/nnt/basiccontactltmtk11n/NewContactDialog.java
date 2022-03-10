package com.bkacad.nnt.basiccontactltmtk11n;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public abstract class NewContactDialog extends Dialog {

    private Button btnAdd, btnCancel;
    private EditText edtName, edtPhone;

    public abstract void newContact(String contact);

    private void initView(){
        btnAdd = findViewById(R.id.btn_dialog_add);
        btnCancel = findViewById(R.id.btn_dialog_cancel);
        edtName = findViewById(R.id.edt_dialog_contact);
        edtPhone = findViewById(R.id.edt_dialog_phone);
    }

    public NewContactDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_contact);
        initView();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ Name, Phone
                String name = edtName.getText().toString();
                if(name.isEmpty()){
                    edtName.setError("Hãy nhập họ tên");
                    return;
                }
                String phone = edtPhone.getText().toString();

                if(phone.isEmpty()){
                    edtName.setError("Hãy nhập số điện thoại");
                    return;
                }
                String contact = name +" - " + phone;
                newContact(contact);
                // Reset lại dữ liệu ở edt
                edtPhone.setText("");
                edtName.setText("");
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
}
