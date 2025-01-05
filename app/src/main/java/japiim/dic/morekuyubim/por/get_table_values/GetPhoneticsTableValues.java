package japiim.dic.morekuyubim.por.get_table_values;


public class GetPhoneticsTableValues {
    private long phonemicId;
    private long phoneticId;
    private String entryRef;
    private String phonetic;
    private String langCode;
    private int sourceLang;


    public GetPhoneticsTableValues(long phonemicId, String entryRef, long phoneticId, String phonetic,
                                   int sourceLang, String langCode) {
        this.phonemicId = phonemicId;
        this.phoneticId = phoneticId;
        this.entryRef = entryRef;
        this.phonetic = phonetic;
        this.sourceLang = sourceLang;
        this.langCode = langCode;

    }


    public long getPhoneticId() {
        return phoneticId;
    }

    public long getPhonemicId() {
        return phonemicId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getPhonetic() {
        return phonetic;
    }

    public String getLangCode() {
        return langCode;
    }

    public int getSourceLang() {
        return sourceLang;
    }
}
