package ie.markomeara.irelandtraintimes.di;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ie.markomeara.irelandtraintimes.data.IrishRailApi;
import ie.markomeara.irelandtraintimes.data.IrishRailService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by markomeara on 19/04/2017.
 */

@Module
public class AppModule {

    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClick() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClientBuilder
                .addInterceptor(logging)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS);

        return okHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    public IrishRailApi providesIrishRailApi(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://api.irishrail.ie/realtime/realtime.asmx/")
                .build();

        return retrofit.create(IrishRailApi.class);
    }

    @Singleton
    @Provides
    public IrishRailService providesIrishRailService(IrishRailApi irishRailApi) {
        return new IrishRailService(irishRailApi);
    }

}
