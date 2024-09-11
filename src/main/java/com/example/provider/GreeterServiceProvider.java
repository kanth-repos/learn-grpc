package com.example.provider;

import com.example.service.GreeterService;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;

public class GreeterServiceProvider {
  private Server server;
  private int port;

  public GreeterServiceProvider(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    this.server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
        .addService(new GreeterService())
        .build()
        .start();
  }

  public void stop() throws InterruptedException {
    this.server.shutdown().awaitTermination();
  }
}
