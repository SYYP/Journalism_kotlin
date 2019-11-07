package www.app.ypy.com.journalism_kotlin.base.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * Created by ypu
 * on 2019/11/6 0006
 */
public class Imageadapters extends BaseAdapter {
    private Context mContext;
    private Integer[]mImageIds;

    private ImageView[]mImages;

    public Imageadapters(Context paramContext,Integer[]paramArrayOfInteger){
        this.mContext=paramContext;
        this.mImageIds=paramArrayOfInteger;
        this.mImages=new ImageView[this.mImageIds.length];
    }

    private Resources getResources(){return null;}

    public boolean createReflectedImages(){
        Integer[]arrayOfInteger=this.mImageIds;
        int i=arrayOfInteger.length;
        byte b1=0;
        for(byte b2=0;;b2++){
            if(b1>=i)
                return true;
            int j=arrayOfInteger[b1].intValue();
            Bitmap bitmap1=BitmapFactory.decodeResource(this.mContext.getResources(),j);
            int k=bitmap1.getWidth();
            j=bitmap1.getHeight();
            Matrix matrix=new Matrix();
            matrix.preScale(1.0F,-1.0F);
            Bitmap bitmap3;
            Bitmap bitmap2=(bitmap3=Bitmap.createBitmap(bitmap1,0,j/2,k,j/2,matrix,false)).createBitmap(k,j/2+j,Bitmap.Config.ARGB_8888);
            Canvas canvas=new Canvas(bitmap2);
            canvas.drawBitmap(bitmap1,0.0F,0.0F,null);
            (new Paint()).setAntiAlias(false);
            canvas.drawBitmap(bitmap3,0.0F,(j+4),null);
            Paint paint=new Paint();
            paint.setAntiAlias(false);
            paint.setShader(new LinearGradient(0.0F,bitmap1.getHeight(),0.0F,(bitmap2.getHeight()+4),0x70ffffff,0x00ffffff,Shader.TileMode.MIRROR));
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            canvas.drawRect(0.0F,j,k,(bitmap2.getHeight()+4),paint);
            ImageView imageView=new ImageView(this.mContext);
            imageView.setImageBitmap(bitmap2);
            imageView.setLayoutParams(new Gallery.LayoutParams(1080,1680));
            this.mImages[b2]=imageView;
            b1++;
        }
    }

    public int getCount(){return this.mImageIds.length;}

    public Object getItem(int paramInt){return Integer.valueOf(paramInt);}

    public long getItemId(int paramInt){return paramInt;}

    public float getScale(boolean paramBoolean,int paramInt){return Math.max(0.0F,1.0F/(float)Math.pow(2.0D,Math.abs(paramInt)));}

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup){return this.mImages[paramInt];}
}

