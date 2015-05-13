# RingCentral API calls using Java

Java Sample implementation for RingCentral API
# Java-Sample-for-Ringcentral-API

Replace ` < ********* >` with the AccessTokens, UserID and Password. 

APIkey and APIsecret should be converted into BASE64 encoded form and replaced in  this piece of code

Program.java
//---------------------------------
httpConn.setRequestProperty(
  "Authorization",
  "Basic <base64 converted apikey and secret>");
//---------------------------------
