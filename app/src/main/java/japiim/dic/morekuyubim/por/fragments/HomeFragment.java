package japiim.dic.morekuyubim.por.fragments;

import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.ViewAdapterIntroduction;
import japiim.dic.morekuyubim.por.ViewAdapterVideoPlay;


public class HomeFragment extends Fragment {

    Context context;

    final public View.OnClickListener onClickListener = new HomeFragment.MyOnClickListner();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);

        ImageView infoView = root.findViewById(R.id.InfoButton);

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        infoView.setOnClickListener(onClickListener);

//        infoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Main", "Introduction CLICKED");
//                Log.d("Main", " GNM-JPP toIntroduction");
//                Intent intent = new Intent(context, ViewAdapterIntroduction.class);
//                context.startActivity(intent);
//
//                //int itemPosition = rvImages.getChildLayoutPosition(v);
//                //String image_string = imagesList.get(itemPosition).getImage();
//                //String entryRef = formsList.get(itemPosition).getEntryRef();
//                //long formId = formsList.get(itemPosition).getFormId();
//
//                //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(context, ViewAdapterVideoPlay.class);
////                intent.putExtra("video_id", videoId);
////                intent.putExtra("sense_bundle_id", senseBundleId);
////                intent.putExtra("video", videoStringNoExtension);
////                intent.putExtra("position", i);
//
//                //intent.putExtra("form_id", formId);
//                //intent.putExtra("vernacular", vernacular);
//                //intent.putExtra("entry_ref", entryRef);
////                context.startActivity(intent);
//
//
//            }
//
//        });


        return root;
    }


    private class MyOnClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {


            //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
            Log.d("Main", "Introduction CLICKED");
            Log.d("Main", " GNM-JPP toIntroduction");

            Intent intent = new Intent(getContext(), ViewAdapterIntroduction.class);
            intent.putExtra("info", 1);
            getActivity().startActivity(intent);


        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

}

