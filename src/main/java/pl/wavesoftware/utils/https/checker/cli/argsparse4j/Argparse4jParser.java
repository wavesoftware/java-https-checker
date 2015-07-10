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

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import pl.wavesoftware.utils.https.checker.cli.Application;
import pl.wavesoftware.utils.https.checker.cli.ArgsParser;
import pl.wavesoftware.utils.https.checker.cli.Cli;
import pl.wavesoftware.utils.https.checker.cli.Cli.Arguments;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class Argparse4jParser implements ArgsParser<ArgumentParser> {

    @Override
    public Function<Void, ArgumentParser> configure() {
        return new Function<Void, ArgumentParser>() {

            @Override
            public ArgumentParser apply(Void input) {
                ArgumentParser parser = ArgumentParsers.newArgumentParser(Cli.APP_NAME)
                    .defaultHelp(true)
                    .description(Cli.DESCRIPTION);
                addAddress(parser, Arguments.ADDRESS);
                addQuiet(parser, Arguments.QUIET);
                addMaxRedirects(parser, Arguments.MAX_REDIRECTS);
                return parser;
            }

            private void addAddress(ArgumentParser parser, Arguments argument) {
                parser.addArgument(argument.label)
                    .type(argument.type)
                    .help(argument.help);
            }

            private void addQuiet(ArgumentParser parser, Arguments argument) {
                parser.addArgument(argument.config)
                    .action(net.sourceforge.argparse4j.impl.Arguments.storeTrue())
                    .setDefault(argument.defaultValue)
                    .type(argument.type)
                    .help(argument.help);
            }

            private void addMaxRedirects(ArgumentParser parser, Arguments argument) {
                parser.addArgument(argument.config)
                    .setDefault(argument.defaultValue)
                    .type(argument.type)
                    .help(argument.help);
            }
        };
    }

    @Override
    public Application parse(Function<Void, ArgumentParser> config, String[] args) {
        Preconditions.checkNotNull(config);
        Preconditions.checkNotNull(args);
        ArgumentParser parser = Preconditions.checkNotNull(config.apply(null));
        Application app;
        try {
            Namespace ns = parser.parseArgs(args);
            app = Argsparse4jApplication.successful(ns);
        } catch (ArgumentParserException e) {
            app = Argsparse4jApplication.failed(parser, e);
        }
        return app;
    }

}
