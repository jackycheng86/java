package socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket网络编程服务端
 * @author chengjian
 * @date 2019/7/16
 */
public class SocketServer {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        //设置标志位为真
        boolean flag=true;
        //创建Socket client以接收来自客户端的请求
        Socket client=null;
        String inputLine;
        //以端口5000创建一个服务器Socket
        ServerSocket serverSocket =new ServerSocket (5000);
        System.out.println("服务器在端口5000上监听");
        //也可以使用serverSocket.getLocalPort()来获得端口号
        while(flag)
        {
            client=serverSocket.accept();
            //监听并接受与此Socket的连接，该方法会阻塞直到有一个连接产生
            DataInputStream input=new DataInputStream(new BufferedInputStream(client.getInputStream()));
            PrintStream output=new PrintStream(new BufferedOutputStream(client.getOutputStream()));
            while (( inputLine= input.readLine())!=null)
            {
                System.out.println("客户端传递内容：" + inputLine);
                if(inputLine.equals("quit"))
                {
                    flag=false;
                    break;
                }
                output.println(inputLine);
                output.flush();
            }
            output.close();
            input.close();
            client.close();
        }
        serverSocket.close();
    }

}
