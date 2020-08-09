package com.power.encrypt.filter;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.netflix.zuul.http.ServletInputStreamWrapper;
import com.power.encrypt.rsa.RsaKeys;
import com.power.encrypt.service.RsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RSARequestFilter extends ZuulFilter {

    @Autowired
    private RsaService rsaService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("过滤器执行了");
        //获取requestContext容器
        RequestContext ctx=RequestContext.getCurrentContext();
        //获得request和response
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        //声明存放加密后数据的变量
        String requestData=null;
        //声明存放解密后数据的变量
        String responseData=null;

        try {
            //通过request获得inputStream
            ServletInputStream inputStream = request.getInputStream();

            //
            requestData=StreamUtils.copyToString(inputStream, Charsets.UTF_8);


            if(!Strings.isNullOrEmpty(requestData)){
                try {
                    System.out.println("加密前的东西:"+requestData);
                    responseData=rsaService.RSADecryptDataPEM(requestData, RsaKeys.getServerPrvKeyPkcs8());
                    System.out.println("解密后的东西"+responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //把解密后的数据转发
                if(!Strings.isNullOrEmpty(responseData)){
                    byte[] bytes = responseData.getBytes();
                    ctx.setRequest(new HttpServletRequestWrapper(request) {
                        @Override
                        public ServletInputStream getInputStream() throws IOException {
                            return new ServletInputStreamWrapper(bytes);
                        }


                        @Override
                        public int getContentLength() {
                            return bytes.length;
                        }

                        @Override
                        public long getContentLengthLong() {
                            return bytes.length;
                        }
                    });
                }
            }
            System.out.println("转发request");
            // 设置request请求头中的Content-Type为application/json，否则api接口模块需要进行url转码操作
            ctx.addZuulRequestHeader("Content-Type", String.valueOf(MediaType.APPLICATION_JSON) + ";charset=UTF-8");
        } catch (IOException e) {
            System.out.println(this.getClass().getName() + "运行出错" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
