# Salesforce Data.com Java REST API
================================

Think the REST API is awesome and powerful, but maybe a bit overwhelming? This native Java implementation aims to keep the implementation simple
while keeping all the power at your fingertips.
 
# Overview
This Java REST API is a simple library using POJO's to interact with the REST API.

# Usage
Simply download the java/src and required libraries and incorporate it into your project.

Compiling the source is easy using gradle:

    gradle assemble

Or to run the unittests as well

    gradle build

The jar file can be found at build/lib/JavaChatterRESTApi.jar.

# Features
- Creating messages, including text, links, @mentions and #tags
- Posting your status (on your own wall)
- Posting to somebody else's wall
- Posting on a group
- Replying to a thread

- Searching for users
- Executing "raw" SOQL queries in case you need some advanced usage*

* The SOQL queries are executed through the REST API

# Configuration and authentication
The Java REST API supports various forms of authentication.
All of them require a form of the IChatterData information to be fed into them, but different methods require different parts of this object to be filled in. 

## ClientSecret authentication

Also see http://www.salesforce.com/us/developer/docs/api_streaming/Content/code_sample_auth_oauth.htm for how this works.

## RefreshToken authentication
If you already possess a Refresh token (from a previous client-secret authentication perhaps) you can use this authentication method.

## Username and password
If your org allows it (grant_type=password), you can use the client ID, client Secret and your username and password to authenticate.
This is discouraged and the ClientSecret authentication is preferred over this one.

## Interactive authentication
This requests information from the user to be returned via the console at runtime. Useful for token-less environments.
It does requires a minimum of a Client Key and Client Callback to be configured.

## Example of a configuration file/class to be used with Password authentication
```java
private static final Map<String, String> config = new HashMap<String, String>(){
    {
        put("grant_type", "password");
        put("username", "myApiUser@myorganisation.com");
        put("password", "myPassword");
        put("client_id", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        put("x-ddc-client-id", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        
        put("server_url", "http://app17.qe1.jigsaw.com:8080/api_rest");
    }
};
```

# Examples
You can find loads of practical examples in the test/integration/src/ folder under the com.salesforce.package.
For example: [TestPost.java](test/integration/src/com/data/api/TestPost.java)

A very simple example:
```java
ServiceFactory factory = new ServiceFactoryImpl(config);

ContactService service = factory.createContactService();

List<com.data.api.connect.client.contact.Contact> contacts = service.get(Arrays.asList(17892515L));
```
or
```java
ContactService service = new ContactServiceImpl(config);
List<com.data.api.connect.client.contact.Contact> contacts = service.get(Arrays.asList(17892515L));
```
This contains almost all information you need.
You create a simple Message object which holds whatever it is you would like to post (and which supports text, links and @mentions).
The ChatterCommand decides where this message should go (to your own or somebody's wall, a particular group or as a response to somebody's thread).
The ChatterData holds all the authentication information while the ChatterService takes care of making the magic happen

# License
The BSD 2-Clause License

http://opensource.org/licenses/BSD-2-Clause

See [LICENSE.txt](./LICENSE.txt)


[![githalytics.com alpha](https://cruel-carlota.pagodabox.com/6ca79e72bf275bb790cfda419b72675b "githalytics.com")](http://githalytics.com/forcedotcom/JavaChatterRESTApi)
