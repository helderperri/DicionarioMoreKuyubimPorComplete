
package japiim.dic.morekuyubim.por.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesGlossesAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseTargetLettersAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesGlossesTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetTargetLettersTableValues;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntriesScrollDisplayTl;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import japiim.dic.morekuyubim.por.R;


public class AlphabeticTargetFragmentNewInstance extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    RecyclerViewAdapterEntriesScrollDisplayTl recyclerViewAdapterEntriesScrollDisplayTl;

    DatabaseTargetLettersAdapter databaseTargetLettersAdapter;
    List<GetTargetLettersTableValues> targetLettersList = new ArrayList<>();


    DatabaseEntriesGlossesAdapter databaseEntriesGlossesAdapter;
    List<GetEntriesGlossesTableValues> entriesGlossesList = new ArrayList<>();


    public static AlphabeticTargetFragmentNewInstance newInstance() {
        return new AlphabeticTargetFragmentNewInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);



        initViews(view);
        return view;
    }

    private void initViews(View view) {

            databaseTargetLettersAdapter = new DatabaseTargetLettersAdapter(getContext(), 0);
            targetLettersList = databaseTargetLettersAdapter.getTargetLetters();

        int position = getArguments().getInt("position");
            GetTargetLettersTableValues getTargetLettersTableValues = targetLettersList.get(position);

            long glyphId = getTargetLettersTableValues.getGlyphId();

            databaseEntriesGlossesAdapter = new DatabaseEntriesGlossesAdapter(getContext(), 0, 0, 0);
            entriesGlossesList = databaseEntriesGlossesAdapter.getGlossesToSearchDisplayByGlyphId(glyphId);


            RecyclerView rvEntries = view.findViewById(R.id.rvEntries);

            recyclerViewAdapterEntriesScrollDisplayTl = new RecyclerViewAdapterEntriesScrollDisplayTl(getActivity(), entriesGlossesList, rvEntries);

            rvEntries.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getActivity());
            rvEntries.setLayoutManager(layoutManager);

            rvEntries.setAdapter(recyclerViewAdapterEntriesScrollDisplayTl);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
