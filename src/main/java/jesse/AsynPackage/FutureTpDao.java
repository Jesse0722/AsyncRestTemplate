package jesse.AsynPackage;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jesse on 2017/8/9.
 */
public class FutureTpDao {
    public AsynClientTemplate asynClientTemplate;

    public FutureTpDao() {
        this.asynClientTemplate = new AsynClientTemplate(null);
    }

    public FutureTpDao(AsyncRestTemplate asyncRestTemplate){
        asynClientTemplate = new AsynClientTemplate(asyncRestTemplate);
    }

    public Map<IEnum,Object> getHttpData(ConcreateWapper wapper){
        if(wapper == null){
            return new HashMap<IEnum, Object>();
        }
        final CountDownLatch latch = new CountDownLatch(wapper.getWrapper().size());
        final Map<IEnum,Object> result = new HashMap<IEnum, Object>();

        if(wapper.getWrapper()!=null){
            for (final ConcreateWapper.Concreate wp : wapper.getWrapper()) {
                try {
                    Map<BaseRequest, ?> requestMap = wp.getRequest();
                    for (final BaseRequest tpRequestInfo : requestMap.keySet()) {
                        getHttpdata(wp, tpRequestInfo, latch, requestMap, result);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                latch.await();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }

    //发送http请求，获取请求结果
    private void getHttpdata(ConcreateWapper.Concreate wp, BaseRequest tpRequestInfo, CountDownLatch latch,
                             Map<BaseRequest, ?> requestMap, Map<IEnum, Object> result) throws Exception {
        ListenableFuture<?> statResponse = null;

        if (requestMap.get(tpRequestInfo) instanceof ParameterizedTypeReference<?>) {
            ParameterizedTypeReference<?> responseType = (ParameterizedTypeReference<?>) requestMap.get(tpRequestInfo);
            statResponse = asynClientTemplate.getAsyncForObject(tpRequestInfo, responseType, wp.getVariables());
        } else if (requestMap.get(tpRequestInfo) instanceof Class<?>) {
            Class<?> responseType = (Class<?>) requestMap.get(tpRequestInfo);
            statResponse = asynClientTemplate.getAsyncForObject(tpRequestInfo, responseType);
        } else {
            throw new RuntimeException("requestType error...");
        }

        addCallBack(statResponse, wp.getBaseEnum(), latch, result);
    }

    //增加回调
    private <T> void addCallBack(ListenableFuture<T> statResponse, IEnum baseEnum, CountDownLatch latch,
                                 Map<IEnum, Object> result) {
        if (statResponse != null) {
            statResponse.addCallback(new CommonListenableCallBack<T>(baseEnum, result, latch));
        }
    }
}
