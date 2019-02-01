import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class JavaRxTx {


    public static void main(String[] args) {
        SerialPort port = SerialPortCr.getInstanse().initialize("COM7");
        UtilitaCOMport coMport = new UtilitaCOMport(port);
        try {
            //  coMport.serialPort_Write("YES"+'\n');
            coMport.serialPort_Read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
