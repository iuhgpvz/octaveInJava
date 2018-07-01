/*
 * Copyright 2009 Ange Optimization ApS
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
package eu.simuline.octave.type.matrix;

/**
 * General matrix with Object values. 
 * 
 * @param <T>
 *    Type of the values
 */
// used as superclass of class OctaveCell only 
public abstract class GenericMatrix<T> extends AbstractGenericMatrix<T[]> {

    /**
     * @param size
     */
    protected GenericMatrix(final int... size) {
        super(size);
    }

    /**
     * @param data
     * @param size
     */
    @SuppressWarnings("unchecked")
    protected GenericMatrix(final Object[] data, final int... size) {
        super((T[]) data, size);
    }

    /**
     * Copy constructor. 
     * 
     * @param o
     */
    protected GenericMatrix(final AbstractGenericMatrix<T[]> o) {
        super(o);
    }

    @SuppressWarnings("unchecked")
    // **** is this a bug (ClassCastException)??
    protected final T[] newD(final int size) {
        return (T[]) new Object[size];
    }

    public final int dataLength() {
        return data.length;
    }

    protected final boolean dataEquals(final int usedLength,
				       final T[] otherData) {
        for (int i = 0; i < usedLength; i++) {
            final T o1 = this.data[i];
            final T o2 = otherData[i];
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set the value resizing by need. 
     * 
     * @param value
     * @param pos
     * @see #setPlain(Object, int)
     */
    // overwritten in OctaveCell 
    // questionable: where is value==null needed? 
    // maybe this can be excluded right here. 
    // It is not worth fixing now because possibly redesign required. 
    @SuppressWarnings("checkstyle:designforextension")
    public void set(final T value, final int... pos) {
        resizeUp(pos);
        setPlain(value, pos2ind(pos));
    }

    /**
     * Set the value assuming resize is not necessary. 
     * 
     * @param value
     * @param pos
     * @see #set(Object, int[])
     */
    public final void setPlain(final T value, final int pos) {
         this.data[pos] = value;
    }

    // api-docs inherited from AbstractGenericMatrix 
    public void setPlain(final String value, final int pos) {
	throw new UnsupportedOperationException();
    }

    /**
     * Get the value. 
     * 
     * @param pos
     * @return value at pos
     */
    // overwritten in OctaveCell 
    // see set(...)
    @SuppressWarnings("checkstyle:designforextension")
    public T get(final int... pos) {
        return this.data[pos2ind(pos)];
    }

}
