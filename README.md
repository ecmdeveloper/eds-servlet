# EDS servlet
This is a library you can use to build an External Data Services servlet for IBM FileNet
Case Manager and IBM FileNet Content Navigator.

## Getting started

If you want to use the library,
you have to create a JEE project to contain the servlet handling the request. If you add
support for Maven to your project then the simplest way to start is to add the depenency
to the EDS servlet API to your pom-file:

```xml
<dependency>
    <groupId>com.ecmdeveloper</groupId>
    <artifactId>eds-servlet</artifactId>
    <version>1.0.0</version>
</dependency>
```

If this is not possible then you have to add the jar-file and it's depenencies manually
to your project. The files can be found here:

TODO: add location of jar-file and depenencies.

The next step is to add a servlet to your project. This servlet must be a subclass
of the class ```com.ecmdeveloper.eds.servlet.AbstractEDSServlet``` and should include
support for the URL patterns ```/type/*``` and ```/types```. A minimal servlet implementation
looks like this:

```java
@WebServlet(
		description = "An example of an EDS servlet.",
		urlPatterns = { "/type/*", "/types"
		})
public class EDSExampleServlet extends AbstractEDSServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {

		  // TODO: add your implementation code here...
	}
}
```

The ```handleRequest()``` method is where all the magic happens. This is where the actual implementation
of your EDS service should take place. The incoming parameter ```dataRequest``` contains information
about the request that is made, and the `dataResponse` object should be filled up by the implementation, describing the behavior of the different properties. You can use the ```getProperty()``` with the symbolic name of the property to get a `Property` object. This object contains several methods to describe the required behavior.

## A simple example

Let's start with a simple IBM Case Manager use case. A solution has a case type ```Customer``` containing the property `EDSX_Name`. We want to make this property editable and
required with a default value when we create a new case and readonly after the case is created.

> (This could also be done making the property "Only settable on create", but for the
> sake of the demo, we will try it the EDS way.)

To accomplish this we can use the following code:
```java
if (dataRequest.getObjectType().equals("Customer") ) {
	Property name = dataRequest.getProperty("EDSX_Name");
	if ( dataRequest.getRequestMode().equals(RequestMode.initialNewObject) ) {
		name.setRequired(true);
		name.setValue("Jane/John Doe");
		name.setDisplayMode(DisplayMode.readwrite);
	} else if (dataRequest.equals(RequestMode.initialExistingObject) ) {
		name.setDisplayMode(DisplayMode.readonly);
	}

	dataResponse.addProperty(name);
}
```
Because requests to EDS are made for different object types of the solution, you
first have to check that you are dealing with the `Customer` case type. Next the
property object is fetched from the dataRequest object. The `RequestMode` gives you
an indication of the context of the call that is being made. In the first part
the user started creating a new case, resulting in a `RequestMode` value of `initialNewObject`.
In the second block of the if-statement, the case is already created, resulting
in a `RequestMode` value of `initialExistingObject`.

Now that the context is determined, we can start to modify the `Property` object, making
it required and editable on create and readonly afterwards. At
the end there is one important thing: the property must be added to the dataResponse
object. This is all it takes to build a simple EDS implementation.

See the complete class for this example  [here](eds-servlet/eds-servlet-example/src/main/java/com/ecmdeveloper/eds/example/servlet/EDSExampleServlet.java)

## A simple Content Navigator examples

The Content Navigator service is a little bit more involved. In this case you have to
provide an implementation for the `getObjectTypeNames()` method. This method has the
following signature:
```java
public String[] getObjectTypeNames(String repositoryId)
```
The implementation of this method should return a string array containing all the object types
you want to be handled by the service. For folders and documents this are simply
the symbolic names of the classes. But the service can also be used for stepprocessors and
search template. Check the documentation what the object type names are in this case.

In this example we will add validation to the `Email` class, so the implementation looks
like this, returning an array with only one element:

```java
@Override
public String[] getObjectTypeNames(String repositoryId) {
	return new String[] {"Email"};
}
```
In this example we check if the `From` property contains a valid e-mail address. The
Internet has a lot of regular expressions to check this. In this case we choose the
version on the [www.w3resource.com](http://www.w3resource.com/javascript/form/email-validation.php) site. This leads to the following implementation of the `handleRequest()` method (for simplicity
some checks are skipped):

```java
@Override
public void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {
	Property from = dataRequest.getProperty("From");
	from.setValue("john.doe@example.com");
	from.setFormat("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
	from.setFormatDescription("e-mail address");
	dataResponse.addProperty(from);
}
```

## Reference

### ExternalDataRequest

Method | Description
-------|------------
`String getObjectId()`|The globally unique identifier (GUID) or persistent identifier (PID) that identifies the item that is being edited.
`String getObjectId()`|The object type of the item that is being edited.
`String getRepositoryId()`|The name of the target external data store that contains the property data.
`RequestMode getRequestMode()` | Returns the  reason that the service is being called. The value can be one of these value: `initialNewObject`, `initialExistingObject`, `inProgressChanges`,`finalNewObject` or `finalExistingObject`
` Map<String, Object> getClientContext()`|An array that contains a series of key value pairs that specify contextual information for a specific class or item type.
`Property getProperty(String name)`|Returns a specific property identified by it's symbolic name
`String getExternalDataIdentifier()`|A string that indicates the state of the data that was returned by the external data service. The request must include this identifier if the `requestMode` parameter is set to one of these values: `inProgressChanges`, `finalNewObject` or `finalExistingObject`
