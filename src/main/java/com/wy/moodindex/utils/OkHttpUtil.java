package com.wy.moodindex.utils;

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
//
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
 //                   .allEnabledTlsVersions()
 //                   .supportsTlsExtensions(false)
 //                   .allEnabledCipherSuites()
                    .build();

            httpsClient = new OkHttpClient.Builder()
                    .connectionSpecs(Collections.singletonList(spec))
                    .connectTimeout(6000, TimeUnit.MILLISECONDS)
                    .readTimeout(6000,TimeUnit.MILLISECONDS)
                    .build();
        }
        return httpsClient;
    }
}
