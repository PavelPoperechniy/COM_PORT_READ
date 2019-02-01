import gnu.io.SerialPort;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class UtilitaCOMport {
    private SerialPort serialPort;


    public UtilitaCOMport() {
    }

    public UtilitaCOMport(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void serialPort_Write(String messege) throws IOException {
        // serialPort = SerialPortCr.getInstanse().getSerialPortInstance(port_name);
        OutputStream out = serialPort.getOutputStream();

        out.write(messege.getBytes());
    }

    public void serialPort_Write(byte messege) throws IOException {
        // serialPort = SerialPortCr.getInstanse().getSerialPortInstance(port_name);
        OutputStream out = serialPort.getOutputStream();

        out.write(messege);
    }

    public void serialPort_Read() throws IOException {
        InputStream inputStream = serialPort.getInputStream();
        byte[] buffer = null;
        buffer = new byte[1024];
        int bytes = 0;
        String s;
        while ((bytes = inputStream.read(buffer)) > 0) {
            System.out.print(new String(buffer, 0, bytes, "UTF-8"));

        }

    }

}
