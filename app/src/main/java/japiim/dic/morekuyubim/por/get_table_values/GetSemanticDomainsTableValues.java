package japiim.dic.morekuyubim.por.get_table_values;


public class GetSemanticDomainsTableValues {
    private long senseBundleId;
    private long semanticDomainId;
    private long semanticDomainTokenId;
    private String entryRef;
    private String semanticDomainName;

    public GetSemanticDomainsTableValues(long senseBundleId, String entryRef, long semanticDomainTokenId, long semanticDomainId, String semanticDomainName) {
        this.senseBundleId = senseBundleId;
        this.semanticDomainId = semanticDomainId;
        this.entryRef = entryRef;
        this.semanticDomainTokenId = semanticDomainTokenId;
        this.semanticDomainName = semanticDomainName;

    }


    public long getSenseBundleId() {
        return senseBundleId;
    }

    public long getSemanticDomainId() {
        return semanticDomainId;
    }


    public long getSemanticDomainTokenId() {
        return semanticDomainTokenId;
    }


    public String getEntryRef() {
        return entryRef;
    }

    public String getSemanticDomainName() {
        return semanticDomainName;
    }

}
