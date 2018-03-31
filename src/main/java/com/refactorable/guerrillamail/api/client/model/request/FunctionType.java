package com.refactorable.guerrillamail.api.client.model.request;

public enum FunctionType {

    GET_EMAIL_ADDRESS( "get_email_address" ),
    SET_EMAIL_USER( "set_email_user" ),
    CHECK_EMAIL( "check_email" ),
    GET_EMAIL_LIST( "get_email_list" ),
    OLDER_EMAIL_LIST( "older_email_list" ),
    FETCH_EMAIL( "fetch_email" ),
    FORGET_ME( "forget_me" );

    private final String function;

    FunctionType( String function ) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "FunctionType{" +
                "function='" + function + '\'' +
                '}';
    }
}
