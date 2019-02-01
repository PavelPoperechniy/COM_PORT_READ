import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;

public class ReaderComPort implements SerialPortEventListener {
    private SerialPort serialPort;

    //   private UtilitaCOMport coMport = new UtilitaCOMport(serialPort);
    public ReaderComPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            UtilitaCOMport coMport = new UtilitaCOMport();
            coMport.setSerialPort(serialPort);
            try {
                coMport.serialPort_Read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
