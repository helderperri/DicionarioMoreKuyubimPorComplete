package japiim.dic.morekuyubim.por.get_table_values;


public class GetSenseBundlesTableValues {
    private long senseBundleId;
    private long entryId;
    private String entryRef;

    public GetSenseBundlesTableValues(long entryId, String entryRef, long senseBundleId) {
        this.senseBundleId = senseBundleId;
        this.entryId = entryId;
        this.entryRef = entryRef;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getEntryId() {
        return entryId;
    }


    public String getEntryRef() {
        return entryRef;
    }
}
