package ie.markomeara.irelandtraintimes.testrules;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.espresso.IdlingResource;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.apache.commons.io.IOUtils;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ie.markomeara.irelandtraintimes.data.IrishRailApi;
import ie.markomeara.irelandtraintimes.di.AppComponent;
import ie.markomeara.irelandtraintimes.di.AppModule;
import ie.markomeara.irelandtraintimes.di.DaggerAppComponent;
import ie.markomeara.irelandtraintimes.di.Injector;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by markomeara on 27/04/2017.
 */

public class MockServerTestRule implements TestRule {

    private final MockWebServer server = new MockWebServer();
    private IdlingResource okHttpIdlingResource;

    @Override
    public Statement apply(Statement base, Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                server.start();
                injectMockedApi();
                base.evaluate();
                server.shutdown();
            }
        };
    }

    public IdlingResource getOkHttpIdlingResource() {
        return okHttpIdlingResource;
    }

    public void enqueueSuccess(String responseBody) {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody(responseBody);
        server.enqueue(mockResponse);
    }

    public void enqueueSuccessStationsList(Context context) throws IOException {
        AssetManager am = context.getAssets();
        InputStream is = am.open("stationslist_response.xml");
        String stationListResponse = IOUtils.toString(is, Charset.defaultCharset());
        enqueueSuccess(stationListResponse);
    }

    private void injectMockedApi() {
        AppComponent component = DaggerAppComponent.builder().appModule(new MockApiModule()).build();
        Injector.setComponent(component);
    }

    @Module
    private class MockApiModule extends AppModule {

        @Override
        @Provides
        @Singleton
        public IrishRailApi providesIrishRailApi(OkHttpClient okHttpClient) {

            okHttpIdlingResource = OkHttp3IdlingResource.create("OkHttp", okHttpClient);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(server.url("/").toString())
                    .client(okHttpClient)
                    .addConverterFactory(SimpleXmlConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(IrishRailApi.class);
        }
    }

}