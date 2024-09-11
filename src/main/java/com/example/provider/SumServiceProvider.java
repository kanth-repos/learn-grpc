package com.example.provider;

import com.example.service.SumService;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;

import java.io.IOException;

public class SumServiceProvider {
  private Server server;
  private int port;

  public SumServiceProvider(int port) {
    this.port = port;
  }

  public void start() throws IOException {
    this.server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
        .addService(new SumService())
        .build()
        .start();
  }

  public void stop() throws InterruptedException {
    this.server.shutdown().awaitTermination();
  }
}
