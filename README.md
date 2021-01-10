# Linius Proxy Server
A proxy server that forwards select requests to the Linius Technologies API, and returns the response. 

## Usage

1. Download the repo
2. Create a `api-keys.properties` file in the directory `src/main/resources`. 
This file should contain the following - 

```
linius.x-api-key = your Linius API key
linius.userName = your Linius username
linius.password = your Linius password
```

This file should not be checked into version control for security reasons. 

## TODO

- Integrate with AWS. 
- Implement logging functionality. 
- Further testing of corner / extreme cases
- Performance benchmarking
- Enforce HTTPS
- Implement auto-refreshing of Linius access token
