package org.project.http;

public class HttpRequest extends HttpMessage{

    private HttpMethod method;
    private String requestTarget;
    private String originalHttpVersion;
    private HttpVersion bestCompatibleHttpVersion;

    HttpRequest() {
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    void setMethod(String methodName) throws HttpParsingException {
        for (HttpMethod method: HttpMethod.values()){
            if(methodName.equals(method.name())){
                this.method = method;
                return;
            }
        }
       throw new HttpParsingException(
               HttpStatusCode.SERVER_ERROR_588_NOT_IMPLEMENTED
       );
    }

     void setRequestTarget(String requestTarget) throws HttpParsingException {
        if(requestTarget == null || requestTarget.length() == 0) {
            throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_588_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = requestTarget;
    }

    void setHttpVersion(String originalhttpVersion) throws BadHttpVersionException, HttpParsingException {
        this.originalHttpVersion = originalhttpVersion;
        this.bestCompatibleHttpVersion = HttpVersion.getBestCompatibleVersion(originalhttpVersion);
        if (this.bestCompatibleHttpVersion == null) {
            throw new HttpParsingException(
                    HttpStatusCode.SERVER_ERROR_588_HTTP_VERSION_NOT_SUPPORTED
            );
        }
    }
}
