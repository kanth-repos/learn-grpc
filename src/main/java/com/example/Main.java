package com.example;

import java.io.IOException;

import com.example.consumer.FibonacciServiceConsumer;
import com.example.consumer.GreeterServiceConsumer;
import com.example.consumer.SumServiceComsumer;
import com.example.provider.FibonacciServiceProvider;
import com.example.provider.GreeterServiceProvider;
import com.example.provider.SumServiceProvider;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;

import java.util.*;

public class Main {
  public static void runFibonacciGrpc() throws IOException, InterruptedException {
    final var server = new FibonacciServiceProvider(port);
    final var client = new FibonacciServiceConsumer(
      Grpc.newChannelBuilder(getTatget(), InsecureChannelCredentials.create()).build()
    );

    System.out.println("Starting server at " + getTatget());
    server.start();
    System.out.println("Fibonacci Sequence <= 20...");
    System.out.println(client.fibonacci(20));
    System.out.println("Stoping Server...");
    server.stop();
  }

  public static void runSumGrpc() throws IOException, InterruptedException {
    final var server = new SumServiceProvider(port);
    final var client = new SumServiceComsumer(
      Grpc.newChannelBuilder(getTatget(), InsecureChannelCredentials.create()).build()
    );

    var list = new ArrayList<Integer>();

    for(int i = 0; i < 10; ++i) {
      list.add(1);
    }

    System.out.println("Starting server at " + getTatget());
    server.start();
    System.out.println("Sum " + list);
    System.out.println(client.sum(list));
    System.out.println("Stoping Server...");
    server.stop();
  }

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
    runSumGrpc();
  }
}
