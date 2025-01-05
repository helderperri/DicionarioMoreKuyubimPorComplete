
package japiim.dic.morekuyubim.por;

import static japiim.dic.morekuyubim.por.MyMediaPlayer.stopAllMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseFormBundlesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesTableValues;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterProns;

public class ViewAdapterEntryBundles extends AppCompatActivity {
    long formBundleIid;

    long entryBundleId;

    RecyclerView.LayoutManager layoutManager;


    List<GetEntriesTableValues> entriesList = new ArrayList<>();

    DatabaseFormBundlesAdapter databaseFormBundlesAdapter;

    DatabaseEntriesAdapter databaseEntriesAdapter;

    RecyclerView rvEntries;
    RecyclerViewAdapterEntries recyclerViewAdapterEntries;
    RecyclerViewAdapterProns recyclerViewAdapterProns;
    MediaPlayer mediaPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_entry_bundle);
        formBundleIid = getIntent().getExtras().getLong("form_bundle_id");
        //String vernacular = getIntent().getExtras().getString("vernacular");
        //String entry_ref = getIntent().getExtras().getString("entry_ref");



        /*
        These two TextViews tvGlossRef and tvEntryRefRef were put here just for reference!!!.
        The data that they hold is incomplete. There may be sibling data associated with them.
        The data in tvGlossRef should be retrieved through entry_bundle > entry > sense_bundle > glosses
        The data in tvEntryRefRef should be retrieved through entry_bundle > entry > form_bundle > forms

         */
        //tvGlossRef = findViewById(R.id.tvGlossRef);
        //tvEntryRefRef = findViewById(R.id.tvEntryRefRef);
        //tvGlossRef.setText(gloss);
        //vEntryRefRef.setText(entry_ref);




        /*
        use the senseBundleIid variable to get the entryBundleId variable, which is the primary id of the table "entry_bundles"
        on the top of the database hierarchy.

         */
        //databaseFormBundlesAdapter = new DatabaseFormBundlesAdapter(this, 0, formBundleIid);
        entryBundleId = getIntent().getExtras().getLong("entry_bundle_id");;

        //tvEntryBundleId = findViewById(R.id.tvEntryBundleId);

        //tvEntryBundleId.setText("entry bundle id: " + entryBundleId);




        /*

        Use the variable entryBundleId to retrieve the all entries in the "entries" table.
        Get each entryId.

        In the current database, there is only one entry in each entry_bundle (1-to-1 relation).
        Other databases however have more than one entry in each entry_bundle, that's
        why we must keep "entries" in a RecycleView.

         */


        databaseEntriesAdapter = new DatabaseEntriesAdapter(this, 0, entryBundleId);

        entriesList = databaseEntriesAdapter.getAllEntries(entryBundleId);
        rvEntries = findViewById(R.id.rvEntries);
        rvEntries.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvEntries.setLayoutManager(layoutManager);
        recyclerViewAdapterEntries = new RecyclerViewAdapterEntries(this, entriesList, rvEntries);
        rvEntries.setAdapter(recyclerViewAdapterEntries);


        /*
        senseBundlesList = databaseSenseBundlesAdapter.getSenseBundles();
        rvSenseBundles = findViewById(R.id.rvSenseBundles);
        rvSenseBundles.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvSenseBundles.setLayoutManager(layoutManager);
        recyclerViewAdapterSenseBundles = new RecyclerViewAdapterSenseBundles(this, senseBundlesList, rvSenseBundles);
        rvSenseBundles.setAdapter(recyclerViewAdapterSenseBundles);
        */





        /*
        databaseExampleBundlesAdapter = new DatabaseExampleBundlesAdapter(this, senseBundleIid);
        exampleBundlesList = databaseExampleBundlesAdapter.getExampleBundles();
        rvExampleBundles = findViewById(R.id.rvExampleBundles);
        rvExampleBundles.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvExampleBundles.setLayoutManager(layoutManager);
        recyclerViewAdapterExampleBundles = new RecyclerViewAdapterExampleBundles(this, exampleBundlesList, rvExampleBundles);
        rvExampleBundles.setAdapter(recyclerViewAdapterExampleBundles);
        */

/*
        for(int i=0; i<exampleBundlesList.size(); i++) {
            GetExampleBundlesTableValues getExampleBundlesTableValues = exampleBundlesList.get(i);
            // Do something with the value
            long example_bundle_id = getExampleBundlesTableValues.getExampleBundleId();
            //long example_bundle_id = exampleBundlesList.get(2).getExampleBundleId();
            int elementId = getResources().getIdentifier("tvExampleBundleId"+i+"", "id", getPackageName());
            tvExampleBundleId = findViewById(elementId);
            tvExampleBundleId.setText(""+example_bundle_id);


        }

*/
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub


        //recyclerViewAdapterEntries.onDestroy();

        Log.d("Main", " GNM-JPP ON DESTROY ENTRY BUNDLES ADAPTER");
        super.onDestroy();

        final AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                    Log.d("Main", " GNM-JPP EXAMPLE PRONS AUDIOFOCUS_GAIN");
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {

                    Log.d("Main", " GNM-JPP EXAMPLE PRONS AUDIOFOCUS_LOSS_TRANSIENT");
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {



                    if(mediaPlayer!=null){

                        /* mediaPlayer.stop();
                        if(mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }

                         */
                        mediaPlayer.release();
                        mediaPlayer = null;
                        Log.d("Main", " GNM-JPP EXAMPLE PRONS AUDIOFOCUS_LOSS file:");

                    }else {
                        Log.d("Main", " GNM-JPP ON AUDIOFOCUS_LOSS EXAMPLE PRONS ADAPTER -- AUDIO EXAMPLE PRON ALREADY RELEASED / NOT RELEASED NOW");
                    }


                }
            }
        };

        Log.d("Main", " GNM-JPP ON DESTROY PHONETICS ADAPTER");

        final AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // initiate the audio playback attributes
        AudioAttributes playbackAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();

        // set the playback attributes for the focus requester
        AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(playbackAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(audioFocusChangeListener)
                .build();

        // request the audio focus and
        // store it in the int variable
        final int audioFocusRequest = audioManager.requestAudioFocus(focusRequest);







        finish();

    }

/*
    @Override
    public void onStop () {
        // TODO Auto-generated method stub
        recyclerViewAdapterEntries.onDestroy();
        super.onStop();

        Log.d("Main", " GNM-JPP ON STOPPED ENTRY BUNDLES ADAPTER");

        finish();

    }
*/
    public void backToEntryList (View view){
        stopAllMediaPlayer();
        finish();

    }
}
