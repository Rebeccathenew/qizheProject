package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;


public class ScannerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvInfo;
    private TessBaseAPI mBaseAPI;
    private ProgressBar mProbar;
    private String path;
    private RadioGroup mRadioGroup;
    private RadioButton mRbtnIdCard;
    private RadioButton mRbtnBankNumber;
    private RadioButton mRbtnTxt;
    TessBaseAPI  mTess = new TessBaseAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_start).setOnClickListener(this);
        mProbar = (ProgressBar) findViewById(R.id.pb);
        mTvInfo = (TextView) findViewById(R.id.tv_info);
//        mRadioGroup = (RadioGroup) findViewById(R.id.rg);
//        mRbtnIdCard = (RadioButton) findViewById(R.id.rb_idCard);
//        mRbtnBankNumber = (RadioButton) findViewById(R.id.rb_bankNumber);
//        mRbtnTxt = (RadioButton) findViewById(R.id.rb_txt);
//        mRadioGroup.check(0);

        path = Environment.getExternalStorageDirectory().getAbsoluteFile().getAbsolutePath();
    }

    @Override
    public void onClick(View v) {
        mTvInfo.setText("");
        switch (v.getId()) {
            case R.id.btn_start:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        // 没有权限
                        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                            //如果没勾选“不再询问”，向用户发起权限请求
                            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0);
                        }else{
                            Toast.makeText(this,"请前往设置——>存储卡权限——>允许",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // 有权限,接着你要干的活
                        startReadText();
                    }
                }else{
                    startReadText();
                }
                break;
        }
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String s = (String) msg.obj;
                    if (!TextUtils.isEmpty(s)) {
                        mProbar.setVisibility(View.GONE);
                        mTvInfo.setText(s);
                        //释放bitmap
                        mBaseAPI.clear();
                    } else {
                        mProbar.setVisibility(View.GONE);
                        Toast.makeText(ScannerActivity.this, "识别图片内容失败", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case 1:
                    Toast.makeText(ScannerActivity.this, "读取图片失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private Bitmap getBitmap(int id) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeResource(getResources(), id);
        } catch (Exception e) {
            return null;
        }
        return bitmap;
    }

    /**
     * 开始识别文字
     */
    private void startReadText() {

        File f = new File(path+"/tessdata") ;
        if(!f.exists()){
            Toast.makeText(this,"请先下载好语言包置于sd/tessdata目录",Toast.LENGTH_SHORT).show();
            return;
        }

//        final int btnId = mRadioGroup.getCheckedRadioButtonId();
//        final int resId ;
//        if(R.id.rb_idCard==btnId){
//            resId = R.drawable.idcard;
//        }else if(R.id.rb_bankNumber==btnId){
//            resId = R.drawable.bank_number;
//        }else{
//            resId = R.drawable.tet_info;
//        }

        final int resId = R.drawable.text;

        mProbar.setVisibility(View.VISIBLE);
        new Thread() {
            @Override
            public void run() {
                mBaseAPI = new TessBaseAPI();//初始化需要耗时，可以启动时程序时，预初始化
                mBaseAPI.init(path + File.separator, "chi_sim");
                Bitmap bitmap = getBitmap(resId);
                if (bitmap == null) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    mBaseAPI.setImage(bitmap);
                    //根据Init的语言，获得ocr后的字符串
                    String t = mBaseAPI.getUTF8Text();//耗时操作
                    Message obtain = Message.obtain();
                    obtain.what = 0;
                    obtain.obj = t;
                    mHandler.sendMessage(obtain);
                }
            }
        }.start();
    }
}
