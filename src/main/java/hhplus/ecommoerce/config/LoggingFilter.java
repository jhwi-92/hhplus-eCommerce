package hhplus.ecommoerce.config;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long startTime = System.currentTimeMillis();

        // 요청 로깅
        logRequest(requestWrapper);

        filterChain.doFilter(requestWrapper, responseWrapper);

        // 응답 로깅
        logResponse(responseWrapper, System.currentTimeMillis() - startTime);

        responseWrapper.copyBodyToResponse();
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String requestBody = new String(request.getContentAsByteArray());
        log.info("Request: [URL: {}, Method: {}, Content: {}]",
            request.getRequestURI(), request.getMethod(), requestBody);
    }

    private void logResponse(ContentCachingResponseWrapper response, long timeTaken) throws IOException {
        String responseBody = new String(response.getContentAsByteArray());
        log.info("Response: [Status: {}, Content: {}, TimeTaken: {}ms]",
            response.getStatus(), responseBody, timeTaken);
    }
}
