package com.example.consumer;

import com.example.greeter.GreeterGrpc;
import com.example.greeter.HelloRequest;
import com.example.greeter.GreeterGrpc.GreeterBlockingStub;

import io.grpc.Channel;

public class GreeterServiceConsumer {
  private final GreeterBlockingStub stub;

  public GreeterServiceConsumer(Channel channel) {
    this.stub = GreeterGrpc.newBlockingStub(channel);
  }

  public String greet(String name) {
    var res = stub.sayHello(HelloRequest.newBuilder().setName(name).build());
    return res.getMessage();
  }
}
