package socket;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * socket网络编程客户端
 * @author chengjian
 * @date 2019/7/16
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            // 创建一个流Socket并与主机mice上的端口5000相连接
            Socket clientSocket = new Socket(InetAddress.getByName("127.0.0.1"), 5000);
            // 向此Socket写入字节的一个输出流
            OutputStream output = clientSocket.getOutputStream();
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            // 创建新的数据输入流以便从指定的输入流中读出数据
            int c;
            String response;
            // 从屏幕上接受输入的字符串，并且分解成一个个字符
            while ((c = System.in.read()) != -1)
            {
                output.write((byte) c);
                // 如果字符为回车，则输出字符串缓冲
                if (c == '\n')
                {
                    output.flush();
                    response = input.readLine();
                    System.out.println("数据交互相应:" + response);
                }
            }
            output.close();
            input.close();
            clientSocket.close();

        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("出现错误！");
        }
    }

}
