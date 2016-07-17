package avrotools.external;

public class ExternalDatamodel {
    private String theFirstField;
    private int theSecondField;

    public ExternalDatamodel() {
    }

    public ExternalDatamodel(String theFirstField, int theSecondField) {
        this.theFirstField = theFirstField;
        this.theSecondField = theSecondField;
    }

    public String getFirstField() {
        return theFirstField;
    }

    public void setFirstField(String theFirstField) {
        this.theFirstField = theFirstField;
    }

    public int getSecondField() {
        return theSecondField;
    }

    public void setSecondField(int theSecondField) {
        this.theSecondField = theSecondField;
    }

    @Override
    public String toString() {
        return "ExternalDatamodel{" +
                "theFirstField='" + theFirstField + '\'' +
                ", theSecondField=" + theSecondField +
                '}';
    }
}
