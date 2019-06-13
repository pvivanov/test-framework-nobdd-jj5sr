package ru.testing.api.interceptors;


import okhttp3.Interceptor;
import okhttp3.Request;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BasicHeadersInterceptor implements Interceptor {

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Accept", "*/*");

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}