package com.example.funnews;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;

public class TextActivity extends Activity implements View.OnClickListener{

    private ImageView img;
    private Button popBtn;
    private PopupWindow popupWindow;
    private Button pop_img;
    private Button pop_file;
    private Button pop_cancle;

    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usr_bio);

        setStatusBar();
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
        initPopWindow();
        initView();
    }

    protected void setStatusBar() {//状态栏沉浸，状态栏颜色，状态栏系统图标的深浅色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            if (false) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorTheme));//设置状态栏背景色
            } else {
                getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            //Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && true) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initView() {
        img = findViewById(R.id.image);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个参数是要将PopupWindow放到的View，第二个参数是位置，第三第四是偏移值
                popupWindow.showAtLocation(img, Gravity.BOTTOM, 0, 0);
            }
        });
    }


    public void initPopWindow() {
        View popView = getLayoutInflater().inflate(R.layout.pop_btn, null);

        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        pop_img = (Button) popView.findViewById(R.id.pop_img);
        pop_file = (Button) popView.findViewById(R.id.pop_file);
        pop_cancle = (Button) popView.findViewById(R.id.pop_cancle);
        pop_img.setOnClickListener(this);
        pop_file.setOnClickListener(this);
        pop_cancle.setOnClickListener(this);
    }

    Intent openCameraIntent;
    private File file=null;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pop_img:
                //关闭popupWindow
                popupWindow.dismiss();
                //拍照
                File dir=null;
                Intent openCameraIntent = new Intent();
                openCameraIntent.setAction( MediaStore.ACTION_IMAGE_CAPTURE);
                openCameraIntent.resolveActivity(getPackageManager());
                if (openCameraIntent.resolveActivity(getPackageManager())!=null) {
                    dir = new File(Environment
                            .getExternalStorageDirectory(), getPackageName());
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    file=new File(dir,"temp.png");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tempUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
                    }else {
                        tempUri = Uri.fromFile(file);
                    }
                    //判断版本
                    openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,tempUri);
                }
                startActivityForResult(openCameraIntent, TAKE_PICTURE);

                break;
            case R.id.pop_file:
                popupWindow.dismiss();
                Intent openAlbumIntent = new Intent(
                        Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, CHOOSE_PICTURE);
                break;
            case R.id.pop_cancle:
                popupWindow.dismiss();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CHOOSE_PICTURE) {//手机
                try {
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == TAKE_PICTURE) {//拍照
                try {
                    if (file.exists()){
                        startPhotoZoom(FileProvider.getUriForFile(this,getPackageName()+".provider",file));// 开始对图片进行裁剪处理
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CROP_SMALL_PICTURE) {// 让刚才选择裁剪得到的图片显示在界面上
                if (data != null) {
//                    setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    Bitmap photo = BitmapFactory.decodeFile(mFile);
                    img.setImageBitmap(photo);
                } else {
                    Log.e("data","data为空");
                }
            }
        }
        else {
            Log.e("cancel","---");
        }

    }


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {

        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
//        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        intent.putExtra("return-data", false);
        File out = new File(getPath());
        if (!out.getParentFile().exists()) {
            out.getParentFile().mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(out));
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }
    private static String mFile;
    public static String getPath() {
        //resize image to thumb
        if (mFile == null) {
            mFile = Environment.getExternalStorageDirectory() + "/" +"wode/"+ "temp.jpg";
        }
        return mFile;
    }

    private void openCamera() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            imageUri = FileProvider.getUriForFile(this, "com.example.funnews", file);//通过FileProvider创建一个content类型的Uri
//        } else {
//            imageUri = Uri.fromFile(file);
//        }
//        Intent intent = new Intent();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
//        }
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
//        startActivityForResult(intent, CODE_CAMERA_REQUEST);
    }
    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            img.setImageBitmap(photo);

        }
    }
    //获取文件的Content uri路径
    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }



}
