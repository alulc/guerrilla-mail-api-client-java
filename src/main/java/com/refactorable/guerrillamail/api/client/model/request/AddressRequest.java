package com.refactorable.guerrillamail.api.client.model.request;

import org.apache.commons.lang3.Validate;

public class AddressRequest {

    private final FunctionType functionType;
    private final String sessionId;
    private final String emailUser;

    /**
     *
     * @param functionType cannot be null
     * @param sessionId optional
     * @param emailUser optional
     */
    private AddressRequest(
            FunctionType functionType,
            String sessionId,
            String emailUser ) {

        Validate.notNull( functionType, "'functionType' cannot be null" );

        this.functionType = functionType;
        this.sessionId = sessionId;
        this.emailUser = emailUser;
    }

    /**
     *
     * @return
     */
    public static AddressRequest initialize() {

        return new AddressRequest(
                FunctionType.GET_EMAIL_ADDRESS,
                null,
                null );
    }

    /**
     *
     * @param sessionId cannot be blank
     * @return email address from active session
     */
    public static AddressRequest remember( String sessionId ) {

        Validate.notBlank( sessionId, "'sessionId' cannot be blank" );

        return new AddressRequest(
                FunctionType.GET_EMAIL_ADDRESS,
                sessionId,
                null );
    }

    /**
     *
     * @param sessionId cannot be blank
     * @return new email address
     */
    public static AddressRequest forget( String sessionId ) {

        Validate.notBlank( sessionId, "'sessionId' cannot be blank" );

        return new AddressRequest(
                FunctionType.FORGET_ME,
                sessionId,
                null );

    }

    /**
     *
     * @param sessionId cannot be blank
     * @param emailUser cannot be blank, must be alphanumeric, and must be less than 75 characters
     * @return
     */
    public static AddressRequest custom(
            String sessionId,
            String emailUser ) {

        Validate.notBlank( sessionId, "'sessionId' cannot be blank" );

        Validate.notBlank( emailUser, "'emailUser' cannot be blank" );
        Validate.isTrue( emailUser.matches( "[a-zA-Z0-9]*" ), "'emailUser' must be alphanumeric" );
        Validate.isTrue( emailUser.length() <= 74, "'emailUser' must be less than 75 characters" );

        return new AddressRequest(
                FunctionType.SET_EMAIL_USER,
                sessionId,
                emailUser );
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getEmailUser() {
        return emailUser;
    }

    @Override
    public String toString() {
        return "AddressRequest{" +
                "functionType=" + functionType +
                ", sessionId='" + sessionId + '\'' +
                ", emailUser='" + emailUser + '\'' +
                '}';
    }
}
