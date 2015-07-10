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

import com.google.common.collect.Maps;
import java.util.Map;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import pl.wavesoftware.utils.https.checker.cli.Application;

/**
 *
 * @author Krzysztof Suszyński <krzysztof.suszynski@wavesoftware.pl>
 */
public class Argsparse4jApplicationTest {

    @Test
    public void testSuccessful() {
        Map<String, Object> map = Maps.newHashMap();
        Namespace namespace = new Namespace(map);
        Application result = Argsparse4jApplication.successful(namespace);
        Assert.assertNotNull(result);
    }

    @Test
    public void testFailed() {
        ArgumentParser parser = Mockito.mock(ArgumentParser.class);
        ArgumentParserException e = Mockito.mock(ArgumentParserException.class);
        Application result = Argsparse4jApplication.failed(parser, e);
        Assert.assertNotNull(result);
    }

}
