package com.example.consumer;

import com.example.fibonacci.FibonacciGrpc;
import com.example.fibonacci.FibonacciRequest;
import com.example.fibonacci.FibonacciGrpc.FibonacciBlockingStub;

import io.grpc.Channel;
import java.util.ArrayList;
import java.util.List;

public class FibonacciServiceConsumer {
  private final FibonacciBlockingStub stub;

  public FibonacciServiceConsumer(Channel channel) {
    this.stub = FibonacciGrpc.newBlockingStub(channel);
  }

  public List<Integer> fibonacci(int limit) {
    var itr = stub.fibonacci(FibonacciRequest.newBuilder().setLimit(limit).build());
    var res = new ArrayList<Integer>();

    while (itr.hasNext()) {
      res.add(itr.next().getValue());
    }

    return res;
  }
}
