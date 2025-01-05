package japiim.dic.morekuyubim.por;


import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.noties.markwon.Markwon;

import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.ext.tables.TableTheme;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterIntroduction;



public class ViewAdapterIntroduction extends AppCompatActivity {


    Context context;
    RecyclerView.LayoutManager layoutManager;

//    MarkdownView mdIntro;
    TextView tvIntro1;
    TextView tvIntro2;

    ImageView ivIntro1;

    ImageView ivIntro2;
    RecyclerView rvIntro;
    RecyclerViewAdapterIntroduction recyclerViewAdapterIntroduction;

    int fileId1;

    int fileId2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout_introduction);

        context = getApplication().getBaseContext();
        tvIntro1 = findViewById(R.id.tvIntro1);
        String mdFilePath1 = "intro1.md";
        loadMarkdownIntoTextView(mdFilePath1, tvIntro1);

        String fileStringNoExtension1 = "territory1";
        fileId1 = context.getResources().getIdentifier(fileStringNoExtension1, "raw", context.getPackageName());

        ivIntro1 = findViewById(R.id.ivIntro1);
        InputStream imageStream = context.getResources().openRawResource(R.raw.territory1);
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        ivIntro1.getLayoutParams().height = 630;
        ivIntro1.setImageBitmap(bitmap);


        myOnClickListener(ivIntro1, fileId1);


        tvIntro2 = findViewById(R.id.tvIntro2);
        String mdFilePath2 = "intro2.md";
        loadMarkdownIntoTextView(mdFilePath2, tvIntro2);


//        String fileStringNoExtension2 = "territory2";
//        fileId2  = context.getResources().getIdentifier(fileStringNoExtension2, "raw", context.getPackageName());

//        ivIntro2 = findViewById(R.id.ivIntro2);
//        InputStream imageStream2 = context.getResources().openRawResource(fileId2);
//        Bitmap bitmap2 = BitmapFactory.decodeStream(imageStream2);
//        ivIntro2.getLayoutParams().height = 630;
//        ivIntro2.setImageBitmap(bitmap2);
//
//        myOnClickListener(ivIntro2, fileId2);
//        ivIntro2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d("Main", "IMAGE INTRO CLICKED");
//
//
////                Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, ViewAdapterImageIntro.class);
//                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("image_id", fileId2);
//
//
//                context.startActivity(intent);
//
//
//            }
//
//        });


    }

    private void myOnClickListener(ImageView ivIntro, int imageId) {



        ivIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Main", "IMAGE THUMB CLICKED");


                //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewAdapterImageIntro.class);
                intent.putExtra("image_id", imageId);
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);


            }

        });

    }


        private void loadMarkdownIntoTextView(String mdFilePath, TextView tvIntro) {


        try {

           

            StringBuilder buf = new StringBuilder();


            final Markwon markwonIntro = Markwon.builder(getBaseContext())
                    // create default instance of TablePlugin
                    .usePlugin(TablePlugin.create(getBaseContext()))
                    .build();

//            final Markwon markwonIntro = Markwon.create(getBaseContext());
            InputStream mdFile = getAssets().open(mdFilePath);
            BufferedReader in = new BufferedReader(new InputStreamReader(mdFile, "UTF-8"));
            String str;
            while((str = in.readLine()) != null) {
                buf.append(str).append("\n");
            }
            in.close();
            markwonIntro.setMarkdown(tvIntro, buf.toString());

        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }


}
