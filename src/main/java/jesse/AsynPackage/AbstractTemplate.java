package jesse.AsynPackage;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Map;

/**
 * Created by jesse on 2017/8/9.
 */
public abstract class AbstractTemplate implements Template {

    public AsyncRestTemplate asyncRestTemplate;

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, Class<T> responseType) throws Exception {
        String url = baseRequest.build();
        try {
            ListenableFuture<ResponseEntity<T>> t = asyncRestTemplate.getForEntity(url, responseType);
            return t;
        }catch (Exception e){
            throw e;
        }

    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, ParameterizedTypeReference<T> responseType) throws Exception {
        String url = baseRequest.build();
        try {
            ListenableFuture<ResponseEntity<T>> t = asyncRestTemplate.exchange(url, HttpMethod.GET, null, responseType);
            return t;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, Class<T> responseType, Map<String, ?> params) throws Exception {
        String url = baseRequest.build();
        try{
            ListenableFuture<ResponseEntity<T>> t = asyncRestTemplate.exchange(url,HttpMethod.GET,null,responseType,params);
            return t;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, ParameterizedTypeReference<T> responseType, Map<String, ?> params) throws Exception {
        String url = baseRequest.build();
        try{
            ListenableFuture<ResponseEntity<T>> t = asyncRestTemplate.exchange(url,HttpMethod.GET,null,responseType,params);
            return t;
        }catch (Exception e){
            throw e;
        }
    }

    abstract void setTemplate(AsyncRestTemplate asyncRestTemplate);
}
