package japiim.dic.morekuyubim.por.get_table_values;


public class GetGlossesTableValues {
    private long senseBundleId;
    private long glossId;
    private String gloss;
    private String entryRef;

    public GetGlossesTableValues(long senseBundleId, long glossId, String gloss, String entryRef) {
        this.senseBundleId = senseBundleId;
        this.glossId = glossId;
        this.gloss = gloss;
        this.entryRef = entryRef;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getGlossId() {
        return glossId;
    }

    public String getGloss() {
        return gloss;
    }


    public String getEntryRef() {
        return entryRef;
    }
}
