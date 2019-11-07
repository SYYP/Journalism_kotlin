package www.app.ypy.com.journalism_kotlin.base.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import android.widget.ImageView;

import activity.ConStellActionActivityes;


/**
 * Created by ypu
 * on 2019/11/6 0006
 */
public class GalleryFlows extends Gallery {
    private Camera mCamera = new Camera();

    private int mCoveflowCenter;

    private int mMaxRotationAngle = 60;

    private int mMaxZoom = -(new Double(ConStellActionActivityes.Companion.getMMax() * 2.2D / 3.0D)).intValue();

    public GalleryFlows(Context context) {
        super(context);
        setStaticTransformationsEnabled(true);
    }

    public GalleryFlows(Context context, AttributeSet attrs) {
        super(context, attrs);
        setStaticTransformationsEnabled(true);

    }

    public GalleryFlows(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setStaticTransformationsEnabled(true);
    }


    private int getCenterOfCoverflow() {
        return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
    }

    private static int getCenterOfView(View paramView) {
        System.out.println("view left :" + paramView.getLeft());
        System.out.println("view width :" + paramView.getWidth());
        return paramView.getLeft() + paramView.getWidth() / 2;
    }

    private void transformImageBitmap(ImageView paramImageView, Transformation paramTransformation, int paramInt) {
        this.mCamera.save();
        Matrix matrix = paramTransformation.getMatrix();
        int i = (paramImageView.getLayoutParams()).height;
        int j = (paramImageView.getLayoutParams()).width;
        int k = Math.abs(paramInt);
        this.mCamera.translate(0.0F, 0.0F, 100.0F);
        if (k < this.mMaxRotationAngle) {
            float f = (float) (this.mMaxZoom + k * 1.5D);
            this.mCamera.translate(0.0F, 0.0F, f);
        }
        this.mCamera.rotateY(paramInt);
        this.mCamera.getMatrix(matrix);
        matrix.preTranslate(-(j / 2), -(i / 2));
        matrix.postTranslate((j / 2), (i / 2));
        this.mCamera.restore();
    }

    protected boolean getChildStaticTransformation(View paramView, Transformation paramTransformation) {
        int i = getCenterOfView(paramView);
        int j = paramView.getWidth();
        paramTransformation.clear();
        paramTransformation.setTransformationType(Transformation.TYPE_MATRIX);
        if (i == this.mCoveflowCenter) {
            transformImageBitmap((ImageView) paramView, paramTransformation, 0);
            return true;
        }
        i = (int) ((this.mCoveflowCenter - i) / j * this.mMaxRotationAngle);
        System.out.println("rotationAngle:" + i);
        j = i;
        if (Math.abs(i) > this.mMaxRotationAngle)
            if (i < 0) {
                j = -this.mMaxRotationAngle;
            } else {
                j = this.mMaxRotationAngle;
            }
        transformImageBitmap((ImageView) paramView, paramTransformation, j);
        return true;
    }

    public int getMaxRotationAngle() {
        return this.mMaxRotationAngle;
    }

    public int getMaxZoom() {
        return this.mMaxZoom;
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        this.mCoveflowCenter = getCenterOfCoverflow();
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public void setMaxRotationAngle(int paramInt) {
        this.mMaxRotationAngle = paramInt;
    }

    public void setMaxZoom(int paramInt) {
        this.mMaxZoom = paramInt;
    }

}
