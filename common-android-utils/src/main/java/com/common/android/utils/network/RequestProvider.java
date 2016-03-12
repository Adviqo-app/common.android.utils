package com.common.android.utils.network;

import com.orhanobut.wasp.BuildConfig;
import com.orhanobut.wasp.Wasp;
import com.orhanobut.wasp.utils.LogLevel;
import com.orhanobut.wasp.utils.NetworkMode;

import java.net.CookiePolicy;

import static com.common.android.utils.ContextHelper.getContext;

/**
 * Created by Nyaruhodo on 06.03.2016.
 */
public class RequestProvider {

    private static final boolean ENABLE_MOCKING = false;

    public static boolean LOGGING_ENABLED = BuildConfig.DEBUG;

    public static boolean isLoggingEnabled() {
        return LOGGING_ENABLED;
    }

    public static void setLoggingEnabled(final boolean loggingEnabled) {
        LOGGING_ENABLED = loggingEnabled;
    }

    /**
     * http://freegeoip.net/json/{$ip} //10k requests per hour
     */
    public static FreeGeoIpService freeGeoIpService() {
        return new Wasp.Builder(getContext())
                .setEndpoint("https://freegeoip.net")
                .setNetworkMode(ENABLE_MOCKING
                        ? NetworkMode.MOCK
                        : NetworkMode.LIVE)
                .setLogLevel(LOGGING_ENABLED
                        ? LogLevel.FULL
                        : LogLevel.NONE)
                .trustCertificates()
                .enableCookies(CookiePolicy.ACCEPT_ALL)
                .build()
                .create(FreeGeoIpService.class);
    }

    /**
     * http://ipinfo.io/{$ip}/json //1k per day
     */
    public static IpInfoIoService ipInfoIoService() {
        return new Wasp.Builder(getContext())
                .setEndpoint("http://ipinfo.io")
                .setNetworkMode(ENABLE_MOCKING
                        ? NetworkMode.MOCK
                        : NetworkMode.LIVE)
                .setLogLevel(BuildConfig.DEBUG
                        ? LogLevel.FULL
                        : LogLevel.NONE)
                .trustCertificates()
                .enableCookies(CookiePolicy.ACCEPT_ALL)
                .build()
                .create(IpInfoIoService.class);
    }

    /**
     * http://ip-api.com/json/{$ip}?fields=country,city,regionName,status //150 per minute
     */
    public static IpApiComService ipApiComService() {
        return new Wasp.Builder(getContext())
                .setEndpoint("http://ip-api.com")
                .setNetworkMode(ENABLE_MOCKING
                        ? NetworkMode.MOCK
                        : NetworkMode.LIVE)
                .setLogLevel(BuildConfig.DEBUG
                        ? LogLevel.FULL
                        : LogLevel.NONE)
                .trustCertificates()
                .enableCookies(CookiePolicy.ACCEPT_ALL)
                .build()
                .create(IpApiComService.class);
    }
}