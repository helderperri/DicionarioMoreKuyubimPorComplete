package japiim.dic.morekuyubim.por.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import japiim.dic.morekuyubim.por.database_adapters.DatabaseEntriesVernacularAdapter;
import japiim.dic.morekuyubim.por.database_adapters.DatabaseSourceLettersAdapter;
import japiim.dic.morekuyubim.por.get_table_values.GetEntriesVernacularTableValues;
import japiim.dic.morekuyubim.por.get_table_values.GetSourceLettersTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntriesScrollDisplaySl;


public class AlphabeticSourceFragmentNewInstance extends Fragment {
    RecyclerViewAdapterEntriesScrollDisplaySl recyclerViewAdapterEntriesScrollDisplaySl;


    RecyclerView.LayoutManager layoutManager;
    DatabaseSourceLettersAdapter databaseSourceLettersAdapter;
    List<GetSourceLettersTableValues> sourceLettersList = new ArrayList<>();

    DatabaseEntriesVernacularAdapter databaseEntriesVernacularAdapter;

    List<GetEntriesVernacularTableValues> entriesVernacularList = new ArrayList<>();

    public static AlphabeticSourceFragmentNewInstance newInstance() {
        return new AlphabeticSourceFragmentNewInstance();
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

        databaseSourceLettersAdapter = new DatabaseSourceLettersAdapter(getActivity(), 0);
        sourceLettersList = databaseSourceLettersAdapter.getSourceLetters();

        int position = getArguments().getInt("position");

        GetSourceLettersTableValues getSourceLettersTableValues = sourceLettersList.get(position);

        long glyphId = getSourceLettersTableValues.getGlyphId();


        databaseEntriesVernacularAdapter = new DatabaseEntriesVernacularAdapter(getContext(), 0, 0, 0);

        entriesVernacularList = databaseEntriesVernacularAdapter.getVernacularsToSearchDisplayByGlyphId(glyphId);

        RecyclerView rvEntries = view.findViewById(R.id.rvEntries);

        recyclerViewAdapterEntriesScrollDisplaySl = new RecyclerViewAdapterEntriesScrollDisplaySl(getActivity(), entriesVernacularList, rvEntries);


        rvEntries.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        rvEntries.setLayoutManager(layoutManager);

        rvEntries.setAdapter(recyclerViewAdapterEntriesScrollDisplaySl);




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
