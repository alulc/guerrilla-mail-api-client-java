package com.refactorable.guerrillamail.api.client.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties( ignoreUnknown = true )
public class EmailsResponse {

    private final List<EmailResponse> emails;

    @JsonCreator
    public EmailsResponse( @JsonProperty( "list" ) List<EmailResponse> emails ) {
        this.emails = emails;
    }

    public List<EmailResponse> getEmails() {
        return emails;
    }

    @Override
    public String toString() {
        return "EmailsResponse{" +
                "emails=" + emails +
                '}';
    }
}
