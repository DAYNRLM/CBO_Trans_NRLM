package com.nrlm.cbo.Utils;

public enum ServiceMessage {
    SUCCESS_RESPONSE("E200", "Success"), INVALIDFIELDDATA("E202", "Required Filed Data is not valid "),
    INTERNALSERVERERROR("E205", "Internal server error"), NO_RECORD_FOUND("E203", "No record found."),
    INVALIDSECURITYTOKEN("E201", "Security validation failed"),
    VALIDATIONFAILD("E1004", "validation is failed"),
    SESSIONEXISTALREADY("E206", "Session already exists");

    private final String errorCode, errorMessage;

     ServiceMessage(String errorCode, String errorMessage) {
        this.errorCode=errorCode;
        this.errorMessage=errorMessage;
    }

    public  String getErrorCode() {
        return errorCode;
    }

    public  String getErrorMessage() {
        return errorMessage;
    }
}
