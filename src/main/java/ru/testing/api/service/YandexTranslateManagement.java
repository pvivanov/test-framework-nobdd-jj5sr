package ru.testing.api.service;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.testing.api.Service;
import ru.testing.api.entities.translate.TranslateResponse;

import static ru.testing.api.ServiceType.MICROSERVICE;

@Service(type = MICROSERVICE, path = "api/v1.5/tr.json/")
public interface YandexTranslateManagement {

    @POST ("translate")
    Call<TranslateResponse> getTranslateText(@Query("key") String key,
                                             @Query("text") String textToTranslate,
                                             @Query("lang") String lang,
                                             @Query("format") String format,
                                             @Query("options") String options);
}