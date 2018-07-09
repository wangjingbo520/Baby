package com.sunbaby.app.common.base;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;

import com.libray.basetools.utils.BitmapUtils;
import com.sunbaby.app.R;
import com.sunbaby.app.common.file.ImageFolder;
import com.sunbaby.app.common.widget.SavePicPopWindow;

import java.io.File;

/**
 * @author 王静波
 * @date 2018/7/9
 * describe
 */
public class BaseCameraActivity extends BaseActivity {
    protected final String TAG = this.getClass().getSimpleName();

    /** 自定义相机 **/
    public static final int CAMER_CUSTOM_CODE = 004;

    /** 相机 **/
    public static final int CAMER_CODE = 005;

    /** 相册 **/
    public static final int ALBUM_CODE = 006;

    /** 完成(得到照片) **/
    public static final int FINISH_CODE = 007;


    /** 当前照片的文件 **/
    private File imageFile;

    /** 是否裁剪，相机相册之后 **/
    private boolean crop = true;

    /** 是否打开相机取图片 **/
    private boolean isOpenCamera = false;

    /**压缩宽度**/
    private int compressWidth = 0;

    /**压缩高度**/
    private int compressHeight = 0;

    /**裁剪宽度**/
    private int cropWith = 100;

    /**裁剪高度**/
    private int cropHeight = 100;

    private boolean cancelFlag = true;

    /**
     * 是否需要上传图片
     **/
    public boolean needUpload;

    /**
     * 当前弹出的popwindow
     **/
    private SavePicPopWindow mPopupWindow = null;

