package com.refactorable.guerrillamail.api.client;

import com.refactorable.guerrillamail.api.client.factory.GuerrillaMailClientFactory;
import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailsRequest;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailsResponse;
import org.glassfish.jersey.logging.LoggingFeature;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class GuerrillaMailClientIntegrationTest {

    private static final Logger logger = Logger.getLogger(
            GuerrillaMailClientIntegrationTest.class.getName() );

    private static GuerrillaMailClient guerrillaMailClient;

    @BeforeClass
    public static void init() {

        Client client = ClientBuilder.newClient();
        client.register( new LoggingFeature( logger, Level.INFO, null, null ) );
        WebTarget apiTarget = client.target( "http://api.guerrillamail.com" );
        guerrillaMailClient = GuerrillaMailClientFactory.defaultClient( apiTarget );
    }

    @Test
    public void ShouldInitializeEmailAddress() {

        AddressResponse addressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        assertTrue( addressResponse.getAddress() != null );
    }

    @Test
    public void ShouldRememberEmailAddress() {

        AddressResponse initializedAddressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        String sessionId = initializedAddressResponse.getSessionId();
        AddressResponse addressResponse =
                guerrillaMailClient
                        .address( AddressRequest.remember( sessionId ) );

        assertTrue(
                addressResponse
                        .getAddress()
                        .equals( initializedAddressResponse.getAddress() ) );
    }

    @Test
    public void ShouldForgetEmailAddress() {

        AddressResponse initializedAddressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        String sessionId = initializedAddressResponse.getSessionId();
        AddressResponse addressResponse =
                guerrillaMailClient
                        .address( AddressRequest.forget( sessionId ) );

        assertTrue(
                !addressResponse
                        .getAddress()
                        .equals( initializedAddressResponse.getAddress() ) );
    }

    @Test
    public void ShouldCustomizeEmailAddress() {

        AddressResponse initializedAddressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        String sessionId = initializedAddressResponse.getSessionId();
        String emailUser = "test";
        AddressResponse addressResponse =
                guerrillaMailClient
                        .address( AddressRequest.custom(
                                sessionId,
                                emailUser ) );

        assertTrue( addressResponse.getAddress() != null );
        assertTrue( addressResponse.getAddress().contains( emailUser ) );
    }

    @Test
    public void ShouldCheckEmails() {

        AddressResponse initializedAddressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        String sessionId = initializedAddressResponse.getSessionId();
        Long sequenceId = 0L;
        EmailsResponse emailsResponse =
                guerrillaMailClient
                        .emails( EmailsRequest.check(
                                sessionId,
                                sequenceId ) );

        assertTrue( !emailsResponse.getEmails().isEmpty() );
    }

    @Test
    public void ShouldGetEmails() {

        AddressResponse initializedAddressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        String sessionId = initializedAddressResponse.getSessionId();
        Long sequenceId = 0L;
        Integer offset = 0;
        EmailsResponse emailsResponse =
                guerrillaMailClient
                        .emails( EmailsRequest.emails(
                                sessionId,
                                sequenceId,
                                offset ) );

        assertTrue( !emailsResponse.getEmails().isEmpty() );
    }

    @Test
    public void ShouldGetEmail() {

        AddressResponse initializedAddressResponse =
                guerrillaMailClient
                        .address( AddressRequest.initialize() );

        String sessionId = initializedAddressResponse.getSessionId();
        Long sequenceId = 0L;
        EmailsResponse emailsResponse =
                guerrillaMailClient
                        .emails( EmailsRequest.check(
                                sessionId,
                                sequenceId ) );

        Long emailId = emailsResponse.getEmails().get( 0 ).getId();
        EmailResponse emailResponse =
                guerrillaMailClient.email(
                        new EmailRequest(
                                sessionId,
                                emailId ) );

        assertTrue( emailResponse.getBody() != null );
    }
}