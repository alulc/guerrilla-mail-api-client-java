package com.refactorable.guerrillamail.api.client;

import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailsRequest;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailsResponse;

public interface GuerrillaMailClient {

    /**
     *
     * @param addressRequest cannot be null
     * @return
     */
    AddressResponse address( AddressRequest addressRequest );

    /**
     *
     * @param emailRequest cannot be null
     * @return
     */
    EmailResponse email( EmailRequest emailRequest );

    /**
     *
     * @param emailsRequest cannot be null
     * @return
     */
    EmailsResponse emails( EmailsRequest emailsRequest );
}
