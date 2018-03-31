package com.refactorable.guerrillamail.api.client.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

@JsonIgnoreProperties( ignoreUnknown = true )
public class AddressResponse {

    private final String sessionId;
    private final String address;
    private final String alias;
    private final String timestamp;

    /**
     *
     * @param sessionId cannot be blank
     * @param address cannot be blank
     * @param alias cannot be blank
     * @param timestamp cannot be blank
     */
    @JsonCreator
    public AddressResponse(
            @JsonProperty( "sid_token" ) String sessionId,
            @JsonProperty( "email_addr" ) String address,
            @JsonProperty( "alias" ) String alias,
            @JsonProperty( "email_timestamp" ) String timestamp ) {

        Validate.notBlank( sessionId, "'sessionId' cannot be blank" );
        Validate.notBlank( address, "'address' cannot be blank" );
        Validate.notBlank( alias, "'alias' cannot be blank" );
        Validate.notBlank( timestamp, "'timestamp' cannot be blank" );

        this.sessionId = sessionId;
        this.address = address;
        this.alias = alias;
        this.timestamp = timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getAddress() {
        return address;
    }

    public String getAlias() {
        return alias;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "AddressResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", address='" + address + '\'' +
                ", alias='" + alias + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
