/*
 * Copyright 2008, 2009 Ange Optimization ApS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.simuline.octave.io.impl;

import java.io.IOException;
import java.io.Writer;

import eu.simuline.octave.io.spi.OctaveDataWriter;
import eu.simuline.octave.type.OctaveString;

/**
 * The writer for the octave type "string" 
 * writing an {@link OctaveString} to a {@link Writer}. 
 * The name is {@link OctaveStringWriter} instead of <code>StringWriter</code> 
 * to avoid name clash with {@link java.io.StringWriter}. 
 */
public final class OctaveStringWriter extends OctaveDataWriter<OctaveString> {

    @Override
    public Class<OctaveString> javaType() {
        return OctaveString.class;
    }

    @Override
    public void write(final Writer writer,
		      final OctaveString octaveString) throws IOException {
        final String string = octaveString.getString();
        writer.write("# type: string\n" + //
		     "# elements: 1\n" + //
		     "# length: " + string.length() + "\n" + //
		     string + "\n");
    }

}
