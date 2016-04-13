package tests;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Since we know that we will be testing multiple classes to verify that they are Serializable
// we can extract common functionality to an abstract class we use for our tests.
// Any test class that needs to test that a class is Serializable can simply extend this
// can call the assertObjectSerializable method.
public abstract class SerializableTestCase {

	public void assertObjectSerializable(Serializable expected) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = new ObjectOutputStream(out);
		objectOut.writeObject(expected);
		InputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream objectIn = new ObjectInputStream(in);
		Serializable actual = (Serializable)objectIn.readObject();
		assertEquals(expected, actual);
	}

}
