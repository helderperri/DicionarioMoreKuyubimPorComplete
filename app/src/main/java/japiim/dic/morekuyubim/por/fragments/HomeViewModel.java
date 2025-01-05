package japiim.dic.morekuyubim.por.fragments;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class HomeViewModel extends ViewModel {

//
//    public void toIntroduction(View view) {
//
//        Log.d("Main", " GNM-JPP toIntroduction");
//    }
    private final MutableLiveData<String> mText;


    String dicName;

    public HomeViewModel() {
        mText = new MutableLiveData<>();

//        dicName = "Dicionário NOME DA lÍNGUA";

//        mText.setValue(dicName);
    }

    public LiveData<String> getText() {



        return mText;
    }
}