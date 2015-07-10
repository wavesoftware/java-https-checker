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

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public abstract class Result {

    /**
     * Returns a retcode for process
     *
     * @return ret code for process
     */
    public abstract int retcode();

    /**
     * Gets a result from possible retcode
     *
     * @param retcode a ret code
     * @return result
     */
    public static Result result(final Cli.Retcodes retcode) {
        return new Result() {

            @Override
            public int retcode() {
                return retcode.code;
            }
        };
    }

}
