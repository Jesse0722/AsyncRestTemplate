package jesse.AsynPackage;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.Map;

/**
 * Created by jesse on 2017/8/9.
 */
public class AsynClientTemplate extends AbstractTemplate {

    public  AsynClientTemplate(AsyncRestTemplate template){
        setTemplate(template);
    }
    @Override
    void setTemplate(AsyncRestTemplate template) {
        asyncRestTemplate = template == null ? new AsyncRestTemplate():template;
    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, Class<T> responseType) throws Exception {
        return super.getAsyncForObject(baseRequest, responseType);
    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, ParameterizedTypeReference<T> responseType) throws Exception {
        return super.getAsyncForObject(baseRequest, responseType);
    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, Class<T> responseType, Map<String, ?> params) throws Exception {
        return super.getAsyncForObject(baseRequest, responseType, params);
    }

    @Override
    public <T> ListenableFuture<ResponseEntity<T>> getAsyncForObject(BaseRequest baseRequest, ParameterizedTypeReference<T> responseType, Map<String, ?> params) throws Exception {
        return super.getAsyncForObject(baseRequest, responseType, params);
    }
}
