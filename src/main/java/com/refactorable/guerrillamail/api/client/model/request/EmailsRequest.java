package com.refactorable.guerrillamail.api.client.model.request;

import org.apache.commons.lang3.Validate;

public class EmailsRequest {

    private final FunctionType functionType;
    private final String sessionId;
    private final Long sequenceId;
    private final Integer offset;

    /**
     *
     * @param functionType cannot be null
     * @param sessionId optional
     * @param sequenceId optional
     * @param offset optional
     */
    private EmailsRequest(
            FunctionType functionType,
            String sessionId,
            Long sequenceId,
            Integer offset ) {

        Validate.notNull( functionType, "'functionType' cannot be null" );

        this.functionType = functionType;
        this.sessionId = sessionId;
        this.sequenceId = sequenceId;
        this.offset = offset;
    }

    /**
     *
     * @param sessionId cannot be blank
     * @param sequenceId cannot be null
     * @return
     */
    public static EmailsRequest check(
            String sessionId,
            Long sequenceId ) {

        Validate.notBlank( sessionId, "'sessionId' cannot be blank" );
        Validate.notNull( sequenceId, "'sequenceId' cannot be null" );

        return new EmailsRequest(
                FunctionType.CHECK_EMAIL,
                sessionId,
                sequenceId,
                null );
    }

    /**
     *
     * @param sessionId cannot be blank
     * @param sequenceId cannot be null
     * @param offset cannot be null
     * @return
     */
    public static EmailsRequest emails(
            String sessionId,
            Long sequenceId,
            Integer offset ) {

        Validate.notBlank( sessionId, "'sessionId' cannot be blank" );
        Validate.notNull( sequenceId, "'sequenceId' cannot be null" );
        Validate.notNull( offset, "'offset' cannot be null" );

        return new EmailsRequest(
                FunctionType.GET_EMAIL_LIST,
                sessionId,
                sequenceId,
                offset );
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public Integer getOffset() {
        return offset;
    }

    @Override
    public String toString() {
        return "EmailsRequest{" +
                "functionType=" + functionType +
                ", sessionId='" + sessionId + '\'' +
                ", sequenceId=" + sequenceId +
                ", offset=" + offset +
                '}';
    }
}
