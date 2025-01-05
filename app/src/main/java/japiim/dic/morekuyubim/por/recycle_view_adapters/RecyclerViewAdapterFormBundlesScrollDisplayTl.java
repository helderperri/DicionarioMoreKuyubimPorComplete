package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static japiim.dic.morekuyubim.por.MainActivity.nightMode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseClassesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesVernacularAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseFormsAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseGlossesAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetClassesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesVernacularTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetFormsTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterFormBundlesScrollDisplayTl extends RecyclerView.Adapter<RecyclerViewAdapterFormBundlesScrollDisplayTl.ViewHolder> {

    Context context;
    DatabaseEntriesVernacularAdapter databaseEntriesVernacularAdapter;
    List<GetEntriesVernacularTableValues> getEntriesVernacular;
    List<GetEntriesVernacularTableValues> entriesVernacularList;


    RecyclerView rvFormBundlesScrollDisplayTl;

    RecyclerView rvFormBundles;
    RecyclerView rvGlossesScrollDisplayTl;
    RecyclerView.LayoutManager layoutManager;
    DatabaseGlossesAdapter databaseGlossesAdapter;
    long formBundleId;
    long entryBundleId;
    long source_lang;
    String vernacular;
    String lang_code;
    String partOfSpeech;

    DatabaseFormsAdapter databaseFormsAdapter;
    List<GetFormsTableValues> formsList;

    DatabaseClassesAdapter databaseClassesAdapter;
    List<GetClassesTableValues> classesList;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView rowVernacular;
        TextView rowLangCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowVernacular = itemView.findViewById(R.id.tvVernacularScrollDisplayTl);
            rowLangCode = itemView.findViewById(R.id.tvLangCode);
        }
    }

    public RecyclerViewAdapterFormBundlesScrollDisplayTl(Context context, List<GetEntriesVernacularTableValues> entriesVernacularList, RecyclerView rvFormBundlesScrollDisplayTl){
        this.context = context;
        this.entriesVernacularList = entriesVernacularList;
        this.rvFormBundlesScrollDisplayTl = rvFormBundlesScrollDisplayTl;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterFormBundlesScrollDisplayTl.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_form_bundles_scroll_display_tl, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFormBundlesScrollDisplayTl.ViewHolder viewHolder, int i) {
        GetEntriesVernacularTableValues entryVernacular = entriesVernacularList.get(i);

        vernacular = entryVernacular.getVernacular();
        lang_code = entryVernacular.getLangCode();
        source_lang = entryVernacular.getSourceLang();


        int numberOfSls = context.getResources().getInteger(R.integer.number_of_sls);

        viewHolder.rowVernacular.setText(""+ vernacular);

        if(numberOfSls == 1){

            viewHolder.rowLangCode.setText("");

        }else if (numberOfSls >= 2){

            viewHolder.rowLangCode.setText(""+" ["+lang_code+"]");
        }


        if(source_lang==1) {


            if(nightMode ==1){

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01_dark));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01_dark));

            }else{

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_01));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_01));

            }

        }else if(source_lang==2){

            if(nightMode ==1){

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02_dark));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02_dark));

            }else{

                viewHolder.rowVernacular.setTextColor(ContextCompat.getColor(context,  R.color.source_02));
                viewHolder.rowLangCode.setTextColor(ContextCompat.getColor(context,R.color.source_02));

            }

        }

    }

    @Override
    public int getItemCount() {
        return entriesVernacularList.size();
    }
}