    /**
     * 显示相机，相册PopWindow
     *
     * @param main
     * @param crop
     * @param needUpload
     */
    public void showCameraPopwindow(View main, final boolean crop, boolean needUpload) {

        this.crop = crop;
        this.needUpload = needUpload;
        if (mPopupWindow == null) {
            mPopupWindow = new SavePicPopWindow(mContext, new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    int i = v.getId();
                    if (i == R.id.btn_take_photo) {
                        // 拍照
                        callCamerImage(crop);

                    } else if (i == R.id.btn_pick_photo) {
                        // 打开相机
                        callLocalImage(crop);
                    }
                }
            });
        }
        if (!mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(main, Gravity.BOTTOM, 0, 0);
        }
    }


    /**
     * 打开相机
     */
    public void callCamerImage(boolean crop) {
        callCamerImageSystem(crop);
    }

    /**
     * 打开本地图库
     */
    public void callLocalImage(boolean crop) {
        this.crop = crop;
        com.libray.basetools.utils.LogUtil.i(TAG, "打开图库");
        imageFile = com.libray.basetools.basecamera.ImageFolder.getTempImageName();

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");


        if (isKitKat()) {
            startActivityForResult(intent, ALBUM_CODE);
        } else {
            startActivityForResult(intent, ALBUM_CODE);
        }

        // 标记是打开图库
        isOpenCamera = false;
    }

    /**
     * 打开相机
     */
    private void callCamerImageSystem(boolean crop) {
        this.crop = crop;
        isOpenCamera = true;
        imageFile = com.libray.basetools.basecamera.ImageFolder.getTempImageName();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        android.net.Uri uri = android.net.Uri.fromFile(imageFile);
        captureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
        captureIntent.putExtra("return-data", true);
        // 需要调裁剪
        startActivityForResult(captureIntent, CAMER_CODE);

    }

    /**
     * 判断是否是4.4以上版本
     *
     * @return
     */
    private boolean isKitKat() {
        return android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT;
    }

    /**
     * 设置裁剪参数
     *
     * @param width
     * @param height
     */
    public void setCropParam(int width, int height) {
        this.cropWith = width;
        this.cropHeight = height;
    }

    /**
     * 设置图片压缩尺寸
     *
     * @param compressWidth
     * @param compressHeight
     */
    public void setCompressParam(int compressWidth, int compressHeight) {
        this.compressWidth = compressWidth;
        this.compressHeight = compressHeight;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cancelFlag = true;
        if (resultCode != RESULT_OK) {
            // 拍照，相册不成功
            return;
        }

        switch (requestCode) {
            case CAMER_CODE:
                // 相机拍照返回
                if (crop) {
                    // 需要调裁剪
                    goToCrop();

                } else {

                    // 不用裁剪,将照片直接返回
                    String temp = getPhotoMy(data, isOpenCamera);
                    handleImageFile(temp);
                }

                break;
            case CAMER_CUSTOM_CODE:

                //自定义相机返回
                if (null != data) {
                    String photo = data.getStringExtra("photo");
                    handleImageFile(photo);
                }
            case ALBUM_CODE:
                // 相册选照片返回

                //String file = getPhotoMy(data, isOpenCamera);
                String file = getPathNew(this, data.getData());
                imageFile = new File(file);
                if (crop) {
                    goToCrop();
                } else {
                    handleImageFile(file);
                }

                break;
            case FINISH_CODE:

                // 完成
                if (null != imageFile) {
                    handleImageFile(imageFile.getAbsolutePath());
                }
                break;
            default:
                break;
        }
    }

    /**
     * 跳转到裁剪界面
     */
    private void goToCrop() {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(android.net.Uri.fromFile(imageFile), "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", cropWith);
        intent.putExtra("aspectY", cropHeight);
        intent.putExtra("outputX", cropWith);
        intent.putExtra("outputY", cropHeight);
        intent.putExtra("noFaceDetection", false);
        intent.putExtra("scale", true);
        imageFile = ImageFolder.getTempImageName();
        intent.putExtra("output", android.net.Uri.fromFile(imageFile));
        // 返回格式
        intent.putExtra("outputFormat", "JPEG");
        startActivityForResult(intent, FINISH_CODE);
    }

    /**
     * 取照片
     *
     * @param isOpenCamera
     * @return
     */
    private String getPhotoMy(Intent data, boolean isOpenCamera) {
        String photoPath = null;
        if (isOpenCamera) {

            // 拍照是一定有路径的
            if (null != imageFile && imageFile.exists()) {
                photoPath = imageFile.getAbsolutePath();
            } else {

                //从数据库里面读取
                photoPath = getPathByData(data);
            }

        } else {

            if (null != imageFile && imageFile.exists()) {

                // 相册-裁剪也是一定有路径的
                photoPath = imageFile.getAbsolutePath();

            } else {

                // 单独选择相册图片是没有图片的,要去图库查询
                photoPath = getPathByData(data);
            }
        }

        //替换坑爹的/storage/emulated/0，这个路径代码里面是获取不到的
        if (photoPath.startsWith("/storage/emulated/0")) {
            photoPath = photoPath.replace("/storage/emulated/0", "/sdcard");
        }

        return photoPath;
    }

    /**
     * 处理图片
     *
     * @param imageFile
     */
    public void handleImageFile(String imageFile) {

        //裁剪的图片不需要进行压缩
        if (crop) {
            onPhotoPickComplete(imageFile);
            return;
        }


        //这里要对图片进行压缩
        String tempFile = ImageFolder.getTempImageName().getAbsolutePath();
        if (compressWidth > 0 && compressHeight > 0) {

            //压缩图片
            Bitmap bitmap = BitmapUtils.showimageFull(imageFile, compressWidth, compressHeight);
            BitmapUtils.saveBitmap(bitmap, tempFile);

            //Bitmap释放
            bitmap.recycle();

            //将生成的图片返回
            onPhotoPickComplete(tempFile);

        } else {

            //将生成的图片返回
            onPhotoPickComplete(imageFile);
        }
    }


    // //-----------------------------

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPathNew(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse
                        ("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private String getPathByData(Intent data) {
        // 单独选择相册图片是没有图片的,要去图库查询
        if (data != null && data.getData() != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = this.getContentResolver().query(selectedImage, filePathColumns, null,
                    null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            return picturePath;
        } else {

            if (null != data && data.hasExtra("image_path")) {
                return data.getStringExtra("image_path");
            }

        }
        return "";
    }

    /**
     * 图片上传成功，将图片url，和本地路径返回
     *
     * @param imageFile
     */
    public void onPhotoPickComplete(String imageFile) {

    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[]
            selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection,
                    selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }

        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }



}
