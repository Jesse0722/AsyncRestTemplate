package jesse.AsynPackage;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jesse on 2017/8/9.
 */
public class CommonListenableCallBack<T> implements ListenableFutureCallback<T> {

    private IEnum type;
    private Map<IEnum,Object> resultValue;
    private volatile CountDownLatch latch;

    public CommonListenableCallBack(IEnum type, Map<IEnum, Object> resultValue, CountDownLatch latch) {
        this.type = type;
        this.resultValue = resultValue;
        this.latch = latch;
    }

    @Override
    public void onFailure(Throwable ex) {
        latch.countDown();
    }

    @Override
    public void onSuccess(T result) {
        ResponseEntity<T> responseEntity = (ResponseEntity<T>) result;
        if(responseEntity!=null && responseEntity.getBody()!=null){
            T body = responseEntity.getBody();
            if(type!=null){
                resultValue.put(type,body);
            }
        }
        latch.countDown();
    }
}
