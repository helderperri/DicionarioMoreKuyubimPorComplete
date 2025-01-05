package japiim.dic.morekuyubim.por.get_table_values;

public class GetExamplesTranslationTableValues {
    private long exampleBundleId;
    private long exampleTranslationId;
    private String exampleTranslation;
    private String entryRef;



    public GetExamplesTranslationTableValues(long exampleBundleId, String entryRef, long exampleTranslationId, String exampleTranslation) {
        this.exampleTranslationId = exampleTranslationId;
        this.exampleBundleId = exampleBundleId;
        this.exampleTranslation = exampleTranslation;
        this.entryRef = entryRef;

    }


    public long getExampleTranslationId() {
        return exampleTranslationId;
    }

    public long getExampleBundleId() {
        return exampleBundleId;
    }


    public String getExampleTranslation() {
        return exampleTranslation;
    }

    public String getEntryRef() {
        return entryRef;
    }
}
