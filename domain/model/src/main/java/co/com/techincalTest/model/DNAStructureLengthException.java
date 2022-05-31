package co.com.techincalTest.model;

import java.io.Serializable;

public class DNAStructureLengthException extends Exception implements Serializable {

    private static final long serialVersionUID = -1701103574391064913L;
    private String erroMessage;

    public String getErroMessage() {
        return erroMessage;
    }

    public void setErroMessage(String erroMessage) {
        this.erroMessage = erroMessage;
    }
    public DNAStructureLengthException(String erroMessage) {
        this.erroMessage  = erroMessage;
    }
}
