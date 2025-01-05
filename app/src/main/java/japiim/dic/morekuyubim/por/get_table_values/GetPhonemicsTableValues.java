package japiim.dic.morekuyubim.por.get_table_values;


public class GetPhonemicsTableValues {
    private long phonemicId;
    private long formId;
    private String entryRef;
    private String phonemic;
    private String langCode;
    private int sourceLang;


    public GetPhonemicsTableValues(long formId, String entryRef, long phonemicId, String phonemic,
                                   int sourceLang, String langCode) {
        this.phonemicId = phonemicId;
        this.formId = formId;
        this.entryRef = entryRef;
        this.phonemic = phonemic;
        this.sourceLang = sourceLang;
        this.langCode = langCode;

    }


    public long getPhonemicId() {
        return phonemicId;
    }

    public long getFormId() {
        return formId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getPhonemic() {
        return phonemic;
    }
    public String getLangCode() {
        return langCode;
    }
    public int getSourceLang() {
        return sourceLang;
    }

}
