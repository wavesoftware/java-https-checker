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
import org.junit.Test;
import pl.wavesoftware.utils.https.checker.cli.Cli;
import pl.wavesoftware.utils.https.checker.cli.Result;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class HttpsCheckerMainTest {

    private static final String GOOGLE = "https://www.google.pl/";

    @Test
    public void testDoMain() {
        String[] args = new String[]{GOOGLE};
        Result result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(0, result.retcode());

        args = new String[]{"-q", GOOGLE};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(0, result.retcode());

        args = new String[]{"-q", "-r", "3", GOOGLE};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(0, result.retcode());

        args = new String[]{"-quoa!", "--max_redirects", "3"};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(Result.result(Cli.Retcodes.INVALID_ARGS).retcode(), result.retcode());

        args = new String[]{"httttp://invalid.addres.com/quoa"};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(Result.result(Cli.Retcodes.INVALID_ARGS).retcode(), result.retcode());

        args = new String[]{"https://invalid.addres.org/quoa"};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(Result.result(Cli.Retcodes.NO_CONNECTION).retcode(), result.retcode());

        args = new String[]{"https://www.google.pl/no-a-file-to-download.txt"};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(Result.result(Cli.Retcodes.NO_CONNECTION).retcode(), result.retcode());

        args = new String[]{"https://tv.eurosport.com/"};
        result = HttpsCheckerMain.doMain(args);
        assertNotNull(args);
        assertEquals(Result.result(Cli.Retcodes.BAD_SSL).retcode(), result.retcode());
    }

}
