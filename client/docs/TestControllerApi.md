# TestControllerApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**test**](TestControllerApi.md#test) | **GET** / | 



## test

> String test()



### Example

```java
// Import classes:
import com.gametrader.api.invoker.ApiClient;
import com.gametrader.api.invoker.ApiException;
import com.gametrader.api.invoker.Configuration;
import com.gametrader.api.invoker.models.*;
import com.gametrader.api.TestControllerApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("http://localhost:8080");

        TestControllerApi apiInstance = new TestControllerApi(defaultClient);
        try {
            String result = apiInstance.test();
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling TestControllerApi#test");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

