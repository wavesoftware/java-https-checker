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

import net.sourceforge.argparse4j.inf.Namespace;
import pl.wavesoftware.utils.https.checker.cli.Application.Params;
import pl.wavesoftware.utils.https.checker.cli.Cli.Arguments;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class NamespaceParams implements Params {

    private final Namespace namespace;

    /**
     * Constructor with namespace
     *
     * @param namespace a namespace
     */
    public NamespaceParams(Namespace namespace) {
        this.namespace = namespace;
    }

    @Override
    public <T> T get(Arguments dest) {
        return namespace.get(dest.label);
    }

}
