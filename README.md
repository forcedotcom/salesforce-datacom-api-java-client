# Salesforce Data.com Java REST API
================================

Think the REST API is awesome and powerful, but maybe a bit overwhelming? This native Java implementation aims to keep the implementation simple
while keeping all the power at your fingertips.
 
# Overview
This Java REST API is a simple library to interact with the REST API.

# Usage
Simply clone the salesforce-datacom-api-java-client project and incorporate it into your project.

Compiling the source is easy using maven:

    maven clean install

The jar file can be found at target/salesforce-datacom-api-java-client-xx.xx.jar.

# Features
- Contact get
- Contact purchase

- Contact search (in progress)

# Configuration and authentication
The Java REST API supports only one forms of authentication.

## Username and password
If your org allows it (grant_type=password), you can use the client ID, client Secret and your username and password to authenticate.
This is discouraged and the ClientSecret authentication is preferred over this one.

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

## Contact get
A very simple example:
```java
ServiceFactory factory = new ServiceFactoryImpl(config);

ContactService service = factory.createContactService();
List<Contact> contacts = service.get(Arrays.asList(17892515L));
```
or
```java
ContactService service = new ContactServiceImpl(config);
List<Contact> contacts = service.get(Arrays.asList(17892515L));
```
This contains almost all information you need.

## Contact purchase
A very simple example:
```java
ServiceFactory factory = new ServiceFactoryImpl(config);

ContactService service = factory.createContactService();
List<Contact> contacts = service.purchase(Arrays.asList(17892515L));
```
or
```java
ContactService service = new ContactServiceImpl(config);
List<Contact> contacts = service.purchase(Arrays.asList(17892515L));
```
This contains almost all information you need.


# License
The BSD 2-Clause License

http://opensource.org/licenses/BSD-2-Clause

See [LICENSE.txt](./LICENSE.txt)


[![githalytics.com alpha](https://cruel-carlota.pagodabox.com/6ca79e72bf275bb790cfda419b72675b "githalytics.com")](http://githalytics.com/forcedotcom/JavaChatterRESTApi)
