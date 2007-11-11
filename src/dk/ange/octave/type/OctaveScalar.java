package dk.ange.octave.type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import dk.ange.octave.OctaveException;

/**
 * @author Kim Hansen
 */
public class OctaveScalar extends OctaveType {

    private static final long serialVersionUID = 2221234552189760358L;

    private double value;

    /**
     * @param reader
     * @throws OctaveException
     */
    public OctaveScalar(final BufferedReader reader) throws OctaveException {
        this(reader, true);
    }

    /**
     * @param reader
     * @param close
     *            whether to close the stream. Really should be true by default, but Java....
     * @throws OctaveException
     */
    public OctaveScalar(final BufferedReader reader, final boolean close) throws OctaveException {
        try {
            String line = reader.readLine();
            final String token = "# type: scalar";
            if (!line.equals(token)) {
                throw new OctaveException("Expected <" + token + ">, but got <" + line + ">\n");
            }
            line = reader.readLine();
            value = parseDouble(line);
            if (close) {
                reader.close();
            }
        } catch (final IOException e) {
            throw new OctaveException(e);
        }
    }

    /**
     * @param value
     */
    public OctaveScalar(final double value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object thatObject) {
        if (!(thatObject instanceof OctaveScalar)) {
            return false;
        }
        final OctaveScalar that = (OctaveScalar) thatObject;
        return this.value == that.value;
    }

    /**
     * @return Returns the value of this object
     */
    public double getDouble() {
        return value;
    }

    @Override
    public void save(final String name, final Writer writer) throws IOException {
        writer.write("# name: " + name + "\n# type: scalar\n" + value + "\n\n");
    }

    @Override
    public OctaveScalar makecopy() {
        return new OctaveScalar(value);
    }

    /**
     * Sets value
     * 
     * @param value
     */
    public void set(final double value) {
        this.value = value;
    }

}
