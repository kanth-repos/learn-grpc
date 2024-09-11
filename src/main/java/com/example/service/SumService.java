package com.example.service;

import com.example.sum.SumGrpc.SumImplBase;
import com.example.sum.SumResponse;
import com.example.sum.SumRequest;

import io.grpc.stub.StreamObserver;

class SumStreamObserver implements StreamObserver<SumRequest> {
  private StreamObserver<SumResponse> response;
  private int sum = 0;

  public SumStreamObserver(StreamObserver<SumResponse> response) {
    this.response = response;
  }

  public int getSum() {
    return sum;
  }

  @Override
  public void onNext(SumRequest value) {
    sum += value.getValue();
  }

  @Override
  public void onError(Throwable t) {
  }

  @Override
  public void onCompleted() {
    response.onNext(SumResponse.newBuilder().setValue(sum).build());
    response.onCompleted();
  }
}

public class SumService extends SumImplBase {
  @Override
  public StreamObserver<SumRequest> sum(
      StreamObserver<SumResponse> responseObserver) {
    return new SumStreamObserver(responseObserver);
  }
}
