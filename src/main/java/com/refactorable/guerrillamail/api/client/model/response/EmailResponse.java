package com.refactorable.guerrillamail.api.client.model.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.Validate;

@JsonIgnoreProperties( ignoreUnknown = true )
public class EmailResponse {

    private final Long id;
    private final String from;
    private final String recipient;
    private final String subject;
    private final String excerpt;
    private final String body;
    private final String timestamp;
    private final String contentType;
    private final Boolean read;

    /**
     *
     * @param id cannot be null
     * @param from cannot be blank
     * @param recipient cannot be blank
     * @param subject cannot be blank
     * @param excerpt cannot be blank
     * @param body cannot be blank
     * @param timestamp cannot be blank
     * @param contentType cannot be blank
     * @param read cannot be null
     */
    @JsonCreator
    public EmailResponse(
            @JsonProperty( "mail_id" ) Long id,
            @JsonProperty( "mail_from" ) String from,
            @JsonProperty( "mail_recipient" ) String recipient,
            @JsonProperty( "mail_subject" ) String subject,
            @JsonProperty( "mail_excerpt" ) String excerpt,
            @JsonProperty( "mail_body" ) String body,
            @JsonProperty( "mail_timestamp" ) String timestamp,
            @JsonProperty( "content_type" ) String contentType,
            @JsonProperty( "mail_read" ) Integer read ) {

        Validate.notNull( id, "'sessionId' cannot be null" );
        Validate.notBlank( from, "'from' cannot be blank" );
        Validate.notBlank( recipient, "'recipient' cannot be blank" );
        Validate.notBlank( subject, "'subject' cannot be blank" );
        Validate.notBlank( excerpt, "'excerpt' cannot be blank" );
        Validate.notBlank( body, "'body' cannot be blank" );
        Validate.notBlank( timestamp, "'timestamp' cannot be blank" );
        Validate.notBlank( contentType, "'contentType' cannot be blank" );
        Validate.notNull( read, "'read' cannot be null" );

        this.id = id;
        this.from = from;
        this.recipient = recipient;
        this.subject = subject;
        this.excerpt = excerpt;
        this.body = body;
        this.timestamp = timestamp;
        this.contentType = contentType;
        this.read = read == 1;
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getBody() {
        return body;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getContentType() {
        return contentType;
    }

    public Boolean getRead() {
        return read;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", body='" + body + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", contentType='" + contentType + '\'' +
                ", read=" + read +
                '}';
    }
}
