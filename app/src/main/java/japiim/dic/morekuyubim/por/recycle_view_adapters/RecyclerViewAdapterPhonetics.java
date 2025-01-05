
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static japiim.dic.morekuyubim.por.MainActivity.nightMode;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabasePronsAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetPhoneticsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetPronsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterPhonetics extends RecyclerView.Adapter<RecyclerViewAdapterPhonetics.ViewHolder> {

    Context context;
    List<GetPhoneticsTableValues> phoneticsList;
    RecyclerView rvPhonetics;
    MediaPlayer mediaPlayer;


    // Audio manager instance to manage or
    // handle the audio interruptions
    //AudioManager audioManager;

    // Audio attributes instance to set the playback
    // attributes for the media player instance
    // these attributes specify what type of media is
    // to be played and used to callback the audioFocusChangeListener
    AudioAttributes playbackAttributes;

    // media player is handled according to the
    // change in the focus which Android system grants for

    RecyclerView.LayoutManager layoutManager;

    DatabasePronsAdapter databasePronsAdapter;
    RecyclerView rvProns;
    RecyclerViewAdapterProns recyclerViewAdapterProns;
    List<GetPronsTableValues> pronsList = new ArrayList<>();
    long phoneticId;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowPhoneticId;
        TextView rowPhonetic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowPhoneticId = itemView.findViewById(R.id.tvPhoneticId);
            rowPhonetic = itemView.findViewById(R.id.tvPhonetic);
        }
    }

    public RecyclerViewAdapterPhonetics(Context context, List<GetPhoneticsTableValues> phoneticsList, RecyclerView rvPhonetics){
        this.context = context;
        this.phoneticsList = phoneticsList;
        this.rvPhonetics = rvPhonetics;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterPhonetics.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_phonetic, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterPhonetics.ViewHolder viewHolder, int i) {
        GetPhoneticsTableValues getPhoneticTableValues = phoneticsList.get(i);


        //viewHolder.rowPhoneticId.setText("phonetic id: "+getPhoneticTableValues.getPhoneticId());
        viewHolder.rowPhonetic.setText(""+getPhoneticTableValues.getPhonetic());


        int source_lang = getPhoneticTableValues.getSourceLang();
        if(source_lang==1) {


            if(nightMode ==1){

                viewHolder.rowPhonetic.setTextColor(ContextCompat.getColor(context,  R.color.source_01_dark));

            }else{

                viewHolder.rowPhonetic.setTextColor(ContextCompat.getColor(context,  R.color.source_01));

            }

        }else if(source_lang==2){

            if(nightMode ==1){

                viewHolder.rowPhonetic.setTextColor(ContextCompat.getColor(context,  R.color.source_02_dark));

            }else{

                viewHolder.rowPhonetic.setTextColor(ContextCompat.getColor(context,  R.color.source_02));

            }

        }


        phoneticId = getPhoneticTableValues.getPhoneticId();


        databasePronsAdapter = new DatabasePronsAdapter(context, phoneticId, 0);
        pronsList = databasePronsAdapter.getProns();
//        rvProns =  viewHolder.itemView.findViewById(R.id.rvProns);
        rvProns = RecyclerViewAdapterVernaculars.rvProns;
        rvProns.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvProns.setLayoutManager(layoutManager);
        recyclerViewAdapterProns = new RecyclerViewAdapterProns(context, pronsList, rvProns);
        rvProns.setAdapter(recyclerViewAdapterProns);


    }


    public void onDestroy() {
        // TODO Auto-generated method stub


    }
    @Override
    public int getItemCount() {
        return phoneticsList.size();
    }

}
