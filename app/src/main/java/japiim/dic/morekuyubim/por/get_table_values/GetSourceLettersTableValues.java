package japiim.dic.morekuyubim.por.get_table_values;


public class GetSourceLettersTableValues {
    private long glyphOrder;
    private long glyphId;
    private long displayBtn;
    private long positionBtn;
    private String glyph;

    public GetSourceLettersTableValues(long glyphId, String glyph, long glyphOrder, long displayBtn, long positionBtn) {
        this.glyphOrder = glyphOrder;
        this.glyphId = glyphId;
        this.displayBtn = displayBtn;
        this.glyph = glyph;
        this.positionBtn =positionBtn;

    }


    public long getGlyphId() {
        return glyphId;
    }

    public long getGlyphOrder() {
        return glyphOrder;
    }

    public long getDisplayBtn() {
        return displayBtn;
    }
    public long getPositionBtn() {
        return positionBtn;
    }



    public String getGlyph() {
        return glyph;
    }

}
