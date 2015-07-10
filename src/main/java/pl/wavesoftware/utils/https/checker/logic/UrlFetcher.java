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

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public interface UrlFetcher {

    HttpResponse getUrl(URL url);

    /**
     * A HTTP HttpResponse
     */
    interface HttpResponse {

        /**
         * Body of the response
         *
         * @return a body as stream
         */
        InputStream getBody();

        /**
         * Gets content length
         *
         * @return length of body content
         */
        Long getContentLength();

        /**
         * Headers for the response
         *
         * @return HTTP headers for response
         */
        Map<String, List<String>> getHeaders();

        /**
         * Short numeric status
         *
         * @return short numeric status ex.: 200
         */
        short getStatus();

        /**
         * Status message
         *
         * @return Status message for ex.: OK
         */
        String getStatusMessage();

        /**
         * Checks if response was successful
         *
         * @return true - if was successful, false otherwise
         */
        boolean isSuccessful();
    }
}
