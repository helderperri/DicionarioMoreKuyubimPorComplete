package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseClassesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseExampleBundlesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseGlossesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseImagesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSemanticDomainsAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSensesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseVideosAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetClassesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetExampleBundlesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetGlossesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSemanticDomainsTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSenseBundlesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSensesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetVideosTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterSenseBundles extends RecyclerView.Adapter<RecyclerViewAdapterSenseBundles.ViewHolder> {

    Context context;
    List<GetSenseBundlesTableValues> senseBundlesList;
    RecyclerView rvSenseBundles;



    RecyclerView.LayoutManager layoutManager;

    DatabaseSensesAdapter databaseSensesAdapter;
    List<GetSensesTableValues> sensesList;


    DatabaseClassesAdapter databaseClassesAdapter;
    List<GetClassesTableValues> classesList;


    DatabaseGlossesAdapter databaseGlossesAdapter;
    RecyclerView rvGlosses;
    RecyclerViewAdapterGlosses recyclerViewAdapterGlosses;
    List<GetGlossesTableValues> glossesList = new ArrayList<>();

    DatabaseExampleBundlesAdapter databaseExampleBundlesAdapter;
    RecyclerView rvExampleBundles;
    RecyclerViewAdapterExampleBundles recyclerViewAdapterExampleBundles;
    List<GetExampleBundlesTableValues> exampleBundlesList = new ArrayList<>();


    DatabaseImagesAdapter databaseImagesAdapter;
    RecyclerView rvImages;
    RecyclerViewAdapterImages recyclerViewAdapterImages;
    List<GetImagesTableValues> imagesList = new ArrayList<>();



    DatabaseVideosAdapter databaseVideosAdapter;
    RecyclerView rvVideos;
    RecyclerViewAdapterVideosThumb recyclerViewAdapterVideosThumb;
    List<GetVideosTableValues> videosList = new ArrayList<>();

    DatabaseSemanticDomainsAdapter databaseSemanticDomainsAdapter;
    RecyclerView rvSemanticDomains;
    RecyclerViewAdapterSemanticDomains recyclerViewAdapterSemanticDomains;
    List<GetSemanticDomainsTableValues> semanticDomainsList = new ArrayList<>();


    long senseBundleId;
    String def;
    String partOfSpeech;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        //TextView rowSenseBundleId;
        //TextView rowGlossId;
        //ImageView rowImage;
        TextView rowDef;
        TextView rowClass;
        TextView rowSenseBundleId;
        //TextView rowEntryId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //rowSenseBundleId = itemView.findViewById(R.id.sense_bundle_id);
            //rowGlossId = itemView.findViewById(R.id.gloss_id);
            //rowSenseBundleId = itemView.findViewById(R.id.tvSenseBundleId);
            rowDef = itemView.findViewById(R.id.tvDefinition);
            rowClass = itemView.findViewById(R.id.tvClass);
            //rowEntryId = itemView.findViewById(R.id.tvEntryId);
            //rowImage = itemView.findViewById(R.id.item_image);
        }
    }

    public RecyclerViewAdapterSenseBundles(Context context, List<GetSenseBundlesTableValues> senseBundlesList, RecyclerView rvSenseBundles){
        this.context = context;
        this.senseBundlesList = senseBundlesList;
        this.rvSenseBundles = rvSenseBundles;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterSenseBundles.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_sense_bundle, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterSenseBundles.ViewHolder viewHolder, int i) {
        GetSenseBundlesTableValues getSenseBundlesTableValues = senseBundlesList.get(i);
        //viewHolder.rowSenseBundleId.setText(""+gloss.getSenseBundleId());
        //viewHolder.rowGlossId.setText(""+gloss.getGlossId());
        //viewHolder.rowSenseBundleId.setText("sense bundle id: "+getSenseBundlesTableValues.getSenseBundleId());
        //viewHolder.rowEntryId.setText(""+getSenseBundlesTableValues.getEntryId());
        //String imageString = country.getImage();
        //int image = context.getResources().getIdentifier(imageString, "drawable", context.getPackageName());
        //viewHolder.rowImage.setImageResource(image);


        senseBundleId = getSenseBundlesTableValues.getSenseBundleId();

        databaseSensesAdapter = new DatabaseSensesAdapter(context, senseBundleId, 0);
        def = databaseSensesAdapter.getDef(senseBundleId);
        viewHolder.rowDef.setText(""+ def);

        databaseClassesAdapter = new DatabaseClassesAdapter(context, senseBundleId, 0, 0);
        //long classId = databaseClassesAdapter.getClassId(senseBundleId);
        partOfSpeech = databaseClassesAdapter.getClassName(senseBundleId);
        viewHolder.rowClass.setText("("+ partOfSpeech+")");

/*
        databaseGlossesAdapter = new DatabaseGlossesAdapter(context, senseBundleId, 0);
        glossesList = databaseGlossesAdapter.getGlosses();
        rvGlosses = viewHolder.itemView.findViewById(R.id.rvGlosses);
        rvGlosses.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvGlosses.setLayoutManager(layoutManager);
        recyclerViewAdapterGlosses = new RecyclerViewAdapterGlosses(context, glossesList, rvGlosses);
        rvGlosses.setAdapter(recyclerViewAdapterGlosses);
*/


        databaseExampleBundlesAdapter = new DatabaseExampleBundlesAdapter(context, senseBundleId);
        exampleBundlesList = databaseExampleBundlesAdapter.getExampleBundles();
        rvExampleBundles = viewHolder.itemView.findViewById(R.id.rvExampleBundles);
        rvExampleBundles.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvExampleBundles.setLayoutManager(layoutManager);
        recyclerViewAdapterExampleBundles = new RecyclerViewAdapterExampleBundles(context, exampleBundlesList, rvExampleBundles);
        rvExampleBundles.setAdapter(recyclerViewAdapterExampleBundles);


        databaseImagesAdapter = new DatabaseImagesAdapter(context, senseBundleId, 0);
        imagesList = databaseImagesAdapter.getImages();
        rvImages = viewHolder.itemView.findViewById(R.id.rvImages);

        rvImages.setHasFixedSize(true);
        //layoutManager = new LinearLayoutManager(context);
        //rvImages.setLayoutManager(layoutManager);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvImages.setLayoutManager(horizontalLayoutManager);
        recyclerViewAdapterImages = new RecyclerViewAdapterImages(context, imagesList, rvImages);
        rvImages.setAdapter(recyclerViewAdapterImages);



        databaseVideosAdapter = new DatabaseVideosAdapter(context, senseBundleId, 0);
        videosList = databaseVideosAdapter.getVideos();
        rvVideos = viewHolder.itemView.findViewById(R.id.rvVideos);
        rvVideos.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagerViideoThumb
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        rvVideos.setLayoutManager(horizontalLayoutManagerViideoThumb);
        recyclerViewAdapterVideosThumb = new RecyclerViewAdapterVideosThumb(context, videosList, rvVideos);
        rvVideos.setAdapter(recyclerViewAdapterVideosThumb);

        databaseSemanticDomainsAdapter = new DatabaseSemanticDomainsAdapter(context, senseBundleId, 0, 0);
        semanticDomainsList = databaseSemanticDomainsAdapter.getSemanticDomains();
        rvSemanticDomains = viewHolder.itemView.findViewById(R.id.rvSemanticDomains);
        rvSemanticDomains.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        rvSemanticDomains.setLayoutManager(layoutManager);
        recyclerViewAdapterSemanticDomains = new RecyclerViewAdapterSemanticDomains(context, semanticDomainsList, rvSemanticDomains);
        rvSemanticDomains.setAdapter(recyclerViewAdapterSemanticDomains);


    }


    public void onDestroy() {
        // TODO Auto-generated method stub
        if (recyclerViewAdapterExampleBundles.getItemCount() != 0){

        recyclerViewAdapterExampleBundles.onDestroy();
        Log.d("Main", " GNM-JPP ON DESTROY SENSE BUNDLES ADAPTER");}

    }


    @Override
    public int getItemCount() {
        return senseBundlesList.size();
    }

}
