package com.sunbaby.app.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.libray.basetools.utils.BitmapUtils;
import com.sunbaby.app.bean.UploadFile;
import com.sunbaby.app.common.api.RequestClient;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public class ImageUpload {

    /**
     * 上下文
     **/
    private Context mContext;

    /**
     * 本地待上传的文件集合
     **/
    private ArrayList<String> localImgFileList = null;


    /**
     * 上传成功返回的图片地址集合
     **/
    private ArrayList<String> remoteImgUrlList = null;


    /**
     * 当前正在上传的本地图片索引值
     **/
    private int curUploadImgIndex = 0;

    /**
     * 上传回调
     **/
    private UpLoadImageListener mUpLoadImageListener;


    public ImageUpload(Context mContext, ArrayList<String> localImgFileList, UpLoadImageListener mUpLoadImageListener) {
        this.mContext = mContext;
        this.localImgFileList = localImgFileList;
        this.mUpLoadImageListener = mUpLoadImageListener;
        remoteImgUrlList = new ArrayList<String>();
    }

    /**
     * 开始上传
     */
    public void reLoad() {
        if (localImgFileList != null && localImgFileList.size() > 0) {
            Tip.showLoadDialog(mContext, "正在上传");
            //默认从第一张开始上传
            String path = localImgFileList.get(curUploadImgIndex);
            imageup(path);

        } else if (mUpLoadImageListener != null) {
            mUpLoadImageListener.UpLoadFail();
        }
    }

    public void startLoad() {
        this.reLoad();
    }

    /**
     * 图片上传
     *
     * @param path
     */
    private void imageup(String path) {
        Bitmap temp = BitmapUtils.showimageFull(path, 1024, 1024);
        String base64String = Base64.imgToBase64(temp);
        long date = System.currentTimeMillis();
        RequestClient.getInstance().uploadFile( date + ".png", base64String)
                .subscribe(new Subscriber<UploadFile>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        //图片上传失败
                        Tip.colesLoadDialog();
                        Toast.makeText(mContext, "图片上传失败啦", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(UploadFile uploadFile) {
                        //取得当前上传成功的图片
                        String imgUrl = uploadFile.getFilePath();
                        remoteImgUrlList.add(imgUrl);
                        if ((localImgFileList.size() - 1) > curUploadImgIndex) {
                            //图片尚未全部上传完毕
                            curUploadImgIndex++;
                            imageup(localImgFileList.get(curUploadImgIndex));
                        } else {
                            //图片上传完毕
                            Tip.colesLoadDialog();
                            if (mUpLoadImageListener != null) {
                                mUpLoadImageListener.UpLoadSuccess(remoteImgUrlList);
                            }
                        }
                    }
                });
    }

    public interface UpLoadImageListener {
        void UpLoadSuccess(ArrayList<String> netimageurls);

        void UpLoadFail();
    }

}
