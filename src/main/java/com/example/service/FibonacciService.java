package com.example.service;

import com.example.fibonacci.FibonacciGrpc.FibonacciImplBase;
import com.example.fibonacci.FibonacciRequest;
import com.example.fibonacci.FibonacciResponse;

import io.grpc.stub.StreamObserver;

public class FibonacciService extends FibonacciImplBase {
  @Override
  public void fibonacci(FibonacciRequest request, StreamObserver<FibonacciResponse> responseObserver) {
    int current = 0, next = 1;

    for (int i = 0; current <= request.getLimit(); ++i) {
      responseObserver.onNext(FibonacciResponse.newBuilder().setIndex(i).setValue(current).build());
      int sum = current + next;
      current = next;
      next = sum;
    }

    responseObserver.onCompleted();
  }
}
