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
package pl.wavesoftware.utils.https.checker;

import static org.junit.Assert.*;

import com.google.common.base.Joiner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.wavesoftware.utils.https.checker.cli.Cli;
import pl.wavesoftware.utils.https.checker.cli.Result;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
@RunWith(Parameterized.class)
public class HttpsCheckerMainTest {

    private static final String GOOGLE = "https://www.google.pl/";

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{
                new Args(new String[]{GOOGLE}),
                0
        }, {
                new Args(new String[]{"-q", GOOGLE}),
                0
        }, {
                new Args(new String[]{"-quoa!", "--max_redirects", "3"}),
                Result.result(Cli.Retcodes.INVALID_ARGS).retcode()
        }, {
                new Args(new String[]{"httttp://invalid.address.com/quoa"}),
                Result.result(Cli.Retcodes.INVALID_ARGS).retcode()
        }, {
                new Args(new String[]{"https://invalid.address.org/quoa"}),
                Result.result(Cli.Retcodes.NO_CONNECTION).retcode()
        }, {
                new Args(new String[]{"https://www.google.pl/no-a-file-to-download.txt"}),
                Result.result(Cli.Retcodes.NO_CONNECTION).retcode()
        }, {
                new Args(new String[]{"https://wrong.host.badssl.com/"}),
                Result.result(Cli.Retcodes.BAD_SSL).retcode()
        }});
    }

    private final Args args;
    private final int retcode;

    public HttpsCheckerMainTest(Args args, int retcode) {
        this.args = args;
        this.retcode = retcode;
    }

    @Test
    public void testDoMain() {
        Result result = HttpsCheckerMain.doMain(args.args);
        assertEquals(retcode, result.retcode());
    }

    private static final class Args {
        private final String[] args;

        private Args(String[] args) {
            this.args = args;
        }

        @Override
        public String toString() {
            return Joiner.on(" ").join(args);
        }
    }

}
