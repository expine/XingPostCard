package com.ashez.garfield.xingpostcard.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.ashez.garfield.xingpostcard.R;
import com.ashez.garfield.xingpostcard.Utils.A;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Garfield on 5/10/16.
 */
public class EditCardActivity extends AppCompatActivity {

    @Bind(R.id.editcard_et)
    EditText editcardEt;
    @Bind(R.id.editcard_bt)
    Button editcardBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_editcard);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.editcard_bt)
    public void onClick() {
        if(!editcardEt.getText().toString().isEmpty()){
            Intent intent = new Intent(this, ArtActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("words", editcardEt.getText().toString());
            A.goOtherActivityFinishNoAnim(this,intent);

        }
    }
}
