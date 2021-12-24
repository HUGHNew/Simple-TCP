import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @program: Simple-TCP
 * @description:
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {
//        Executor executor= Executors.newSingleThreadExecutor();
//        executor.execute(()-> System.out.println("HelloWorld"));
//        String msg="Hello";
//        System.out.printf("msg len:%d msg bytes len:%d%n", msg.length(),msg.getBytes().length);
        ExecutorService pool=Executors.newCachedThreadPool();
        Future<?> svr=pool.submit(Test::TcpServerDemo);
        Thread.sleep(100);
        Future<?> clt=pool.submit(Test::TcpClientDemo);
        Thread.sleep(100);
//        svr.get(10,TimeUnit.MILLISECONDS);
        svr.cancel(true);
        try {
            System.out.println((String)clt.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
    }
    public static final int Port=12345;
    public static void UdpServerDemo(){
        DatagramSocket svr= null;
        try {
            svr = new DatagramSocket(Port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        DatagramPacket packet=new DatagramPacket(new byte[1024],1024 );
        Function<byte[], String> pkt = (byte[] data) ->new String(data, StandardCharsets.UTF_8).toUpperCase();
        while(true){
            try {
                assert svr != null;
                svr.receive(packet);
                packet.setData(pkt.apply(packet.getData()).getBytes());
                svr.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void UdpClientDemo(){
        DatagramSocket client=null;
        try {
            client=new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("New Client Error");
            return;
        }
        String msg="Hello";
        System.out.println(msg);
        DatagramPacket pkt=new DatagramPacket(msg.getBytes(),msg.getBytes().length);
        try {
            client.connect(InetAddress.getByName("127.0.0.1"), Port);
            client.send(pkt);
            client.receive(pkt);
            msg=new String(pkt.getData());
//            client.
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg+"|Client Exit");
    }
    public static void TcpServerDemo(){
        try{
            ServerSocket server=new ServerSocket(Port);
            while(true){
                Socket worker=server.accept();
                new Thread(()->{
                    try {
                        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(worker.getOutputStream()));
                        BufferedReader reader=new BufferedReader(new InputStreamReader(worker.getInputStream()));
                        writer.write(reader.readLine());
                        writer.flush();
                        worker.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
//                Thread.sleep(1000);
            }
        }catch (IOException ignored){}
    }
    public static String TcpClientDemo(){
        try{
            Socket client=new Socket(InetAddress.getByName("127.0.0.1"),Port);
            InputStream input=client.getInputStream();
            OutputStream output=client.getOutputStream();
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(output));
            BufferedReader reader=new BufferedReader(new InputStreamReader(input));
            writer.write("Hello\n");
            writer.flush();
            String str=reader.readLine();
            client.close();
            return str;
        }catch (IOException ignored){}
        return "Error";
    }
}
