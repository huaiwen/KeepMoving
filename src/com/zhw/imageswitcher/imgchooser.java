package com.zhw.imageswitcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.imudges.keepmoving.R;

/**
 * Created with IntelliJ IDEA.
 * User: Wymon Zhang
 * Date: 13-7-20
 * Time: 下午8:07
 * To change this template use File | Settings | File Templates.
 */
public class imgchooser extends Activity {
    ImageButton ib = null;
    AlertDialog alertDialog = null;
    ImageSwitcher imageSwitcher;
    Gallery gallery;
    myViewFactory mf = new myViewFactory(this);
    private int[] image = {R.drawable.logo_douban, R.drawable.logo_email, R.drawable.logo_evernote, R.drawable.logo_facebook, R.drawable.logo_foursquare, R.drawable.logo_googleplus, R.drawable.logo_kaixin, R.drawable.logo_linkedin, R.drawable.logo_neteasemicroblog, R.drawable.logo_qzone, R.drawable.logo_renren};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registeredmessage);
        ib = (ImageButton) findViewById(R.id.btn_img_choose);
        ib.setOnClickListener(img_listener);
    }

    public View.OnClickListener img_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            initImageChoose();
            alertDialog.show();
        }
    };

    public void initImageChoose() {
        AlertDialog.Builder abt = new AlertDialog.Builder(this);
        abt.setTitle("请选择一个图片作为头像");


        LayoutInflater layoutInflater = LayoutInflater.from(this);


        View view = layoutInflater.inflate(R.layout.imgchoose, null);


        gallery = (Gallery) view.findViewById(R.id.img_gallery);
        gallery.setAdapter(new ImageAdapder(this));
        gallery.setSelection(image.length / 2);


        imageSwitcher = (ImageSwitcher) view.findViewById(R.id.img_switch);
        imageSwitcher.setFactory(mf);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if ((i > 1 || i == 1) && (i < image.length))
                    imageSwitcher.setImageResource(image[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //To change body of implemented methods use File | Settings | File Templates.
                gallery.setSelection(image.length / 2);

            }
        });
        abt.setView(view);
        alertDialog = abt.create();
    }

    class myViewFactory implements ViewSwitcher.ViewFactory {
        private Context content;

        myViewFactory(Context content) {
            this.content = content;
        }

        @Override
        public View makeView() {
            ImageView iv = new ImageView(content);
            iv.setLayoutParams(new ImageSwitcher.LayoutParams(20, 20));
            return iv;
        }

    }

    class ImageAdapder extends BaseAdapter {
        private Context context;

        ImageAdapder(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return image.length;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(image[i]);
            iv.setAdjustViewBounds(true);
            iv.setLayoutParams(new Gallery.LayoutParams(80, 80));
            iv.setPadding(15, 10, 15, 10);
            return iv;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}