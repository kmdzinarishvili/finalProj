package ge.edu.btu.imdb.data.network

import ge.edu.btu.imdb.constants.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor implementation for adding an authentication query parameter to outgoing requests.
 */
class AuthInterceptor : Interceptor {
    /**
     * Intercepts the request and adds the authentication query parameter before proceeding.
     *
     * @param chain The interceptor chain.
     * @return The response from the intercepted request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the original request
        val original = chain.request()
        // Extract the original URL
        val originalHttpUrl: HttpUrl = original.url
        // Build the new URL with added authentication query parameter
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("api_key", Constants.API_KEY)
            .build()
        // Build a new request with the modified URL
        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        // Proceed with the new request and return the response
        return chain.proceed(request)
    }
}