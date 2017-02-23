package com.example.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by LENOVO on 2017/2/10.
 */

public class CirclelmageViewW extends ImageView {
    private static final ScaleType SCALE_TYPE=ScaleType.CENTER_CROP;
    private static final Bitmap.Config BITMAP_CONFIG= Bitmap.Config.ARGB_8888;
    private static final int COLORDAWABLE_DIMENSION=2;

    private final RectF mDrawableRect=new RectF();
    private final Matrix mShaderMatrix=new Matrix();
    private final Paint mBitmapPaint=new Paint();

    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;//位图渲染
    private int mBitmapWidth;//位图宽度
    private int mBitmapHeight;//位图高度

    private float mDrawableRadius;//图片半径

    private ColorFilter mColorFilter;//滤色器

    private boolean mReady;//初始化时都为false
    private boolean mSetupPending;

    public CirclelmageViewW(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public void init(){
        super.setScaleType(SCALE_TYPE);
        mReady=true;

        if (mSetupPending){
            setup();
            mSetupPending=false;
        }
    }

    @Override
    public ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (scaleType!=SCALE_TYPE){
            throw new IllegalArgumentException(String.format("ScaleType%s not supported",scaleType));
        }
    }

    @Override
    public void setAdjustViewBounds(boolean adjustViewBounds) {
        if (adjustViewBounds){
            throw  new IllegalArgumentException("adjustViewBounds not supported");
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable()==null){
            return;
        }
        canvas.drawCircle(getWidth()/2,getHeight()/2,mDrawableRadius,mBitmapPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setup();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap=bm;
        setup();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap=getBitmapFromDrawable(drawable);
        setup();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap=getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap=getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (cf==mColorFilter){
            return;
        }

        mColorFilter=cf;
        mBitmapPaint.setColorFilter(mColorFilter);
        invalidate();
    }

    //Drawable转Bitmap
    private Bitmap getBitmapFromDrawable(Drawable drawable){
        if (drawable==null){
            return null;
        }

        if (drawable instanceof BitmapDrawable){
            return (((BitmapDrawable) drawable).getBitmap());
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable){
                bitmap=Bitmap.createBitmap(COLORDAWABLE_DIMENSION
                        ,COLORDAWABLE_DIMENSION,BITMAP_CONFIG);
            }else {
                bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth()
                        ,drawable.getIntrinsicHeight(),BITMAP_CONFIG);
            }

            Canvas canvas=new Canvas(bitmap);
            drawable.setBounds(0,0,canvas.getWidth(),canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }catch (OutOfMemoryError e){
            Log.d(TAG, "getBitmapFromDrawable:函数的设置出错误 ");
            return null;
        }
    }

    private void setup(){
        if (!mReady){
            mSetupPending=true;
            Log.d(TAG, "setup: 在第一步的返回，未执行构造函数");
            return;
        }

        if (mBitmap==null){
            Log.d(TAG, "setup: mBitmap空指针异常");
            return;
        }

        mBitmapShader= new BitmapShader(mBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBitmapHeight=mBitmap.getHeight();//取原图片的宽高
        mBitmapWidth=mBitmap.getWidth();

        mDrawableRect.set(0,0,getWidth(),getHeight());
        mDrawableRadius=Math.min(mDrawableRect.height()/2,mDrawableRect.width()/2);
        updateShaderMatrix();
        invalidate();
    }

    private void updateShaderMatrix(){
        float scale;//缩小或者放大比例
        float dx=0;
        float dy=0;

        mShaderMatrix.set(null);

        if (mBitmapWidth/mDrawableRect.width()>mBitmapHeight/mDrawableRect.height()){
            //由于位图与原图的宽的比大于位图与原图高的比，所以沿高的方向进行缩小，并在x轴方向平移
            scale=mDrawableRect.height()/(float)mBitmapHeight;
            dx=(mDrawableRect.width()-mBitmapWidth*scale)*0.5f;
        }else {
            scale=mDrawableRect.width()/(float)mBitmapWidth;
            dy=(mDrawableRect.height()-mBitmapHeight*scale)*0.5f;
        }

        mShaderMatrix.setScale(scale,scale);//shader的变换矩阵，用于缩小或放大
        mShaderMatrix.postTranslate((int)(dx+0.5f)+mDrawableRect.left
                ,(int)(dy+0.5f)+mDrawableRect.top);//平移

        mBitmapShader.setLocalMatrix(mShaderMatrix);//设置变换矩阵
    }
}
