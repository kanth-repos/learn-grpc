package com.example.service;

import com.example.greeter.HelloReply;
import com.example.greeter.HelloRequest;
import com.example.greeter.GreeterGrpc.GreeterImplBase;

import io.grpc.stub.StreamObserver;

public class GreeterService extends GreeterImplBase {
  @Override
  public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    var reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
