package www.app.ypy.com.journalism_kotlin.base.view;

import android.content.Context;
import android.graphics.Camera;

import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by ypu
 * on 2019/11/5 0005
 * 循环控件
 */
public class GalleryFlow extends Gallery {
    private Camera mCamera = new Camera();
    private int mMaxRotationAngle = 60;
    private int mMaxZoom = -120;
    private int mCoveflowCenter;

    public GalleryFlow(Context context) {
        super(context);
        this.setStaticTransformationsEnabled(false);
    }

    public GalleryFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setStaticTransformationsEnabled(false);
    }

    public GalleryFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setStaticTransformationsEnabled(false);
    }

    public int getMaxRotationAnle() {
        return mMaxRotationAngle;
    }

    public int setMaxRotationAnle(int maxRotationAngle) {
        return mMaxRotationAngle = maxRotationAngle;
    }

    public int getMaxZoom() {
        return mMaxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        mMaxZoom = maxZoom;
    }

    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2
                + getPaddingLeft();
    }

    private static int getCenterOfView(View view) {
        return view.getLeft() + view.getWidth() / 2;
    }

    protected boolean getChildStaticTransformation(View child, Transformation t) {

        final int childCenter = getCenterOfView(child);
        final int childWidth = child.getWidth();
        int rotationAngle = 0;

        t.clear();
        t.setTransformationType(Transformation.TYPE_MATRIX);
        Log.v("tag", "getChildStaticTransformation>>>>>>>>>>>>>>>>>>>childCenter" + childCenter + ">>>>>" + Math.abs((mCoveflowCenter - childCenter) / (childWidth)));
        if (childCenter == mCoveflowCenter) {
            transformImageBitmap((ImageView) child, t, 0);
        } else {
            if ((mCoveflowCenter - childCenter) > 0) {
                rotationAngle = (int) mMaxRotationAngle;
            } else {
                rotationAngle = (int) -mMaxRotationAngle;
            }
            if (Math.abs((mCoveflowCenter - childCenter) / (childWidth / 2)) == 0) {
                rotationAngle = (int) (((float) (mCoveflowCenter - childCenter) /
                        childWidth) * mMaxRotationAngle);
            }

            transformImageBitmap((ImageView) child, t, rotationAngle);
        }

        return true;
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void transformImageBitmap(ImageView child, Transformation t,
                                      int rotationAngle) {
        mCamera.save();
        final Matrix imageMatrix = t.getMatrix();
        final int imageHeight = child.getLayoutParams().height;
        final int imageWidth = child.getLayoutParams().width;
        final int rotation = Math.abs(rotationAngle);

        // 在Z轴上正向移动camera的视角，实际效果为放大图片。
        // 如果在Y轴上移动，则图片上下移动；X轴上对应图片左右移动。
        mCamera.translate(0.0f, 0.0f, 100.0f);

        //// As the angle of the view gets less, zoom in
        //        if (rotation < mMaxRotationAngle) {
        //            float zoomAmount = (float) (mMaxZoom + (rotation * 1.5));
        //            mCamera.translate(0.0f, 0.0f, zoomAmount);
        //        }

        // 在Y轴上旋转，对应图片竖向向里翻转。
        // 如果在X轴上旋转，则对应图片横向向里翻转。
        mCamera.rotateY(rotationAngle);
        mCamera.getMatrix(imageMatrix);
        imageMatrix.preTranslate(-(imageWidth / 2), -(imageHeight / 2));
        imageMatrix.postTranslate((imageWidth / 2), (imageHeight / 2));
        mCamera.restore();
    }
}
