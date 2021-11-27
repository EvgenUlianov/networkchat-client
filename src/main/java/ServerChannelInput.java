import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

@Slf4j
@Data
public class ServerChannelInput extends  Thread{
    private SocketChannel channel;

    public ServerChannelInput(SocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run(){
        //  Определяем буфер для получения данных
        final ByteBuffer buffer = ByteBuffer.allocate(2 << 10);

        while (channel.isConnected() && isAlive()) {

            //  читаем данные из канала в буфер
            try {

                int bytesCount = 0;
                while (bytesCount <= 0) {
                    bytesCount = channel.read(buffer);
                }

                final String text = new String(buffer.array(), 0, bytesCount, StandardCharsets.UTF_8);
                buffer.clear();

                System.out.println(text);

            } catch (SocketException e) {
                System.out.printf("сервер отвалился: %s %n", e.getMessage());
                try {
                    channel.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

        }




    }
}
