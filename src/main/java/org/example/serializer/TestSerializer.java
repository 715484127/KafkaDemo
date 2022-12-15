package org.example.serializer;

import org.example.beans.Persion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TestSerializer {
    public static void main(String[] args) throws IOException {
        byte[] data;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(os);
        outputStream.writeObject(new Persion());
        data = os.toByteArray();
        System.out.println(data);
    }
}
