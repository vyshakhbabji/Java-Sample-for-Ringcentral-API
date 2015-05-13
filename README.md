# Java Sample for Ringcentral API

RingCentral API calls using Java

# Usage

In the `Program.java` file, replace ` < ********* >` with your `AccessToken`, `UserID` and `Password`. 

`APIkey` and `APIsecret` should be converted into BASE64 encoded form and replaced in  this piece of code

```java
//---------------------------------
httpConn.setRequestProperty(
  "Authorization",
  "Basic <base64 converted apikey and secret>");
//---------------------------------
```
