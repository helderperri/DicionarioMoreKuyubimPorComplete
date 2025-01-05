package japiim.dic.morekuyubim.por.get_table_values;


public class GetSemanticDomainNamesTableValues {
    private long semanticDomainId;
    private long semanticDomainNameId;
    private String semanticDomainName;
    private String langCode;

    public GetSemanticDomainNamesTableValues(long semanticDomainId, long semanticDomainNameId, String langCode, String semanticDomainName) {
        this.semanticDomainId = semanticDomainId;
        this.semanticDomainNameId = semanticDomainNameId;
        this.semanticDomainName = semanticDomainName;
        this.langCode = langCode;

    }


    public long getSemanticDomainNameId() {
        return semanticDomainNameId;
    }

    public long getSemanticDomainId() {
        return semanticDomainId;
    }




    public String getLangCode() {
        return langCode;
    }

    public String getSemanticDomainName() {
        return semanticDomainName;
    }
}
