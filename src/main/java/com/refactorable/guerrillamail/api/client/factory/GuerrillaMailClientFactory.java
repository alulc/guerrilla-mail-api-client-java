package com.refactorable.guerrillamail.api.client.factory;

import com.refactorable.guerrillamail.api.client.GuerrillaMailClient;
import com.refactorable.guerrillamail.api.client.GuerrillaMailClientImpl;
import org.apache.commons.lang3.Validate;

import javax.ws.rs.client.WebTarget;

public class GuerrillaMailClientFactory {

    private static final String API_PATH = "ajax.php";

    /**
     *
     * @param baseApiTarget cannot be null
     * @return
     */
    public static GuerrillaMailClient defaultClient( WebTarget baseApiTarget ) {

        Validate.notNull( baseApiTarget, "'baseApiTarget' cannot be null" );

        return client(
                baseApiTarget,
                API_PATH );
    }

    /**
     *
     * @param baseApiTarget cannot be null
     * @param apiPath cannot be null
     * @return
     */
    public static GuerrillaMailClient client(
            WebTarget baseApiTarget,
            String apiPath ) {

        Validate.notNull( baseApiTarget, "'baseApiTarget' cannot be null" );
        Validate.notBlank( apiPath, "'apiPath' cannot be blank" );

        return new GuerrillaMailClientImpl(
                baseApiTarget,
                apiPath );
    }
}
