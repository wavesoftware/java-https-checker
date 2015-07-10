/*
 * Copyright 2015 Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.wavesoftware.utils.https.checker.logic;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLException;
import pl.wavesoftware.utils.https.checker.cli.Application;
import pl.wavesoftware.utils.https.checker.cli.Cli.Arguments;
import pl.wavesoftware.utils.https.checker.cli.Cli.Retcodes;
import pl.wavesoftware.utils.https.checker.cli.ProcessIO;
import pl.wavesoftware.utils.https.checker.cli.Result;
import pl.wavesoftware.utils.https.checker.logic.UrlFetcher.HttpResponse;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public abstract class MainApplication implements Application {

    private IO io;

    @Override
    public Result perform(ProcessIO pio) {
        Boolean quiet = getParams().get(Arguments.QUIET);
        io = new IO(pio, quiet);
        String address = getParams().get(Arguments.ADDRESS);
        io.out("Request URL: " + address);
        Result result;
        HttpResponse response;
        try {
            response = get(address);
            io.out("------");
            io.out("Response OK?: " + response.isSuccessful());
            io.out("------");
            printHeaders(io, response);
            result = Result.result(Retcodes.OK);

        } catch (MalformedURLException ex) {
            io.exception(ex);
            result = Result.result(Retcodes.INVALID_ARGS);
        } catch (SSLException ex) {
            io.exception(ex);
            result = Result.result(Retcodes.BAD_SSL);
        } catch (IOException ex) {
            io.exception(ex);
            result = Result.result(Retcodes.NO_CONNECTION);
        }
        return result;
    }

    private HttpResponse get(String address) throws MalformedURLException, IOException {
        Short redirects = getParams().get(Arguments.MAX_REDIRECTS);
        return get(address, redirects);
    }

    private HttpResponse get(String address, short redirects) throws MalformedURLException, IOException {
        URL url = new URL(address);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(15000);
        conn.setDoInput(true);
        conn.connect();
        Map<String, List<String>> headers = conn.getHeaderFields();
        long length = conn.getContentLengthLong();
        InputStream body = conn.getInputStream();
        return new HttpResponseImpl(headers, length, body);
    }

    private void printHeaders(IO io, HttpResponse response) {
        for (Map.Entry<String, List<String>> entrySet : response.getHeaders().entrySet()) {
            String headerValue = Joiner.on("").join(entrySet.getValue());
            if (entrySet.getKey() == null) {
                io.out(headerValue);
            } else {
                io.out(entrySet.getKey() + ": " + headerValue);
            }
        }
    }

    private static class HttpResponseImpl implements HttpResponse {

        private final Map<String, List<String>> headers;

        private final Long contentLenght;

        private final InputStream body;

        public HttpResponseImpl(final Map<String, List<String>> headers,
            final Long contentLenght,
            final InputStream body) {

            this.headers = headers;
            this.contentLenght = contentLenght;
            this.body = body;
        }

        @Override
        public InputStream getBody() {
            return body;
        }

        @Override
        public Long getContentLength() {
            return contentLenght;
        }

        @Override
        public Map<String, List<String>> getHeaders() {
            return headers;
        }

        private String getStatusLine() {
            List<String> line = getHeaders().get(null);
            return Joiner.on("").join(line);
        }

        @Override
        public short getStatus() {
            String middle = getStatusLine().split(" ")[1];
            return Short.parseShort(middle);
        }

        @Override
        public String getStatusMessage() {
            return getStatusLine().split(" ")[2];
        }

        @Override
        public boolean isSuccessful() {
            short status = getStatus();
            return (status >= 200 && status < 300);
        }

    }

    private static class IO {

        private final ProcessIO pio;

        private final Boolean quiet;

        public IO(ProcessIO pio, Boolean quiet) {
            Preconditions.checkNotNull(pio);
            Preconditions.checkNotNull(quiet);
            this.pio = pio;
            this.quiet = quiet;
        }

        public void out(String message) {
            if (quiet == false) {
                pio.getOut().println(message);
            }
        }

        public void err(String message) {
            if (quiet == false) {
                pio.getErr().println(message);
            }
        }

        public void exception(Exception ex) {
            Class<? extends Exception> cls = ex.getClass();
            String name = cls.getName().replace("Exception", "");
            String message = ex.getLocalizedMessage();
            String full = name + ": " + message;
            this.err(full);
        }
    }

}
