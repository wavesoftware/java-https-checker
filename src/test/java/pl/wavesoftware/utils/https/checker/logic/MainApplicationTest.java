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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.wavesoftware.utils.https.checker.cli.Cli;
import pl.wavesoftware.utils.https.checker.cli.ProcessIO;
import pl.wavesoftware.utils.https.checker.cli.Result;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class MainApplicationTest {

    private OutputStream out;

    private OutputStream err;

    private ProcessIO createProcessIO() {
        byte[] buf = new byte[0];
        return createProcessIO(buf);
    }

    private ProcessIO createProcessIO(byte[] buf) {
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        return new ProcessIO() {

            @Override
            public PrintStream getOut() {
                return new PrintStream(out);
            }

            @Override
            public PrintStream getErr() {
                return new PrintStream(err);
            }
        };
    }

    @Test
    public void testPerform() {
        ProcessIO pio = createProcessIO();
        MainApplication instance = new MainApplicationImpl("https://www.google.pl/", false, 10);
        int expResult = 0;
        Result result = instance.perform(pio);
        assertEquals(expResult, result.retcode());
    }

    public class MainApplicationImpl extends MainApplication {

        private final String url;

        private final Boolean quiet;

        private final Short maxRedirects;

        public MainApplicationImpl(String url, boolean quiet, int maxRedirects) {
            this.url = url;
            this.quiet = quiet;
            this.maxRedirects = Integer.valueOf(maxRedirects).shortValue();
        }

        @Override
        public Params getParams() {
            return new Params() {

                @Override
                @SuppressWarnings("unchecked")
                public <T> T get(Cli.Arguments argument) {
                    switch (argument) {
                        case ADDRESS:
                            return (T) url;
                        case QUIET:
                            return (T) quiet;
                        case MAX_REDIRECTS:
                            return (T) maxRedirects;
                        default:
                            throw new UnsupportedOperationException();
                    }
                }
            };
        }
    }

}
