
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static japiim.dic.morekuyubim.por.MainActivity.nightMode;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseExamplePronsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplePhoneticsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplePronsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplesVernacularTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterExamplesVernacular extends RecyclerView.Adapter<RecyclerViewAdapterExamplesVernacular.ViewHolder> {

    Context context;
    List<GetExamplesVernacularTableValues> examplesVernacularList;
    RecyclerView rvExamplesVernacular;


    RecyclerView.LayoutManager layoutManager;

    DatabaseExamplePronsAdapter databaseExamplePronsAdapter;
    RecyclerView rvExampleProns;
    RecyclerViewAdapterExampleProns recyclerViewAdapterExampleProns;
    RecyclerView rvExamplePhonetics;
    RecyclerViewAdapterExamplePhonetics recyclerViewAdapterExamplePhonetics;
    List<GetExamplePronsTableValues> pronsList = new ArrayList<>();
    List<GetExamplePhoneticsTableValues> phoneticsList = new ArrayList<>();

    List<String> examplePhoneticList = new ArrayList<String>();
    String examplePhonetic;
    long exampleId;
    long sourceLang;
    String langCode;
    String exampleVernacular;
    MediaPlayer mediaPlayer;


    /*
     Audio manager instance to manage or
     handle the audio interruptions
    AudioManager audioManager;

     Audio attributes instance to set the playback
     attributes for the media player instance
     these attributes specify what type of media is
     to be played and used to callback the audioFocusChangeListener
    */
    AudioAttributes playbackAttributes;



    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowExampleVernacularId;
        TextView rowExampleVernacular;
//        TextView rowLangCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowExampleVernacularId = itemView.findViewById(R.id.tvExampleId);
            rowExampleVernacular = itemView.findViewById(R.id.tvExampleVernacular);
//            rowLangCode = itemView.findViewById(R.id.tvLangCode);
        }
    }

    public RecyclerViewAdapterExamplesVernacular(Context context, List<GetExamplesVernacularTableValues> examplesVernacularList, RecyclerView rvExamplesVernacular){
        this.context = context;
        this.examplesVernacularList = examplesVernacularList;
        this.rvExamplesVernacular = rvExamplesVernacular;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterExamplesVernacular.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        LayoutInflater inflater =LayoutInflater.from(context);

        Integer orientation = context.getResources().getConfiguration().orientation;
        ViewHolder viewHolder;

        View view = inflater.inflate(R.layout.single_item_example_vernacular, viewGroup, false);
        viewHolder = new ViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterExamplesVernacular.ViewHolder viewHolder, int i) {
        GetExamplesVernacularTableValues getExampleVernacularTableValues = examplesVernacularList.get(i);

        //viewHolder.rowExampleVernacularId.setText("example vernacular id: "+getExampleVernacularTableValues.getExampleId());
        exampleVernacular = getExampleVernacularTableValues.getExampleVernacular();
        langCode = getExampleVernacularTableValues.getLangCode();
        sourceLang = getExampleVernacularTableValues.getSourceLang();

        int numberOfSls = context.getResources().getInteger(R.integer.number_of_sls);

        viewHolder.rowExampleVernacular.setText(""+exampleVernacular);

        if(numberOfSls == 1){
//            viewHolder.rowExampleVernacular.setText("");
//            viewHolder.rowLangCode.setText("");

        }else if (numberOfSls >= 2){

//            viewHolder.rowLangCode.setText("");
//            viewHolder.rowLangCode.setText(""+" ["+langCode+"]");

        }

        if(sourceLang==1) {


            if(nightMode ==1){

                viewHolder.rowExampleVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01_dark));
//                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01_dark));

            }else{

                viewHolder.rowExampleVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01));
//                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01));

            }

        }else if(sourceLang==2){

            if(nightMode ==1){

                viewHolder.rowExampleVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02_dark));
//                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02_dark));

            }else{

                viewHolder.rowExampleVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02));
//                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02));

            }

        }

        exampleId = getExampleVernacularTableValues.getExampleId();


        databaseExamplePronsAdapter = new DatabaseExamplePronsAdapter(context, exampleId, 0);
        pronsList = databaseExamplePronsAdapter.getExampleProns();

        layoutManager = new LinearLayoutManager(context);

        rvExampleProns = viewHolder.itemView.findViewById(R.id.rvExampleProns);
        rvExampleProns.setHasFixedSize(true);

        rvExampleProns.setLayoutManager(layoutManager);
        recyclerViewAdapterExampleProns = new RecyclerViewAdapterExampleProns(context, pronsList, rvExampleProns);
        rvExampleProns.setAdapter(recyclerViewAdapterExampleProns);


        for (int r = 0; r < pronsList.size(); r++) {
            GetExamplePronsTableValues getExamplePronsTableValues = pronsList.get(r);
            examplePhonetic = getExamplePronsTableValues.getExamplePhonetic();
            if (examplePhonetic == null || examplePhonetic.isEmpty() || examplePhonetic.trim().isEmpty()){
                //Log.d("MAIN", "EX PHONETIC EMPTY"+examplePhonetic.length());
            }else{
                //Log.d("MAIN", "EX PHONETIC NOT EMPTY"+examplePhonetic.length());
                examplePhoneticList.add(examplePhonetic);

            }

        }
        if (examplePhoneticList == null || examplePhoneticList.size() == 0) {

        }else {
            GetExamplePronsTableValues first = pronsList.get(0);
            Log.d("MAIN", "LIST_NOT_NUL"+examplePhoneticList.size());

            rvExamplePhonetics = viewHolder.itemView.findViewById(R.id.rvExamplePhonetics);
            rvExamplePhonetics.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(context);

            rvExamplePhonetics.setLayoutManager(layoutManager);
            recyclerViewAdapterExamplePhonetics = new RecyclerViewAdapterExamplePhonetics(context, pronsList, rvExamplePhonetics);
            rvExamplePhonetics.setAdapter(recyclerViewAdapterExamplePhonetics);

        }


    }



    public void onDestroy() {
        // TODO Auto-generated method stub
        //recyclerViewAdapterExampleProns.onDestroy();
        Log.d("Main", " GNM-JPP ON DESTROY EXAMPLE VERNACULAR ADAPTER");

    }

    @Override
    public int getItemCount() {
        return examplesVernacularList.size();
    }

}
