package com.refactorable.guerrillamail.api.client;

import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailsRequest;
import com.refactorable.guerrillamail.api.client.model.request.FunctionType;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailsResponse;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class GuerrillaMailClientImpl implements GuerrillaMailClient {

    private final static Logger log = LoggerFactory.getLogger( GuerrillaMailClientImpl.class );

    private final static String PARAM_NAME_FUNCTION = "f";
    private final static String PARAM_NAME_SESSION_ID = "sid_token";
    private final static String PARAM_NAME_SEQUENCE_ID = "seq";
    private final static String PARAM_NAME_OFFSET = "offset";
    private final static String PARAM_NAME_EMAIL_ID = "email_id";
    private final static String PARAM_NAME_EMAIL_USER = "email_user";

    private final WebTarget apiTarget;

    /**
     *
     * @param baseApiTarget cannot be null
     * @param apiPath cannot be blank
     */
    public GuerrillaMailClientImpl(
            WebTarget baseApiTarget,
            String apiPath ) {

        Validate.notNull( baseApiTarget, "'baseApiTarget' cannot be null" );
        Validate.notBlank( apiPath, "'apiPath' cannot be blank" );

        log.info( "initializing guerrilla mail client" );

        this.apiTarget = baseApiTarget.path( apiPath );
    }

    @Override
    public AddressResponse address( AddressRequest addressRequest ) {

        Validate.notNull( addressRequest, "'addressRequest' cannot be null" );

        log.debug( "making address request: {}", addressRequest );

        try{
            return apiTarget
                    .queryParam(
                            PARAM_NAME_FUNCTION,
                            addressRequest.getFunctionType().getFunction() )
                    .queryParam(
                            PARAM_NAME_SESSION_ID,
                            addressRequest.getSessionId() )
                    .queryParam(
                            PARAM_NAME_EMAIL_USER,
                            addressRequest.getEmailUser() )
                    .request( MediaType.APPLICATION_JSON )
                    .get( AddressResponse.class );
        } catch( ResponseProcessingException rpe ) {
            // ¯\_(ツ)_/¯
            if( FunctionType.FORGET_ME.equals( addressRequest.getFunctionType() ) ) {
                return address(
                        AddressRequest
                                .remember( addressRequest.getSessionId() ) );
            }
            return null;
        } catch( Exception e ) {
            log.error( "email address request failed: ", e );
            throw e;
        }
    }

    @Override
    public EmailResponse email( EmailRequest emailRequest ) {

        Validate.notNull( emailRequest, "'emailRequest' cannot be null" );

        log.debug( "making email request: {}", emailRequest );

        try {
            return apiTarget
                    .queryParam(
                            PARAM_NAME_FUNCTION,
                            emailRequest.getFunctionType().getFunction())
                    .queryParam(
                            PARAM_NAME_SESSION_ID,
                            emailRequest.getSessionId())
                    .queryParam(
                            PARAM_NAME_EMAIL_ID,
                            emailRequest.getId())
                    .request( MediaType.APPLICATION_JSON )
                    .get( EmailResponse.class );
        } catch( ResponseProcessingException rpe ) {
            return null;
        } catch( Exception e ) {
            log.error( "email request failed: ", e );
            throw e;
        }
    }

    @Override
    public EmailsResponse emails( EmailsRequest emailsRequest ) {

        Validate.notNull( emailsRequest, "'emailsRequest' cannot be null" );

        log.debug( "making emails request: {}", emailsRequest );

        try{
            return apiTarget
                    .queryParam(
                            PARAM_NAME_FUNCTION,
                            emailsRequest.getFunctionType().getFunction() )
                    .queryParam(
                            PARAM_NAME_SESSION_ID,
                            emailsRequest.getSessionId() )
                    .queryParam(
                            PARAM_NAME_SEQUENCE_ID,
                            emailsRequest.getSequenceId() )
                    .queryParam(
                            PARAM_NAME_OFFSET,
                            emailsRequest.getOffset() )
                    .request( MediaType.APPLICATION_JSON )
                    .get( EmailsResponse.class );
        } catch( ResponseProcessingException rpe ) {
            return null;
        } catch( Exception e ) {
            log.error( "emails request failed: ", e );
            throw e;
        }
    }
}
