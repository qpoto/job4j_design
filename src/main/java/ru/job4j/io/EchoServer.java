package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String answer = in.readLine();
                    if (answer.contains("msg=Hello")) {
                        out.write("Hello, dear friend.".getBytes());
                    }

                    if (answer.contains("msg=What")) {
                        out.write("What".getBytes());
                    }

                    if (answer.contains("msg=Bye")) {
                        out.write("Server close".getBytes());
                        server.close();
                    }
                    out.flush();
                }
            }
        }
    }
}