import General.*;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

@Slf4j
public class NetworkChatClient {

    private static class Settings{
        private static int PORT;

        static {
            NetworkChatClient chatServer = new NetworkChatClient();
            URL url = chatServer.getClass().getClassLoader().getResource("settings.txt");
            String fileName = Decoder.DecodeURL(url);
            try {
                url = new URL(fileName);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            File file = new File(url.getFile());

            Map<String, Consumer<String>> settingsCommands = new HashMap<>();
            settingsCommands.put("PORT", (stringNumber) -> {
                try {
                    PORT = Integer.parseInt(stringNumber);
                    log.debug(String.format("NetworkChatServer.Settings.PORT = %d", Settings.PORT));
                } catch (NumberFormatException ex) {
                    log.error(ex.getMessage(), ex);
                    ex.printStackTrace();
                }
            });

            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line = reader.readLine();
                while (line != null) {
                    WordDelimiter wordDelimiter = new WordDelimiter(line);
                    Consumer<String> command = settingsCommands.get(wordDelimiter.getFirstWord());
                    if (command != null)
                        command.accept(wordDelimiter.getSecondWord());
                    line = reader.readLine();
                }
            } catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private static final String EXIT_COMMAND = "/exit";

    public static void main(String[] args) {
        System.out.println("NetworkChatClient is run...");
        log.info("NetworkChatClient is run...");

        //C:\Windows\System32\drivers\etc\hosts
        String host = "netology.homework";//"localhost";//"127.0.0.1";
        int port = Settings.PORT;

        // Определяем сокет сервера
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        try (SocketChannel socketChannel = SocketChannel.open()) {

            //  подключаемся к серверу
            socketChannel.connect(socketAddress);

            final ByteBuffer buffer = ByteBuffer.allocate(2 << 10);

            try {

                // ждем приветствие от сервера
                int bytesCount = 0;
                while (bytesCount == 0) bytesCount = socketChannel.read(buffer);
                String result = new String(buffer.array(), 0, bytesCount, StandardCharsets.UTF_8).trim();
                System.out.println(result);
                buffer.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }

            Thread channelInput = new ServerChannelInput(socketChannel);
            channelInput.setDaemon(true);
            channelInput.start();

            try (Scanner scanner = new Scanner(System.in)) {

                String text;
                while (true) {
                    text = scanner.nextLine();
                    socketChannel.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
                    if (EXIT_COMMAND.equals(text)){
                        channelInput.interrupt();
                        socketChannel.close();
                        break;
                    }

                }
            } catch (SocketException e) {
                System.out.printf("сервер отвалился: %s %n", e.getMessage());
                channelInput.interrupt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}