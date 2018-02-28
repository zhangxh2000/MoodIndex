package com.wy.moodindex.model.source;

import okhttp3.*;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private static OkHttpClient httpsClient;
    public static OkHttpClient getHttpsClient() {
        if (httpsClient == null) {
            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                    .build();

            httpsClient = new OkHttpClient.Builder()
                    .connectionSpecs(Collections.singletonList(spec))
                    .connectTimeout(2000, TimeUnit.MILLISECONDS)
                    .readTimeout(3000,TimeUnit.MILLISECONDS)
                    .build();
        }
        return httpsClient;
    }
}
