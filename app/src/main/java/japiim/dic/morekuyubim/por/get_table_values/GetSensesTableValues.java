package japiim.dic.morekuyubim.por.get_table_values;


public class GetSensesTableValues {
    private long senseBundleId;
    private long senseId;
    private String entryRef;
    private String def;

    public GetSensesTableValues(long senseBundleId, String entryRef, long senseId, String def) {
        this.senseBundleId = senseBundleId;
        this.senseId = senseId;
        this.entryRef = entryRef;
        this.def = def;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getSenseId() {
        return senseId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getDef() {
        return def;
    }

}
