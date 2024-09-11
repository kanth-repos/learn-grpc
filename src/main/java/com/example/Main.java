package com.example;

import java.io.IOException;

import com.example.consumer.GreeterServiceConsumer;
import com.example.provider.GreeterServiceProvider;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;

public class Main {
  public static void runGreetGrpc() throws IOException, InterruptedException {
    final var server = new GreeterServiceProvider(port);
    final var client = new GreeterServiceConsumer(
      Grpc.newChannelBuilder(getTatget(), InsecureChannelCredentials.create()).build()
    );

    System.out.println("Starting server at " + getTatget());
    server.start();
    System.out.println("Greeting...");
    System.out.println(client.greet("John Deo"));
    System.out.println("Stoping Server...");
    server.stop();
  }

  final static int port = 3000;

  public static String getTatget() {
    return "localhost:" + port; 
  }

  public static void main(String[] args) throws Exception {
    runGreetGrpc();
  }
}
