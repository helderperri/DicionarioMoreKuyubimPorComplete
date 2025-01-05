package japiim.dic.morekuyubim.por.get_table_values;


public class GetExamplePronsTableValues {
    private long exampleId;
    private long examplePronId;
    private String entryRef;
    private String examplePron;
    private String examplePhonetic;

    public GetExamplePronsTableValues(long exampleId, String entryRef, long examplePronId, String examplePron, String examplePhonetic) {
        this.exampleId = exampleId;
        this.examplePronId = examplePronId;
        this.entryRef = entryRef;
        this.examplePron = examplePron;
        this.examplePhonetic = examplePhonetic;

    }


    public long getExamplePronId() {
        return examplePronId;
    }

    public long getExampleId() {
        return exampleId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getExamplePron() {
        return examplePron;
    }

    public String getExamplePhonetic() {
        return examplePhonetic;
    }

}
