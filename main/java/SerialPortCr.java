import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

public class SerialPortCr {
    private static SerialPortCr instanse = null;
    /* Буферизованный поток ввода с порта */
    private static InputStream input;
    private static SerialPort serialPort = null;


    /* поток вывода на порт */
    private static OutputStream output;

    /* миллисекунды для блокировки при ожидании открытия порта */
    private static final int TIME_OUT = 2000;

    /* Биты по умолчанию в секунду для COM-порта. */
    private static final int DATA_RATE = 9600;

    public static SerialPortCr getInstanse() {
        if (instanse == null) {
            instanse = new SerialPortCr();
        }
        return instanse;
    }

    private SerialPortCr() {
    }

    public SerialPort initialize(String port_name) {
        SerialPort port = null;
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // iterate through, looking for the port
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            if (currPortId.getName().equals(port_name)) {
                // System.out.println(currPortId.getName());
                try {
                    port = (SerialPort) currPortId.open(port_name, TIME_OUT);
                    // установить параметры порта
                    try {
                        port.setSerialPortParams(DATA_RATE,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);

                        // открываем потоки
                        //   input = serialPort.getInputStream ();
                        //   output = serialPort.getOutputStream ();

                        // добавить прослушиватели событий
                        port.addEventListener(new ReaderComPort(port));
                        port.notifyOnDataAvailable(true);

                    } catch (UnsupportedCommOperationException e) {
                        e.printStackTrace();

                    } catch (TooManyListenersException e) {
                        e.printStackTrace();
                        // } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (PortInUseException e) {
                    e.printStackTrace();
                }

            }
        }
        return port;
    }
//    public  SerialPort getSerialPortInstance(String name_port) {
//        if(serialPort ==null){
//            serialPort = initialize(name_port);
//        }
//        return serialPort;
//    }


}
