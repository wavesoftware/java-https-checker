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

import com.google.common.base.Preconditions;
import java.io.PrintStream;
import pl.wavesoftware.utils.https.checker.cli.Application;
import pl.wavesoftware.utils.https.checker.cli.ProcessIO;
import pl.wavesoftware.utils.https.checker.cli.Result;
import pl.wavesoftware.utils.https.checker.cli.argsparse4j.Argparse4jParser;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public abstract class HttpsCheckerMain {

    /**
     * Main method for Java
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.exit(doMain(args).retcode());
    }

    protected static Result doMain(String[] args) {
        Argparse4jParser argsParser = new Argparse4jParser();
        Application app = argsParser.parse(argsParser.configure(), args);
        ProcessIO pio = new StdProcessIO();
        return app.perform(pio);
    }

    private static class StdProcessIO implements ProcessIO {

        @Override
        public PrintStream getOut() {
            return Preconditions.checkNotNull(System.out);
        }

        @Override
        public PrintStream getErr() {
            return Preconditions.checkNotNull(System.err);
        }
    }

}
