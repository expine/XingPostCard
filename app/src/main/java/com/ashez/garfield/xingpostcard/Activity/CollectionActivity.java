package com.ashez.garfield.xingpostcard.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.ashez.garfield.xingpostcard.R;
import com.ashez.xingpostcard.adapter.Collection_SimpleAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Garfield on 4/26/16.
 */
public class CollectionActivity extends AppCompatActivity {
    public ArrayList<Integer> Pick_mData,Collection_data_real,Collection_data_cartoon;
    private RecyclerView recyclerView_cartoon,recyclerView_real;
    public Collection_SimpleAdapter mAdapter_real,mAdapter_cartoon;
    Context con;
    Button bendi;
    int Pick_position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        initData();
        initView();
        // 第一个参数是key， 第二个参数是若给定key对应的值不存在则默认的返回值；
         Pick_position = getIntent().getIntExtra("styleCode",-1);
        System.out.println("styleCode:"+Pick_position);
//        img.setImageResource(mData.get(id));

        view_layout(); //布局设置
        select_Click();//点击选择本地图片
//        get_picture();
        real_Click();
        cartoon_Click();


//"pictureFromApp" 卡通过真实


    }

    private void cartoon_Click() {
        mAdapter_cartoon.setOnItemClickListener(new Collection_SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CollectionActivity.this, ArtActivity.class);
                //  第一个参数是key， 第二个参数是要传递的值
                intent.putExtra("pictureFromApp",
                        Collection_data_cartoon.get(position));
                intent.putExtra("styleCode",Pick_position);
                CollectionActivity.this.startActivity(intent);
            }
        });

    }

    private void real_Click() {
        mAdapter_real.setOnItemClickListener(new Collection_SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(CollectionActivity.this, ArtActivity.class);
                //  第一个参数是key， 第二个参数是要传递的值
                intent.putExtra("pictureFromApp",
                        Collection_data_real.get(position));
                intent.putExtra("styleCode",Pick_position);
                CollectionActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();//"pictureFromLocal"
            Intent intent=new Intent(CollectionActivity.this,ArtActivity.class);
            intent.putExtra("pictureFromLocal",
                    uri);
            intent.putExtra("styleCode",Pick_position);
            CollectionActivity.this.startActivity(intent);




//            ContentResolver cr = this.getContentResolver();
            //   Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
            System.out.println("vvvvvvvvvvvv"+uri);
//                img = (ImageView) findViewById(R.id.img);
                /* 将Bitmap设定到ImageView */
//                img.setImageBitmap(bitmap);  //获取图片
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void select_Click() {
        bendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
        });
    }

    private void view_layout() {
        final LinearLayoutManager linearLayoutMannger=new LinearLayoutManager(con,LinearLayoutManager.HORIZONTAL,false);
        mAdapter_real=new Collection_SimpleAdapter(this,Collection_data_real);
        recyclerView_real.setAdapter(mAdapter_real);
        recyclerView_real.setLayoutManager(linearLayoutMannger);


        final LinearLayoutManager linearLayoutMannger2=new LinearLayoutManager(con,LinearLayoutManager.HORIZONTAL,false);
        mAdapter_cartoon=new Collection_SimpleAdapter(this,Collection_data_cartoon);
        recyclerView_cartoon.setAdapter(mAdapter_cartoon);
        recyclerView_cartoon.setLayoutManager(linearLayoutMannger2);

    }

    private void initData() {
        Pick_mData = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));
        Collection_data_real = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));
        Collection_data_cartoon = new ArrayList<Integer>(Arrays.asList(R.mipmap.ic_launcher,
                R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher));
    }

    private void initView() {
        recyclerView_cartoon= (RecyclerView) findViewById(R.id.recyclerView_cartoon);
        recyclerView_real= (RecyclerView) findViewById(R.id.recyclerView_real);
        bendi= (Button) findViewById(R.id.bendi);
    }
}
