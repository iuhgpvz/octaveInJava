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

import eu.simuline.octave.type.OctaveInt;

/**
 * This is deactivated. 
 * writing an {@link OctaveInt} holding an uint8 to a {@link Writer}. 
 * Note that java class {@link OctaveInt} 
 * is registered with {@link Int32MatrixWriter}. 
 */
public final class Uint8MatrixWriter 
    extends AbstractPrimitiveMatrixWriter<OctaveInt> {

    @Override
    public Class<OctaveInt> javaType() {
        return OctaveInt.class;
    }

    @Override
    public void write(final Writer writer, 
		      final OctaveInt octaveInt) throws IOException {

        writer.write("# type: uint8 matrix\n");
	saveDataVectorized(writer, octaveInt);
	// **** note: unlike for floating types and bool, 
	// there is no special case for 2 dimensions, i.e. matrices 
	// using saveData2d(writer, octaveInt);
	saveDataVectorized(writer, octaveInt);
    }

    // private void saveData2d(final Writer writer, 
    // 			    final OctaveInt octaveMatrix) 
    // 	throws IOException {

    // 	throw new IllegalStateException("Not supported by integer types");
    // }

    private void saveDataVectorized(final Writer writer, 
				    final OctaveInt octaveMatrix) 
	throws IOException {

        final int[] data = octaveMatrix.getData();
        writer.write(NDIMS + octaveMatrix.getSizeLength() + "\n");
        for (int idx = 1; idx <= octaveMatrix.getSizeLength(); idx++) {
            writer.write(" " + octaveMatrix.getSize(idx));
        }
        for (final int iNum : data) {
            writer.write("\n " + iNum);
        }
        writer.write("\n");
    }

}
