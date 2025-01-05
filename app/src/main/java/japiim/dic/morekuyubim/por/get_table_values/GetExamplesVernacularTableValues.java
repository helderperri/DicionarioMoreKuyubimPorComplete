package japiim.dic.morekuyubim.por.get_table_values;

public class GetExamplesVernacularTableValues {
    private long exampleBundleId;
    private long exampleId;
    private String exampleVernacular;
    private String entryRef;
    private long sourceLang;
    private String langCode;



    public GetExamplesVernacularTableValues(long exampleBundleId, String entryRef, long exampleId, String exampleVernacular, long sourceLang, String langCode) {
        this.exampleId = exampleId;
        this.exampleBundleId = exampleBundleId;
        this.exampleVernacular = exampleVernacular;
        this.entryRef = entryRef;
        this.sourceLang = sourceLang;
        this.langCode = langCode;

    }


    public long getExampleId() {
        return exampleId;
    }

    public long getExampleBundleId() {
        return exampleBundleId;
    }


    public String getExampleVernacular() {
        return exampleVernacular;
    }

    public String getEntryRef() {
        return entryRef;
    }

    public String getLangCode() {
        return langCode;
    }

    public long getSourceLang() {
        return sourceLang;
    }

}
