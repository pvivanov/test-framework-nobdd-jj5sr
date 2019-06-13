package ru.testing.api;

import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.testing.api.exceptions.ServicePathException;
import ru.testing.api.interceptors.BasicHeadersInterceptor;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ServiceBuilder {

    private List<Interceptor> interceptors = new ArrayList<>();

    @SneakyThrows
    public <T> T build(Class<T> clazz) {
        Service service = clazz.isAnnotationPresent(Service.class) ? clazz.getAnnotation(Service.class) : null;

        if (null == service && !isServicePathEndsWithSlash(service.type().toString())) {
            throw new ServicePathException();
        }

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        for (Interceptor interceptor : this.interceptors) {
            okHttpClientBuilder.addInterceptor(interceptor);
        }

        return getRetrofit(service, okHttpClientBuilder).create(clazz);
    }

    private Retrofit getRetrofit(Service service, OkHttpClient.Builder okHttpClientBuilder) {
        return new Retrofit.Builder()
                .baseUrl(service.type().getBaseUrl() + service.path())
                .client(okHttpClientBuilder
                        .addInterceptor(new HttpLoggingInterceptor(log::info)
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .addInterceptor(new BasicHeadersInterceptor())
                        .followRedirects(false)
                        .build())
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .setLenient()
                                .create()))
                .build();
    }

    private static boolean isServicePathEndsWithSlash(String servicePath) {
        return servicePath.endsWith("/");
    }
}