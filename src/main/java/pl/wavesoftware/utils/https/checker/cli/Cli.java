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
package pl.wavesoftware.utils.https.checker.cli;

import com.google.common.base.Preconditions;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public abstract class Cli {

    public static final String APP_NAME = "HttpsChecker";

    public static final String DESCRIPTION = "It can check is your Java installation can perform connection "
        + "with given HTTPS address";

    public static enum Retcodes {

        OK(0),
        INVALID_ARGS(5),
        BAD_SSL(1),
        NO_CONNECTION(2);

        public final short code;

        private Retcodes(Integer code) {
            this.code = code.byteValue();
        }
    }

    public static enum Arguments {

        ADDRESS(
            new String[]{},
            "Address to be checked",
            String.class,
            null
        ),
        QUIET(
            new String[]{"-q", "--quiet"},
            "Do not display anything, only retcodes",
            Boolean.class,
            false
        ),
        MAX_REDIRECTS(
            new String[]{"-r", "--max_redirects"},
            "Number of redirects to perform at maximum",
            Short.class,
            Integer.valueOf(10).shortValue()
        );

        public final String label;

        public final String[] config;

        public final Class<?> type;

        public final Object defaultValue;

        public final String help;

        private Arguments(String[] config, String help, Class<?> type, Object defaultValue) {
            this.config = config;
            if (config.length > 0) {
                String last = config[Preconditions.checkElementIndex(config.length - 1, config.length)];
                this.label = last.replace("-", "").trim();
            } else {
                this.label = this.name().toLowerCase();
            }
            this.type = type;
            this.defaultValue = defaultValue;
            this.help = help;
        }
    }
}
