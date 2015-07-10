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
package pl.wavesoftware.utils.https.checker.cli.argsparse4j;

import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import pl.wavesoftware.utils.https.checker.cli.Application;
import pl.wavesoftware.utils.https.checker.cli.Cli.Retcodes;
import pl.wavesoftware.utils.https.checker.cli.ProcessIO;
import pl.wavesoftware.utils.https.checker.cli.Result;
import pl.wavesoftware.utils.https.checker.logic.MainApplication;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public abstract class Argsparse4jApplication implements Application {

    public static Application successful(final Namespace namespace) {
        final NamespaceParams np = new NamespaceParams(namespace);
        return new MainApplication() {

            @Override
            public Params getParams() {
                return np;
            }
        };
    }

    public static Application failed(ArgumentParser parser, ArgumentParserException e) {
        return new Failed(parser, e);
    }

    static class Failed extends Argsparse4jApplication {

        private final ArgumentParser parser;

        private final ArgumentParserException e;

        public Failed(ArgumentParser parser, ArgumentParserException e) {
            this.parser = parser;
            this.e = e;
        }

        @Override
        public Result perform(ProcessIO pio) {
            parser.handleError(e);
            return Result.result(Retcodes.INVALID_ARGS);
        }

        @Override
        public Params getParams() {
            return null;
        }
    }

}
