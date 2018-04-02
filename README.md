<a href="https://jitpack.io/#refactorable/guerrilla-mail-api/v1.0.1"><img src="https://jitpack.io/v/refactorable/guerrilla-mail-api.svg" /></a>
<a href="https://jitpack.io/#refactorable/guerrilla-mail-api"><img src="https://jitpack.io/v/refactorable/guerrilla-mail-api/month.svg" /></a>
<a href="https://travis-ci.org/refactorable/guerrilla-mail-api/builds"><img src="https://travis-ci.org/refactorable/guerrilla-mail-api.svg?branch=master" /></a>
<a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" /></a>

# guerrilla-mail-api
This is a simple java client for the guerrilla mail api.

## Requirements 
Java 1.8 or later.

## Installation

### maven
```xml
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```
```xml
<dependency>
    <groupId>com.github.refactorable</groupId>
    <artifactId>guerrilla-mail-api</artifactId>
    <version>v1.0.1</version>
</dependency>
```

### gradle

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
```groovy
dependencies {
  compile 'com.github.refactorable:guerrilla-mail-api:v1.0.1'
}
```

## Usage
I recommend looking at the integration tests to get a full understanding of what you can do, but below is just one example.
```java
import com.refactorable.guerrillamail.api.client.GuerrillaMailClient;
import com.refactorable.guerrillamail.api.client.factory.GuerrillaMailClientFactory;
import com.refactorable.guerrillamail.api.client.model.request.AddressRequest;
import com.refactorable.guerrillamail.api.client.model.request.EmailsRequest;
import com.refactorable.guerrillamail.api.client.model.response.AddressResponse;
import com.refactorable.guerrillamail.api.client.model.response.EmailsResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class GuerrillaMailClientExample {

    public static void main( String[] args ) {

        // create client
        Client client = ClientBuilder.newClient();
        WebTarget apiTarget = client.target( "http://api.guerrillamail.com" );
        GuerrillaMailClient guerrillaMailClient = GuerrillaMailClientFactory.defaultClient( apiTarget );

        // create address
        AddressResponse initializedAddressResponse = guerrillaMailClient.address( AddressRequest.initialize() );

        // check for emails
        String sessionId = initializedAddressResponse.getSessionId();
        Long sequenceId = 0L;
        EmailsResponse emailsResponse = guerrillaMailClient.emails( EmailsRequest.check( sessionId, sequenceId ) );

        System.out.print( emailsResponse.getEmails() );
    }
}
```
## Testing

You must have Gradle installed in order to run the following:

    ./gradlew test
