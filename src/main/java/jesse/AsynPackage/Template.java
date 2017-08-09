package jesse.AsynPackage;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Map;

/**
 * Created by jesse on 2017/8/9.
 */
public interface Template {
    <T>ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, Class<T> responseType) throws Exception;
    <T>ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, ParameterizedTypeReference<T> responseType) throws Exception;
    <T>ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, Class<T> responseType, Map<String, ?> params) throws Exception;
    <T>ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, ParameterizedTypeReference<T> responseType, Map<String, ?> params) throws Exception;

}
