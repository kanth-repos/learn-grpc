package com.example.consumer;

import com.example.sum.SumGrpc;
import com.example.sum.SumGrpc.SumStub;

import com.example.sum.SumResponse;
import com.example.sum.SumRequest;

import io.grpc.stub.StreamObserver;
import io.grpc.Channel;

import java.util.List;

class SumStreamObserver implements StreamObserver<SumResponse> {
  private Integer sum = null;

  public Integer getSum() {
    return sum;
  }

  @Override
  public void onNext(SumResponse value) {
    sum = value.getValue();
  }

  @Override
  public void onError(Throwable t) {
  }

  @Override
  public void onCompleted() {
    synchronized(this) {
      this.notifyAll();
    }
  }
}

public class SumServiceComsumer {
  private final SumStub stub;

  public SumServiceComsumer(Channel channel) {
    this.stub = SumGrpc.newStub(channel);
  }

  public int sum(List<Integer> list) throws InterruptedException {
    var resObs = new SumStreamObserver();
    var reqObs = stub.sum(resObs);

    for (var e: list) {
      reqObs.onNext(SumRequest.newBuilder().setValue(e).build());
    }

    reqObs.onCompleted();

    synchronized(resObs) {
      while (resObs.getSum() == null) {
        resObs.wait();
      }
    }

    return resObs.getSum();
  }
}
